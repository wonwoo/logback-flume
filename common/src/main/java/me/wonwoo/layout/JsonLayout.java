/**
 * Copyright (C) 2016, The logback-contrib developers. All rights reserved.
 * <p>
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 * <p>
 * or (per the licensee's choosing)
 * <p>
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package me.wonwoo.layout;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;

/**
 * @author wonwoo
 */
public class JsonLayout extends JsonLayoutBase<ILoggingEvent> {

  private static final String TIMESTAMP_ATTR_NAME = "timestamp";
  private static final String LEVEL_ATTR_NAME = "level";
  private static final String THREAD_ATTR_NAME = "thread";
  private static final String MDC_ATTR_NAME = "mdc";
  private static final String LOGGER_ATTR_NAME = "logger";
  private static final String FORMATTED_MESSAGE_ATTR_NAME = "message";
  private static final String MESSAGE_ATTR_NAME = "raw-message";
  private static final String EXCEPTION_ATTR_NAME = "exception";
  private static final String CONTEXT_ATTR_NAME = "context";
  private static final String LINE_ATTR_NUMBER = "lineNumber";
  private static final String HOST_ATTR_NAME = "hostName";

  private boolean includeLevel;
  private boolean includeThreadName;
  private boolean includeMDC;
  private boolean includeLoggerName;
  private boolean includeFormattedMessage;
  private boolean includeMessage;
  private boolean includeException;
  private boolean includeContextName;
  private boolean includeHostName;
  private boolean includeLineNumber;
  private String hostName;

  private ThrowableHandlingConverter throwableProxyConverter;

  public JsonLayout() {
    super();
    this.includeLevel = true;
    this.includeThreadName = true;
    this.includeMDC = true;
    this.includeLoggerName = true;
    this.includeFormattedMessage = true;
    this.includeException = true;
    this.includeContextName = true;
    this.includeLineNumber = true;
    this.includeHostName = true;
    try {
      this.hostName = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException ignored) {
    }

    this.throwableProxyConverter = new ThrowableProxyConverter();
  }

  @Override
  public void start() {
    this.throwableProxyConverter.start();
    super.start();
  }

  @Override
  public void stop() {
    super.stop();
    this.throwableProxyConverter.stop();
  }

  @Override
  protected Map<String, Object> toJsonMap(ILoggingEvent event) {

    Map<String, Object> map = new LinkedHashMap<String, Object>();

    if (this.includeTimestamp) {
      long timestamp = event.getTimeStamp();
      String formatted = formatTimestamp(timestamp);
      if (formatted != null) {
        map.put(TIMESTAMP_ATTR_NAME, formatted);
      }
    }

    if (this.includeLevel) {
      Level level = event.getLevel();
      if (level != null) {
        String lvlString = String.valueOf(level);
        map.put(LEVEL_ATTR_NAME, lvlString);
      }
    }

    if (this.includeThreadName) {
      String threadName = event.getThreadName();
      if (threadName != null) {
        map.put(THREAD_ATTR_NAME, threadName);
      }
    }

    if (this.includeMDC) {
      Map<String, String> mdc = event.getMDCPropertyMap();
      if ((mdc != null) && !mdc.isEmpty()) {
        map.put(MDC_ATTR_NAME, mdc);
      }
    }

    if (this.includeLoggerName) {
      String loggerName = event.getLoggerName();
      if (loggerName != null) {
        map.put(LOGGER_ATTR_NAME, loggerName);
      }
    }

    if (this.includeFormattedMessage) {
      String msg = event.getFormattedMessage();
      if (msg != null) {
        map.put(FORMATTED_MESSAGE_ATTR_NAME, msg);
      }
    }

    if (this.includeMessage) {
      String msg = event.getMessage();
      if (msg != null) {
        map.put(MESSAGE_ATTR_NAME, msg);
      }
    }

    if (this.includeContextName) {
      String msg = event.getLoggerContextVO().getName();
      if (msg != null) {
        map.put(CONTEXT_ATTR_NAME, msg);
      }
    }

    if (this.includeLineNumber) {
      String number = lineNumber(event);
      map.put(LINE_ATTR_NUMBER, number);
    }

    if (this.includeHostName) {
      try {
        if(this.hostName == null){
          this.hostName = InetAddress.getLocalHost().getHostName();
        }
        map.put(HOST_ATTR_NAME, this.hostName);
      } catch (UnknownHostException ignored) {
      }
    }

    if (this.includeException) {
      IThrowableProxy throwableProxy = event.getThrowableProxy();
      if (throwableProxy != null) {
        String ex = throwableProxyConverter.convert(event);
        if (ex != null && !ex.equals("")) {
          map.put(EXCEPTION_ATTR_NAME, ex);
        }
      }
    }

    addCustomDataToJsonMap(map, event);
    return map;
  }


  /**
   * line number
   * @param le
   * @return
   */
  private String lineNumber(ILoggingEvent le) {
    StackTraceElement[] cda = le.getCallerData();
    if (cda != null && cda.length > 0) {
      return Integer.toString(cda[0].getLineNumber());
    } else {
      return CallerData.NA;
    }
  }

  /**
   * Override to add custom data to the produced JSON from the logging event.
   * Useful if you e.g. want to include the parameter array as a separate json attribute.
   *
   * @param map   the map for JSON serialization, populated with data corresponding to the
   *              configured attributes. Add new entries from the event to this map to have
   *              them included in the produced JSON.
   * @param event the logging event to extract data from.
   */
  protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
    // Nothing to do in default implementation
  }

  public boolean isIncludeLevel() {
    return includeLevel;
  }

  public void setIncludeLevel(boolean includeLevel) {
    this.includeLevel = includeLevel;
  }

  public boolean isIncludeLoggerName() {
    return includeLoggerName;
  }

  public void setIncludeLoggerName(boolean includeLoggerName) {
    this.includeLoggerName = includeLoggerName;
  }

  public boolean isIncludeFormattedMessage() {
    return includeFormattedMessage;
  }

  public void setIncludeFormattedMessage(boolean includeFormattedMessage) {
    this.includeFormattedMessage = includeFormattedMessage;
  }

  public boolean isIncludeMessage() {
    return includeMessage;
  }

  public void setIncludeMessage(boolean includeMessage) {
    this.includeMessage = includeMessage;
  }

  public boolean isIncludeMDC() {
    return includeMDC;
  }

  public void setIncludeMDC(boolean includeMDC) {
    this.includeMDC = includeMDC;
  }

  public boolean isIncludeThreadName() {
    return includeThreadName;
  }

  public void setIncludeThreadName(boolean includeThreadName) {
    this.includeThreadName = includeThreadName;
  }

  public boolean isIncludeException() {
    return includeException;
  }

  public void setIncludeException(boolean includeException) {
    this.includeException = includeException;
  }

  public boolean isIncludeContextName() {
    return includeContextName;
  }

  public void setIncludeContextName(boolean includeContextName) {
    this.includeContextName = includeContextName;
  }

  public ThrowableHandlingConverter getThrowableProxyConverter() {
    return throwableProxyConverter;
  }

  public void setThrowableProxyConverter(ThrowableHandlingConverter throwableProxyConverter) {
    this.throwableProxyConverter = throwableProxyConverter;
  }

  public void setIncludeLineNumber(boolean includeLineNumber) {
    this.includeLineNumber = includeLineNumber;
  }

  public boolean isIncludeLineNumber() {
    return this.includeLineNumber;
  }

  public void setIncludeHostName(boolean includeHostName) {
    this.includeHostName = includeHostName;
  }
  public boolean isIncludeHostName() {
    return this.includeHostName;
  }
}