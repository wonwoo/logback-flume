package me.wonwoo.sink;


import me.wonwoo.flume.channel.ChannelAttr;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class FlumeMongoSinkTests {

  @Test
  public void createSink() {
    ChannelAttr channelAttr = new ChannelAttr();
    channelAttr.setDataDirs("./test");
    channelAttr.setCheckpointDir("./test/");
    FlumeMongoSink flumeRedisSink = new FlumeMongoSink("test", channelAttr, "sinkTest",new MongoAttr(),  new MemoryChannel());
    Sink sink = flumeRedisSink.createSink();
    assertThat(sink.getName()).contains("sinkTest");
    assertThat(sink.getLifecycleState().name()).contains("IDLE");
  }

}