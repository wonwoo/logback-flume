package me.wonwoo.sink;

import lombok.Data;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
@Data
public class MongoAttr {
  private String host;
  private Integer port;
  private String username;
  private String password;
  private String database;
  private String collectionName;
}
