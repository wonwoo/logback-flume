package me.wonwoo.appender;

import org.apache.flume.channel.file.FileChannel;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.FlumeSink;
import me.wonwoo.sink.FlumeRedisSink;
import me.wonwoo.sink.RedisAttr;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisAppender extends AbstractFlumeAppender {

  private String sinkName;
  private String channelName;
  private ChannelAttr channelAttr;
  private RedisAttr redisAttr;

  @Override
  protected FlumeSink createSink() {
    return new FlumeRedisSink(this.channelName, this.channelAttr, this.sinkName, this.redisAttr, new FileChannel());
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
}
