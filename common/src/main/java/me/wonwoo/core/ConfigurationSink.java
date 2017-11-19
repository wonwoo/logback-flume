package me.wonwoo.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flume.Context;
import org.apache.flume.Sink;
import org.apache.flume.conf.Configurables;

import java.util.Map;

public class ConfigurationSink {

  private final Sink sink;
  private final Map<String, String> config;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public ConfigurationSink(Sink sink, Map<String, String> config) {
    this.sink = sink;
    this.config = config;
  }

  public ConfigurationSink(Sink sink, Object config) {
    this.sink = sink;
    this.config = objectMapper.convertValue(config, new TypeReference<Map<String, String>>() {
    });
  }

  public Context context() {
    return new Context(config);
  }

  public Sink getSink() {
    return sink;
  }

  public Map<String, String> getConfig() {
    return config;
  }

  public Sink configure() {
    Configurables.configure(sink, context());
    return sink;
  }
}
