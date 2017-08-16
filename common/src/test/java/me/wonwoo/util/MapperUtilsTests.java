package me.wonwoo.util;

import java.util.Map;

import org.junit.Test;

import lombok.Data;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperUtilsTests {

	@Test
	public void configMap() {
		User user = new User();
		user.setName("wonwoo");
		Map<String, String> stringStringMap = MapperUtils.configMap(user);
		assertThat(stringStringMap.get("name")).isEqualTo("wonwoo");
	}

	@Data
	static class User {
		private String name;
	}
}