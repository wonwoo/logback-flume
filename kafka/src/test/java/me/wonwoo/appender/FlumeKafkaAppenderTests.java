package me.wonwoo.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import me.wonwoo.encoding.DefaultFlumeMessageEncoder;
import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import me.wonwoo.layout.JsonLayout;
import me.wonwoo.sink.FlumeKafkaSink;
import me.wonwoo.sink.KafkaAttr;
import me.wonwoo.sink.NullSink;
import org.apache.flume.Channel;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeKafkaAppenderTests {

  @Test
  public void createSink() {
    FlumeKafkaAppender flumeKafkaAppender = new FlumeKafkaAppender();
    flumeKafkaAppender.setChannelName("channelTest");
    flumeKafkaAppender.setSinkName("sinkTest");
    flumeKafkaAppender.setChannel(new MemoryChannel());
    flumeKafkaAppender.setKafkaAttr(new KafkaAttr());
    flumeKafkaAppender.setChannelAttr(new ChannelAttr());
    FlumeKafkaSink sink = (FlumeKafkaSink) flumeKafkaAppender.createSink();
    assertThat(sink).isNotNull();
  }

  @Test
  public void append() {
    FlumeKafkaAppender flumeKafkaAppender = spy(FlumeKafkaAppender.class);
    JsonLayout jsonLayout = new JsonLayout();
    DefaultFlumeMessageEncoder<ILoggingEvent> layout = new DefaultFlumeMessageEncoder<>();
    layout.setLayout(jsonLayout);
    flumeKafkaAppender.setEncoder(layout);
    flumeKafkaAppender.setChannelName("channelTest");
    flumeKafkaAppender.setSinkName("sinkTest");
    flumeKafkaAppender.setKafkaAttr(new KafkaAttr());
    flumeKafkaAppender.setChannelAttr(new ChannelAttr());
    given(flumeKafkaAppender.createSink()).willReturn(new NullFlumeSink(null, null));
    Logger logger = LoggerFactory.getLogger(this.getClass());
    LoggingEvent loggingEvent = new LoggingEvent(
        this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
        "error message", new NullPointerException(), null);
    flumeKafkaAppender.start();
    flumeKafkaAppender.append(loggingEvent);
    flumeKafkaAppender.stop();
    verify(flumeKafkaAppender).createSink();

  }

  private static class NullFlumeSink extends AbstractChannelFlumeSink {
    public NullFlumeSink(String channelName, ChannelAttr channelAttr) {
      super(channelName, channelAttr, new MemoryChannel());
    }

    @Override
    protected Map<String, String> configureChannel(Channel channel) {
      return new HashMap<>();
    }

    @Override
    protected Sink createSink() {
      return new NullSink();
    }
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