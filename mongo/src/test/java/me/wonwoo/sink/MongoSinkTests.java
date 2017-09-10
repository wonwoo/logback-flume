package me.wonwoo.sink;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import me.wonwoo.flume.channel.ChannelAttr;
import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.event.EventBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.util.Collections;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class MongoSinkTests {


  private MongodStarter starter;
  private FlumeMongoSink flumeMongoSink;

  @Test
  public void process() throws Exception {
    flumeMongoSink.processEvents(Collections.singletonList(EventBuilder.withBody("{\"foo\":\"bar\"}".getBytes())));
  }

  @Rule
  public final ExternalResource resource = new ExternalResource() {

    private MongodExecutable mongodExecutable = null;

    @Override
    protected void before() throws Throwable {
      starter = MongodStarter.getDefaultInstance();
      String bindIp = "localhost";
      int port = 27017;
      IMongodConfig mongodConfig = new MongodConfigBuilder()
          .version(Version.Main.PRODUCTION)
          .net(new Net(bindIp, port, Network.localhostIsIPv6()))
          .build();
      mongodExecutable = starter.prepare(mongodConfig);
      mongodExecutable.start();
      ChannelAttr channelAttr = new ChannelAttr();
      flumeMongoSink = new FlumeMongoSink("test", channelAttr, "sinkTest", new MongoAttr(), new MemoryChannel());
      flumeMongoSink.start();
    }

    @Override
    protected void after() {
      if (mongodExecutable != null) {
        mongodExecutable.stop();
      }
      if(flumeMongoSink != null) {
        flumeMongoSink.shutdown();
      }

    }
  };
}