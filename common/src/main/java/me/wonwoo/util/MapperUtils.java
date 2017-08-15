package me.wonwoo.util;

import me.wonwoo.flume.channel.ChannelAttr;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.Map;

/**
 * Created by wonwoolee on 2017. 8. 15..
 */
public class MapperUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static Map<String, String> configMap(Object object) {
    return objectMapper.convertValue(object, new TypeReference<Map<String,String>>() {});
  }

}
