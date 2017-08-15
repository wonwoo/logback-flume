
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
import org.apache.flume.sink.kafka.KafkaSink;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.Map;
import java.util.UUID;

/**
 * Created by wonwoo on 2016. 6. 7..
 */
public class FlumeKafkaSink extends AbstractChannelFlumeSink {

  private final String sinkName;
  private final KafkaAttr kafkaAttr;

  public FlumeKafkaSink(String sinkName, String channelName, KafkaAttr kafkaAttr, ChannelAttr channelAttr) {
    super(channelName, channelAttr);
    this.sinkName = sinkName;
    this.kafkaAttr = kafkaAttr;
  }

  @Override
  protected Sink createSink() {
    Sink sink = new KafkaSink();
    sink.setName(this.sinkName + "-" + UUID.randomUUID());
    //카프카 설정
    Map<String, String> sinkParameters = MapperUtils.configMap(this.kafkaAttr);
    Context sinkContext = new Context(sinkParameters);
    Configurables.configure(sink, sinkContext);
    return sink;
  }
}
