package me.wonwoo.sink;

import lombok.Data;

@Data
public class RedisAttr {
	private String host;
	private int port;
	private String topic;
	private String password;
}
