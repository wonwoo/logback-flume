package me.wonwoo.flume.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogBackAppenderExceptionTests {
	@Test
	public void logBackAppenderException() {
		NullPointerException e = new NullPointerException();
		LogBackAppenderException logBackAppenderException
				= new LogBackAppenderException("null", e);
		assertThat(logBackAppenderException.getMessage()).isEqualTo("null");
		assertThat(logBackAppenderException.getCause()).isEqualTo(e);
	}
}