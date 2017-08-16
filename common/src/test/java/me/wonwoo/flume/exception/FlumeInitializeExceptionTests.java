package me.wonwoo.flume.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlumeInitializeExceptionTests {


	@Test
	public void flumeInitializeException() {
		NullPointerException e = new NullPointerException();
		FlumeInitializeException flumeInitializeException
				= new FlumeInitializeException("null", e);
		assertThat(flumeInitializeException.getMessage()).isEqualTo("null");
		assertThat(flumeInitializeException.getCause()).isEqualTo(e);
	}
}