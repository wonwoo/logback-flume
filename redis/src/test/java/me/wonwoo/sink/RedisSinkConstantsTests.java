package me.wonwoo.sink;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoolee on 2017. 8. 17..
 */
public class RedisSinkConstantsTests {

  @Test
  public void constants() {
    assertThat(RedisSinkConstants.HOST).isEqualTo("host");
    assertThat(RedisSinkConstants.PORT).isEqualTo("port");
    assertThat(RedisSinkConstants.CHANNEL).isEqualTo("channel");
    assertThat(RedisSinkConstants.DEFAULT_HOST).isEqualTo("localhost");
    assertThat(RedisSinkConstants.DEFAULT_PORT).isEqualTo(6379);
    assertThat(RedisSinkConstants.DEFAULT_CHANNEL).isEqualTo("flume-channel");
  }

}