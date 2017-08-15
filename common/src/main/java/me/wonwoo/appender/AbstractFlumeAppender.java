package me.wonwoo.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

import static me.wonwoo.util.Constant.FLUME_PREFIX;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public abstract class AbstractFlumeAppender extends FlumeAppenderBase<ILoggingEvent> {

  @Override
  protected String doAppender(final ILoggingEvent event) {
    String loggerName = event.getLoggerName();

    if (loggerName.startsWith(FLUME_PREFIX)) {
      return null;
    }
    int current = event.getLevel().toInt();
    int mode = Level.toLevel(getMode()).toInt();
    if (mode > current) {
      return null;
    }
    return getEncoder().doEncode(event);
  }
}
