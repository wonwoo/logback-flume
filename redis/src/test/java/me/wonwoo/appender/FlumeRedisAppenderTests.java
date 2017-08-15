package me.wonwoo.appender;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class FlumeRedisAppenderTests {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void appenderRedisError() {
    try {
      URL uri = new URL("urltest");
      uri.openConnection();
    } catch (MalformedURLException e) {
      logger.error("url formed exception ", e);
    } catch (IOException e) {
      logger.error("error {} : ", e.toString());
    }
  }
}