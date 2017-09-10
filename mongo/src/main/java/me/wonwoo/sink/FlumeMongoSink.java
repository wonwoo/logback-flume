package me.wonwoo.sink;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import me.wonwoo.util.MapperUtils;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.conf.Configurables;

import java.util.Map;
import java.util.UUID;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class FlumeMongoSink extends AbstractChannelFlumeSink {

  private final String sinkName;
  private final MongoAttr mongoAttr;

  public FlumeMongoSink(String channelName, ChannelAttr channelAttr, String sinkName, MongoAttr mongoAttr, Channel channel) {
    super(channelName, channelAttr, channel);
    this.sinkName = sinkName;
    this.mongoAttr = mongoAttr;
  }

  @Override
  protected Sink createSink() {
    Sink sink = new MongoSink();
    sink.setName(this.sinkName + "-" + UUID.randomUUID());
    //TODO config
    Map<String, String> sinkParameters = MapperUtils.configMap(this.mongoAttr);
    Context sinkContext = new Context(sinkParameters);
    Configurables.configure(sink, sinkContext);
    return sink;
  }
}
