package me.wonwoo.sink;

import me.wonwoo.core.ConfigurationSink;
import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import org.apache.flume.Channel;
import org.apache.flume.Sink;

import java.util.UUID;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisSink extends AbstractChannelFlumeSink {

  private final String sinkName;
  private final RedisAttr redisAttr;

  public FlumeRedisSink(String channelName, ChannelAttr channelAttr, String sinkName, RedisAttr redisAttr, Channel channel) {
    super(channelName, channelAttr, channel);
    this.sinkName = sinkName;
    this.redisAttr = redisAttr;
  }

  @Override
  protected ConfigurationSink createSink() {
    Sink sink = new RedisSink();
    sink.setName(this.sinkName + "-" + UUID.randomUUID());
    //TODO config
    return new ConfigurationSink(sink, redisAttr);
  }

}
