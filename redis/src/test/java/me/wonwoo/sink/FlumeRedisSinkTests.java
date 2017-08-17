package me.wonwoo.sink;

import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.Test;

import me.wonwoo.flume.channel.ChannelAttr;

import static org.assertj.core.api.Assertions.assertThat;

public class FlumeRedisSinkTests {

	@Test
	public void createSink() {
		ChannelAttr channelAttr = new ChannelAttr();
		channelAttr.setDataDirs("./test");
		channelAttr.setCheckpointDir("./test/");
		FlumeRedisSink flumeRedisSink = new FlumeRedisSink("test", channelAttr, "sinkTest", new MemoryChannel());
		Sink sink = flumeRedisSink.createSink();
		assertThat(sink.getName()).contains("sinkTest");
		assertThat(sink.getLifecycleState().name()).contains("IDLE");
	}

}