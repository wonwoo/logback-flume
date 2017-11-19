package me.wonwoo.core;

import me.wonwoo.flume.sink.NullConfig;
import me.wonwoo.flume.sink.NullSink;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ConfigurationSinkTests {

  @Test
  public void config() {
    NullConfig config = new NullConfig();
    config.setName("test");
    ConfigurationSink configurationSink =
        new ConfigurationSink(new NullSink(), config);
    assertThat(configurationSink.getConfig())
        .containsKeys("name")
        .containsValues("test");
  }


  @Test
  public void context() {
    NullConfig config = new NullConfig();
    config.setName("test");
    ConfigurationSink configurationSink =
        new ConfigurationSink(new NullSink(), config);
    Context context = configurationSink.context();
    assertThat(context.getString("name")).isEqualTo("test");
  }

  @Test
  public void configure() {
    NullConfig config = new NullConfig();
    config.setName("test");
    ConfigurationSink configurationSink =
        new ConfigurationSink(new NullSink(), config);

    Sink sink = configurationSink.configure();
    assertThat(sink).isInstanceOf(NullSink.class);
  }
}