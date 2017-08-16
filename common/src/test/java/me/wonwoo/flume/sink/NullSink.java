package me.wonwoo.flume.sink;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

public class NullSink extends AbstractSink implements Configurable {

	@Override
	public Status process() throws EventDeliveryException {
		return Status.READY;
	}

	@Override
	public void configure(Context context) {
	}
}
