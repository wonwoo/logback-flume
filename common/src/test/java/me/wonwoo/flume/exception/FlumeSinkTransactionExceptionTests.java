package me.wonwoo.flume.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlumeSinkTransactionExceptionTests {

	@Test
	public void flumeSinkTransactionException() {
		NullPointerException e = new NullPointerException();
		FlumeSinkTransactionException flumeSinkTransactionException
				= new FlumeSinkTransactionException("null", e);
		assertThat(flumeSinkTransactionException.getMessage()).isEqualTo("null");
		assertThat(flumeSinkTransactionException.getCause()).isEqualTo(e);
	}
}