
package me.wonwoo.sink;

import java.util.Map;
import java.util.UUID;

import me.wonwoo.core.ConfigurationSink;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.conf.Configurables;
import org.apache.flume.sink.kafka.KafkaSink;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import me.wonwoo.util.MapperUtils;

/**
 * Created by wonwoo on 2016. 6. 7..
 */
public class FlumeKafkaSink extends AbstractChannelFlumeSink {

  private final String sinkName;
  private final KafkaAttr kafkaAttr;

  public FlumeKafkaSink(String sinkName, String channelName, KafkaAttr kafkaAttr, ChannelAttr channelAttr, Channel channel) {
    super(channelName, channelAttr, channel);
    this.sinkName = sinkName;
    this.kafkaAttr = kafkaAttr;
  }

  @Override
  protected ConfigurationSink createSink() {
    Sink sink = new KafkaSink();
    sink.setName(this.sinkName + "-" + UUID.randomUUID());
    return new ConfigurationSink(sink, kafkaAttr);
  }
}
