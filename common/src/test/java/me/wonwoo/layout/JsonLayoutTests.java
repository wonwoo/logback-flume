package me.wonwoo.layout;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonLayoutTests {

	@Test
	public void get() {
		JsonLayout jsonLayout = new JsonLayout();
		assertThat(jsonLayout.isIncludeContextName()).isTrue();
		assertThat(jsonLayout.isIncludeException()).isTrue();
		assertThat(jsonLayout.isIncludeFormattedMessage()).isTrue();
		assertThat(jsonLayout.isIncludeHostName()).isTrue();
		assertThat(jsonLayout.isIncludeLevel()).isTrue();
		assertThat(jsonLayout.isIncludeLineNumber()).isTrue();
		assertThat(jsonLayout.isIncludeLoggerName()).isTrue();
		assertThat(jsonLayout.isIncludeMDC()).isTrue();
		assertThat(jsonLayout.isIncludeMessage()).isFalse();
		assertThat(jsonLayout.isIncludeThreadName()).isTrue();
		assertThat(jsonLayout.isIncludeTimestamp()).isTrue();
	}

	@Test
	public void toJsonMap() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExclude() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeLevel(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isNull();
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExcludeThreadName() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeThreadName(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isNull();
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExcludeLoggerName() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeLoggerName(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isNull();
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExcludeFormattedMessage() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeFormattedMessage(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isNull();
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}


	@Test
	public void toJsonMapLevelExcludeContextName() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeContextName(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isNull();
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExcludeLineNumber() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeLineNumber(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isNull();
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExcludeHostName() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeHostName(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNull();
		assertThat((String)map.get("exception")).contains("java.lang.NullPointerException: null");
	}

	@Test
	public void toJsonMapLevelExcludeException() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setIncludeException(false);
		Map<String, Object> map = jsonLayout.toJsonMap(loggingEvent);
		assertThat(map.get("timestamp")).isNotNull();
		assertThat(map.get("level")).isEqualTo("DEBUG");
		assertThat(map.get("thread")).isEqualTo("main");
		assertThat(map.get("logger")).isEqualTo("me.wonwoo.layout.JsonLayoutTests");
		assertThat(map.get("message")).isEqualTo("error message");
		assertThat(map.get("context")).isEqualTo("default");
		assertThat(map.get("lineNumber")).isEqualTo("-2");
		assertThat(map.get("hostName")).isNotNull();
		assertThat((String)map.get("exception")).isNull();
	}
}
