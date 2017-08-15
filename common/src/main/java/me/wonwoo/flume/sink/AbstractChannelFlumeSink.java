package me.wonwoo.flume.sink;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.util.MapperUtils;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.channel.file.FileChannel;
import org.apache.flume.conf.Configurables;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.Map;
import java.util.UUID;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public abstract class AbstractChannelFlumeSink extends AbstractFlumeSink {

  private final String channelName;
  private final ChannelAttr channelAttr;

  public AbstractChannelFlumeSink(String channelName, ChannelAttr channelAttr) {
    this.channelName = channelName;
    this.channelAttr = channelAttr;
  }

  @Override
  protected Channel createChannel() {
    Channel channel = new FileChannel();
    channel.setName(this.channelName + "-" + UUID.randomUUID());
    //채널 설정
    Map<String, String> channelParameters = MapperUtils.configMap(channelAttr);
    Context channelContext = new Context(channelParameters);

    Configurables.configure(channel, channelContext);
    return channel;
  }

}
