package me.wonwoo.sink;

import org.apache.commons.lang.StringUtils;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

import com.google.common.base.Throwables;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class RedisSink extends AbstractSink implements Configurable {

  private String host;
  private int port;
  private JedisPool pool;
  private String channel;
  private String password;

  @Override
  public synchronized void start() {
    this.pool = new JedisPool(new JedisPoolConfig(), host, port);
    super.start();
  }

  @Override
  public synchronized void stop() {
    if (this.pool != null) {
      this.pool.close();
    }

    super.stop();
  }

  //TODO 확인 필요
  @Override
  public Status process() throws EventDeliveryException {
    Status result = Status.READY;
    Channel channel = getChannel();
    Transaction transaction = null;
    try (Jedis jedis = pool.getResource()) {
      if(StringUtils.isNotBlank(password)) {
        jedis.auth(password);
      }
      transaction = channel.getTransaction();
      transaction.begin();
      Event event = channel.take();

      if(event.getBody() != null) {
        jedis.publish(this.channel.getBytes(), event.getBody());
      }
      transaction.commit();
    } catch (Exception ex) {
      String errorMsg = "Failed to publish events : " + ex;
      if (transaction != null) {
        try {
          transaction.rollback();
        } catch (Exception e) {
          throw Throwables.propagate(e);
        }
      }
      throw new EventDeliveryException(errorMsg, ex);
    }
    return result;
  }

  //TODO config
  @Override
  public void configure(Context context) {
    host = context.getString(RedisSinkConstants.HOST,
        RedisSinkConstants.DEFAULT_HOST);
    port = context.getInteger(RedisSinkConstants.PORT,
        RedisSinkConstants.DEFAULT_PORT);
    channel = context.getString(RedisSinkConstants.TOPIC,
        RedisSinkConstants.DEFAULT_TOPIC);
    password = context.getString(RedisSinkConstants.PASSWORD,
            RedisSinkConstants.DEFAULT_PASSWORD);

  }
}
