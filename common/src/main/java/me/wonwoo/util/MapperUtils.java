package me.wonwoo.util;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class MapperUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static Map<String, String> configMap(Object object) {
    return objectMapper.convertValue(object, new TypeReference<Map<String,String>>() {});
  }

}
