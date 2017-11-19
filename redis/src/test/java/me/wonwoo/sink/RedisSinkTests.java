package me.wonwoo.sink;

import java.util.Collections;

import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.event.EventBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import me.wonwoo.flume.channel.ChannelAttr;
import redis.embedded.RedisServer;

public class RedisSinkTests {

	private RedisServer redisServer;
	private FlumeRedisSink flumeRedisSink;


	@Test
	public void process() throws Exception {
		flumeRedisSink.processEvents(EventBuilder.withBody("test".getBytes()));
	}

	@Rule
	public final ExternalResource resource = new ExternalResource() {

		@Override
		protected void before() throws Throwable {
			redisServer = new RedisServer();
			redisServer.start();
			ChannelAttr channelAttr = new ChannelAttr();
			flumeRedisSink = new FlumeRedisSink("test", channelAttr, "sinkTest", new RedisAttr(), new MemoryChannel());
			flumeRedisSink.start();
		}

		@Override
		protected void after() {
			if (redisServer != null) {
				redisServer.stop();
			}
			if(flumeRedisSink != null) {
				flumeRedisSink.shutdown();
			}

		}
	};

}