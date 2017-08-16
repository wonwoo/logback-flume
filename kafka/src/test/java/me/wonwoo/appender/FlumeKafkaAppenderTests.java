package me.wonwoo.appender;

import org.junit.Test;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.sink.FlumeKafkaSink;
import me.wonwoo.sink.KafkaAttr;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeKafkaAppenderTests {

  @Test
  public void createSink() {
    FlumeKafkaAppender flumeKafkaAppender = new FlumeKafkaAppender();
    flumeKafkaAppender.setChannelName("channelTest");
    flumeKafkaAppender.setSinkName("sinkTest");
    flumeKafkaAppender.setKafkaAttr(new KafkaAttr());
    flumeKafkaAppender.setChannelAttr(new ChannelAttr());
    FlumeKafkaSink sink = (FlumeKafkaSink) flumeKafkaAppender.createSink();
    assertThat(sink).isNotNull();
  }

//  @Test
//  public void appenderKafkaTest() {
//    logger.info("kafka1 sink test");
//  }
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