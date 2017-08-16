package me.wonwoo.sink;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import me.wonwoo.flume.sink.AbstractFlumeSink;
import me.wonwoo.util.MapperUtils;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.channel.file.FileChannel;
import org.apache.flume.conf.Configurables;

import java.util.Map;
import java.util.UUID;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisSink extends AbstractChannelFlumeSink {

  private String sinkName;

  public FlumeRedisSink(String channelName, ChannelAttr channelAttr, String sinkName) {
    super(channelName, channelAttr, new FileChannel());
    this.sinkName = sinkName;
  }

  @Override
  protected Sink createSink() {
    Sink sink = new RedisSink();
    sink.setName(this.sinkName + "-" + UUID.randomUUID());
    //TODO config
//    Map<String, String> sinkParameters = MapperUtils.configMap(this.kafkaAttr);
    Context sinkContext = new Context();
    Configurables.configure(sink, sinkContext);
    return sink;
  }

}
