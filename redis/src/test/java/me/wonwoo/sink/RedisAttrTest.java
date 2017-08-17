package me.wonwoo.sink;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RedisAttrTest {

	@Test
	public void redisAttr() {
		RedisAttr redisAttr = new RedisAttr();
		redisAttr.setHost("localhost");
		redisAttr.setPort(6379);
		redisAttr.setPassword("test");
		redisAttr.setTopic("test");
		assertThat(redisAttr.getHost()).isEqualTo("localhost");
		assertThat(redisAttr.getPort()).isEqualTo(6379);
		assertThat(redisAttr.getPassword()).isEqualTo("test");
		assertThat(redisAttr.getTopic()).isEqualTo("test");
	}
}
