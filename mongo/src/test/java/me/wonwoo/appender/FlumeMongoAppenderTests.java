package me.wonwoo.appender;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.sink.FlumeMongoSink;
import me.wonwoo.sink.MongoAttr;
import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class FlumeMongoAppenderTests {

  @Test
  public void createSink() {
    FlumeMongoAppender flumeMongoAppender = new FlumeMongoAppender();
    flumeMongoAppender.setChannelName("channelTest");
    flumeMongoAppender.setSinkName("sinkTest");
    flumeMongoAppender.setChannelAttr(new ChannelAttr());
    flumeMongoAppender.setChannel(new MemoryChannel());
    MongoAttr mongoAttr = new MongoAttr();
    mongoAttr.setHost("localhost");
    mongoAttr.setPort(6379);
    mongoAttr.setUsername("wonwoo");
    mongoAttr.setPassword("test");
    mongoAttr.setDatabase("flume");
    mongoAttr.setCollectionName("logs");
    flumeMongoAppender.setMongoAttr(mongoAttr);
    FlumeMongoSink sink = (FlumeMongoSink) flumeMongoAppender.createSink();
    assertThat(sink).isNotNull();
  }

}