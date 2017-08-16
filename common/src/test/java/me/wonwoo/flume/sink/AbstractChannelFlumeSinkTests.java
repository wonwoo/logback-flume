package me.wonwoo.flume.sink;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.wonwoo.flume.channel.ChannelAttr;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractChannelFlumeSinkTests {

	private AbstractChannelFlumeSink flumeSink;

	@Before
	public void setup() {
		flumeSink = new AbstractChannelFlumeSink("test", new ChannelAttr()) {

			@Override
			protected Sink createSink() {
				return new NullSink();
			}

			@Override
			protected Channel createChannel() {
				MemoryChannel memoryChannel = new MemoryChannel();
				memoryChannel.configure(new Context());
				return memoryChannel;
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