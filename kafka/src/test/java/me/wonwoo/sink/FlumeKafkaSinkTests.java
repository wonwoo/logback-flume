package me.wonwoo.sink;

import me.wonwoo.core.ConfigurationSink;
import org.apache.flume.Sink;
import org.apache.flume.channel.MemoryChannel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.wonwoo.flume.channel.ChannelAttr;
import net.manub.embeddedkafka.EmbeddedKafka$;
import net.manub.embeddedkafka.EmbeddedKafkaConfig;

import static org.assertj.core.api.Assertions.assertThat;


public class FlumeKafkaSinkTests {

	@Before
	public void setup() {
		EmbeddedKafka$.MODULE$.start(EmbeddedKafkaConfig.defaultConfig());
	}

	@Test
	public void createSink() {
		KafkaAttr kafkaAttr = new KafkaAttr();
		kafkaAttr.setTopic("test");
		kafkaAttr.setBrokerList("localhost:9092");
		ChannelAttr channelAttr = new ChannelAttr();
		channelAttr.setDataDirs("/Users/wonwoolee/Desktop/flume");
		channelAttr.setCheckpointDir("/Users/wonwoolee/Desktop/flume/checkout");
		FlumeKafkaSink flumeKafkaSink = new FlumeKafkaSink("test", "channelTest", kafkaAttr, channelAttr, new MemoryChannel());
		ConfigurationSink sink = flumeKafkaSink.createSink();
		assertThat(sink.getSink().getName()).contains("test");
		assertThat(sink.getSink().getLifecycleState().name()).isEqualTo("IDLE");
	}

	@After
	public void close() {
		EmbeddedKafka$.MODULE$.stop();
	}

}