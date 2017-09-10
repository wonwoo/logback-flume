package me.wonwoo.sink;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class MongoAttrTests {

  @Test
  public void mongoAttr() {
    MongoAttr mongoAttr = new MongoAttr();
    mongoAttr.setHost("localhost");
    mongoAttr.setPort(6379);
    mongoAttr.setUsername("wonwoo");
    mongoAttr.setPassword("test");
    mongoAttr.setDatabase("flume");
    mongoAttr.setCollectionName("logs");
    assertThat(mongoAttr.getHost()).isEqualTo("localhost");
    assertThat(mongoAttr.getPort()).isEqualTo(6379);
    assertThat(mongoAttr.getUsername()).isEqualTo("wonwoo");
    assertThat(mongoAttr.getPassword()).isEqualTo("test");
    assertThat(mongoAttr.getDatabase()).isEqualTo("flume");
    assertThat(mongoAttr.getCollectionName()).isEqualTo("logs");
  }
}