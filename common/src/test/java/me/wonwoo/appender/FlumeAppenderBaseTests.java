package me.wonwoo.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import me.wonwoo.encoding.DefaultFlumeMessageEncoder;
import me.wonwoo.flume.channel.ChannelAttr;
import me.wonwoo.flume.sink.AbstractChannelFlumeSink;
import me.wonwoo.flume.sink.NullSink;
import me.wonwoo.layout.JsonLayout;
import org.apache.flume.Channel;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by wonwoolee on 2017. 8. 17..
 */
public class FlumeAppenderBaseTests {
  @Test
  public void append() {
    FlumeAppenderBase flumeAppenderBase = spy(FlumeAppenderBase.class);
    JsonLayout jsonLayout = new JsonLayout();
    DefaultFlumeMessageEncoder<ILoggingEvent> layout = new DefaultFlumeMessageEncoder<>();
    layout.setLayout(jsonLayout);
    flumeAppenderBase.setEncoder(layout);
    given(flumeAppenderBase.createSink()).willReturn(new NullFlumeSink(null, null));
    Logger logger = LoggerFactory.getLogger(this.getClass());
    LoggingEvent loggingEvent = new LoggingEvent(
        this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
        "error message", new NullPointerException(), null);
    flumeAppenderBase.start();
    flumeAppenderBase.append(loggingEvent);
    flumeAppenderBase.stop();
    verify(flumeAppenderBase).createSink();

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
}