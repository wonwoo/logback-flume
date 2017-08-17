package me.wonwoo.flume.sink;

import java.util.Map;

import org.apache.flume.Channel;
import org.apache.flume.channel.file.FileChannel;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.util.MapperUtils;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public abstract class AbstractChannelFlumeSink extends AbstractFlumeSink {

  private final String channelName;
  private final ChannelAttr channelAttr;

  public AbstractChannelFlumeSink(String channelName, ChannelAttr channelAttr, Channel channel) {
    super(channel);
    this.channelName = channelName;
    this.channelAttr = channelAttr;
  }

  public AbstractChannelFlumeSink(String channelName, ChannelAttr channelAttr) {
    this(channelName, channelAttr, new FileChannel());
  }

  @Override
  protected Map<String,String> configureChannel(Channel channel) {
    channel.setName(this.channelName);
    //채널 설정
    return MapperUtils.configMap(channelAttr);

  }
}
