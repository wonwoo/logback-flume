package me.wonwoo.appender;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.sink.FlumeRedisSink;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisAppenderTests {

  @Test
  public void createSink() {
    FlumeRedisAppender flumeRedisAppender = new FlumeRedisAppender();
    flumeRedisAppender.setChannelName("channelTest");
    flumeRedisAppender.setSinkName("sinkTest");
    flumeRedisAppender.setChannelAttr(new ChannelAttr());
    FlumeRedisSink sink = (FlumeRedisSink) flumeRedisAppender.createSink();
    assertThat(sink).isNotNull();
  }

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void appenderRedisError() {
    try {
      URL uri = new URL("urltest");
      uri.openConnection();
    } catch (MalformedURLException e) {
      logger.error("url formed exception ", e);
    } catch (IOException e) {
      logger.error("error {} : ", e.toString());
    }
  }
}