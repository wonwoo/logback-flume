package me.wonwoo.appender;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.FlumeSink;
import me.wonwoo.sink.FlumeMongoSink;
import me.wonwoo.sink.MongoAttr;
import org.apache.flume.Channel;
import org.apache.flume.channel.MemoryChannel;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class FlumeMongoAppender extends AbstractFlumeAppender {

  private String sinkName;
  private String channelName;
  private ChannelAttr channelAttr;
  private MongoAttr mongoAttr;
  private Channel channel;

  @Override
  protected FlumeSink createSink() {
    if(channel == null) {
      this.channel = new MemoryChannel();
    }
    return new FlumeMongoSink(this.channelName, this.channelAttr, this.sinkName, this.mongoAttr, this.channel);
  }

  public void setSinkName(String sinkName) {
    this.sinkName = sinkName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public void setChannelAttr(ChannelAttr channelAttr) {
    this.channelAttr = channelAttr;
  }

  public void setMongoAttr(MongoAttr mongoAttr) {
    this.mongoAttr = mongoAttr;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }


  @Override
  protected String[] excludePrefix() {
    return new String[] {"org.mongodb.driver"};
  }
}
