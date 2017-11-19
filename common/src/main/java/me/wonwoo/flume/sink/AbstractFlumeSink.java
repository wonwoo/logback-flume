package me.wonwoo.flume.sink;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

import me.wonwoo.core.ConfigurationSink;
import org.apache.flume.*;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.channel.MultiplexingChannelSelector;
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
  public void processEvents(Event event) throws Exception {
    Transaction txn = channel.getTransaction();
    txn.begin();
    try {
      channel.put(event);
      txn.commit();
    } catch (Throwable e) {
      txn.rollback();
      throw new FlumeSinkTransactionException("flume transaction error ", e);
    } finally {
      txn.close();
    }

    //TODO 더 알아보기
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
    sink = createSink().configure();
    assertNotNull(sink, "sink");
    assertNotNull(channel, "channel");
    configChannel(channel);
    sink.setChannel(channel);
    sink.start();
    channel.start();
  }

  private void configChannel(Channel channel) {
    Map<String, String> parameters = configureChannel(channel);
    if (parameters != null) {
      Context channelContext = new Context(parameters);
      Configurables.configure(channel, channelContext);
    }
  }

  protected abstract Map<String, String> configureChannel(Channel channel);

  protected abstract ConfigurationSink createSink();

  public Channel getChannel() {
    return channel;
  }

  public Sink getSink() {
    return sink;
  }
}
