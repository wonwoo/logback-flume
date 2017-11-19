package me.wonwoo.appender;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.FlumeSink;
import me.wonwoo.sink.FlumeKafkaSink;
import me.wonwoo.sink.KafkaAttr;
import org.apache.flume.Channel;
import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.channel.file.FileChannel;


/**
 * Created by wonwoo on 2016. 6. 7..
 */
public class FlumeKafkaAppender extends AbstractFlumeAppender {

  private String sinkName;
  private String channelName;
  private KafkaAttr kafkaAttr;
  private ChannelAttr channelAttr;
  private Channel channel;

  public FlumeKafkaAppender() {
  }

  @Override
  protected FlumeSink createSink() {
    if (this.channel == null) {
      this.channel = new FileChannel();
    }
    return new FlumeKafkaSink(this.sinkName, this.channelName, this.kafkaAttr, this.channelAttr, this.channel);
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public void setChannelAttr(ChannelAttr channelAttr) {
    this.channelAttr = channelAttr;
  }

  public void setSinkName(String sinkName) {
    this.sinkName = sinkName;
  }

  public void setKafkaAttr(KafkaAttr kafkaAttr) {
    this.kafkaAttr = kafkaAttr;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }
}
