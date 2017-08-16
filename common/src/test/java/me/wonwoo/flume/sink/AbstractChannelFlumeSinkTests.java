package me.wonwoo.flume.sink;

import me.wonwoo.flume.channel.ChannelAttr;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractChannelFlumeSinkTests {

	private AbstractChannelFlumeSink flumeSink;

	@Before
	public void setup() {
		flumeSink = new AbstractChannelFlumeSink("test", new ChannelAttr(), new MemoryChannel()) {

			@Override
			protected Sink createSink() {
				return new NullSink();
			}

		};
	}

	@Test
	public void channel() {
		flumeSink.start();
		Sink sink = flumeSink.createSink();
		assertThat(sink.getLifecycleState().name()).isEqualTo("IDLE");
	}

	@After
	public void close() {
		if(flumeSink != null) {
			flumeSink.shutdown();
		}
	}

}