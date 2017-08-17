package me.wonwoo.sink;

import java.util.Map;
import java.util.UUID;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.conf.Configurables;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import me.wonwoo.util.MapperUtils;

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
  protected Sink createSink() {
    Sink sink = new RedisSink();
    sink.setName(this.sinkName + "-" + UUID.randomUUID());
    //TODO config
    Map<String, String> sinkParameters = MapperUtils.configMap(this.redisAttr);
    Context sinkContext = new Context(sinkParameters);
    Configurables.configure(sink, sinkContext);
    return sink;
  }

}
