package me.wonwoo.sink;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KafkaAttrTests {

	@Test
	public void kafkaAttr() {
		KafkaAttr kafkaAttr = new KafkaAttr();
		kafkaAttr.setBatchSize("1000");
		kafkaAttr.setBrokerList("localhost:9200");
		kafkaAttr.setTopic("test");
		kafkaAttr.setRequiredAcks("true");
		assertThat(kafkaAttr.getBatchSize()).isEqualTo("1000");
		assertThat(kafkaAttr.getBrokerList()).isEqualTo("localhost:9200");
		assertThat(kafkaAttr.getTopic()).isEqualTo("test");
		assertThat(kafkaAttr.getRequiredAcks()).isEqualTo("true");
	}

}