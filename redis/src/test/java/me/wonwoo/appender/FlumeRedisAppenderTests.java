package me.wonwoo.appender;

import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.sink.FlumeRedisSink;
import me.wonwoo.sink.RedisAttr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisAppenderTests {

  @Test
  public void createSink() {
    FlumeRedisAppender flumeRedisAppender = new FlumeRedisAppender();
    flumeRedisAppender.setChannelName("channelTest");
    flumeRedisAppender.setSinkName("sinkTest");
    flumeRedisAppender.setChannelAttr(new ChannelAttr());
    flumeRedisAppender.setChannel(new MemoryChannel());
    RedisAttr redisAttr = new RedisAttr();
    redisAttr.setTopic("test");
    redisAttr.setPassword("123");
    redisAttr.setHost("localhost");
    redisAttr.setPort(6379);
    flumeRedisAppender.setRedisAttr(redisAttr);
    FlumeRedisSink sink = (FlumeRedisSink) flumeRedisAppender.createSink();
    assertThat(sink).isNotNull();
  }

//  private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//  @Test
//  public void appenderRedisError() {
//    try {
//      URL uri = new URL("urltest");
//      uri.openConnection();
//    } catch (MalformedURLException e) {
//      logger.error("url formed exception ", e);
//    } catch (IOException e) {
//      logger.error("error {} : ", e.toString());
//    }
//  }
}