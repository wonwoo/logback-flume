package me.wonwoo.flume.sink;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
		flumeSink = new AbstractFlumeSink(new MemoryChannel()) {

			@Override
			protected Map<String, String> configureChannel(Channel channel) {
				channel.setName("test");
				return new HashMap<>();
			}

			@Override
			protected Sink createSink() {
				return new NullSink();
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