package me.wonwoo.flume.sink;

import java.util.Collections;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.event.EventBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static me.wonwoo.util.Constant.CHARSET;
import static org.apache.flume.lifecycle.LifecycleState.START;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFlumeSinkTests {

	private AbstractFlumeSink flumeSink;

	@Before
	public void setup() {
		flumeSink = new AbstractFlumeSink() {

			@Override
			protected Sink createSink() {
				return new NullSink();
			}

			@Override
			protected Channel createChannel() {
				MemoryChannel memoryChannel = new MemoryChannel();
				memoryChannel.setName("test");
				memoryChannel.configure(new Context());
				return memoryChannel;
			}
		};
	}

	@Test
	public void processEvents() throws Exception {
		flumeSink.start();
		flumeSink.processEvents(Collections.singletonList(EventBuilder.withBody("message", CHARSET)));
		assertThat(flumeSink.getChannel().getName()).isEqualTo("test");
		assertThat(flumeSink.getChannel().getLifecycleState()).isEqualTo(START);

	}

	@After
	public void close() {
		if(flumeSink != null) {
			flumeSink.shutdown();
		}
	}
}