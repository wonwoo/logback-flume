package me.wonwoo.appender;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.FlumeSink;
import me.wonwoo.sink.FlumeRedisSink;
import me.wonwoo.sink.RedisAttr;
import org.apache.flume.Channel;
import org.apache.flume.channel.MemoryChannel;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisAppender extends AbstractFlumeAppender {

  private String sinkName;
  private String channelName;
  private ChannelAttr channelAttr;
  private RedisAttr redisAttr;
  private Channel channel;

  @Override
  protected FlumeSink createSink() {
    if(channel == null) {
      this.channel = new MemoryChannel();
    }
    return new FlumeRedisSink(this.channelName, this.channelAttr, this.sinkName, this.redisAttr, this.channel);
  }

  public void setChannelAttr(ChannelAttr channelAttr) {
    this.channelAttr = channelAttr;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public void setSinkName(String sinkName) {
    this.sinkName = sinkName;
  }

  public void setRedisAttr(RedisAttr redisAttr) {
    this.redisAttr = redisAttr;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }
}
