package me.wonwoo.appender;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import me.wonwoo.encoding.DefaultFlumeMessageEncoder;
import me.wonwoo.flume.sink.FlumeSink;
import me.wonwoo.layout.JacksonJsonFormatter;
import me.wonwoo.layout.JsonLayout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AbstractFlumeAppenderTests {

	@Test
	public void doAppender() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		LoggingEvent loggingEvent = new LoggingEvent(
				this.getClass().getName(), (ch.qos.logback.classic.Logger) logger, Level.DEBUG,
				"error message", new NullPointerException(), null);

		AbstractFlumeAppender appender = new AbstractFlumeAppender() {
			@Override
			protected FlumeSink createSink() {
				return mock(FlumeSink.class);
			}
		};
		DefaultFlumeMessageEncoder<ILoggingEvent> layout = new DefaultFlumeMessageEncoder<>();
		JsonLayout jsonLayout = new JsonLayout();
		jsonLayout.setJsonFormatter(new JacksonJsonFormatter());
		layout.setLayout(jsonLayout);
		appender.setEncoder(layout);
		String s = appender.doAppender(loggingEvent);
		assertThat(JsonPath.parse(s).read("level")).isEqualTo("DEBUG");
		assertThat(JsonPath.parse(s).read("thread")).isEqualTo("main");
		assertThat(JsonPath.parse(s).read("logger")).isEqualTo("me.wonwoo.appender.AbstractFlumeAppenderTests");
		assertThat(JsonPath.parse(s).read("message")).isEqualTo("error message");
		assertThat(JsonPath.parse(s).read("context")).isEqualTo("default");
		assertThat(JsonPath.parse(s).read("lineNumber")).isEqualTo("-2");
		assertThat((String)JsonPath.parse(s).read("exception")).contains("java.lang.NullPointerException: null");
	}
}