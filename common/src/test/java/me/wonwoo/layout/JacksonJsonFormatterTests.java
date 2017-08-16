package me.wonwoo.layout;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jayway.jsonpath.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonJsonFormatterTests {

	@Test
	public void toJsonString() throws Exception {
		JsonFormatter jsonFormatter = new JacksonJsonFormatter();
		Map<String,Object> p = new HashMap<>();
		p.put("name", "wonwoo");
		String s = jsonFormatter.toJsonString(p);
		assertThat(JsonPath.parse(s).read("name")).isEqualTo("wonwoo");
	}

	@Test
	public void toJsonStringPretty() throws Exception {
		JacksonJsonFormatter jsonFormatter = new JacksonJsonFormatter();
		jsonFormatter.setPrettyPrint(true);
		Map<String,Object> p = new HashMap<>();
		p.put("name", "wonwoo");
		String s = jsonFormatter.toJsonString(p);
		assertThat(JsonPath.parse(s).read("name")).isEqualTo("wonwoo");
	}
}