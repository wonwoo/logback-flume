package me.wonwoo.sink;


import me.wonwoo.flume.channel.ChannelAttr;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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




//  private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//  @Test
//  public void appenderKafkaError() {
//    try {
//      URL uri = new URL("urltest");
//      uri.openConnection();
//    } catch (MalformedURLException e) {
//      logger.error("url formed exception ", e);
//    } catch (IOException e) {
//      logger.error("error {} : ", e.toString());
//    }
//  }
}