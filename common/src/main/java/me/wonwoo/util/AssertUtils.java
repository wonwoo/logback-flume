package me.wonwoo.util;

/**
 * Created by wonwoo on 2016. 6. 7..
 */
public class AssertUtils {

  private AssertUtils() {
    throw new UnsupportedOperationException();
  }

  public static <T> T assertNotNull(T value, String name) {
    if (value == null) {
      throw new NullPointerException(name);
    } else {
      return value;
    }
  }
}
