package me.wonwoo.flume.sink;

import java.util.List;
import java.util.Map;

import org.apache.flume.*;
import org.apache.flume.conf.Configurables;

import me.wonwoo.flume.exception.FlumeSinkTransactionException;

import static me.wonwoo.util.AssertUtils.assertNotNull;

/**
 * Created by wonwoo on 2016. 6. 7..
 */
public abstract class AbstractFlumeSink implements FlumeSink {

  private final Channel channel;
  private Sink sink;

  AbstractFlumeSink(Channel channel) {
    this.channel = channel;
  }

  @Override
  public void processEvents(List<Event> events) throws Exception {
    Transaction txn = channel.getTransaction();
    txn.begin();
    try {
      for (Event event : events) {
        channel.put(event);
      }
      txn.commit();
    } catch (Throwable e) {
      txn.rollback();
      throw new FlumeSinkTransactionException("flume transaction error ", e);
    } finally {
      txn.close();
    }
    sink.process();
  }

  @Override
  public void shutdown() {
    if (channel != null) {
      channel.stop();
    }
    if (sink != null) {
      sink.stop();
    }
  }


  @Override
  public void start() {
    sink = createSink();
    assertNotNull(sink, "sink");
    assertNotNull(channel, "channel");
    configChannel(channel);
    sink.setChannel(channel);
    sink.start();
    channel.start();
  }

  private void configChannel(Channel channel) {
    Map<String, String> parameters = configureChannel(channel);
    if(parameters != null) {
      Context channelContext = new Context(parameters);
      Configurables.configure(channel, channelContext);
    }
  }

  protected abstract Map<String,String> configureChannel(Channel channel);

  protected abstract Sink createSink();

  public Channel getChannel() {
    return channel;
  }

  public Sink getSink() {
    return sink;
  }
}
