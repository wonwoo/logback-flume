package me.wonwoo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;

import lombok.Data;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperUtilsTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void configMap() {
		User user = new User();
		user.setName("wonwoo");
		Map<String, String> stringStringMap = MapperUtils.configMap(user);
		assertThat(stringStringMap.get("name")).isEqualTo("wonwoo");
	}

	@Test
	public void privateConstructor() throws Exception {
		exception.expect(InvocationTargetException.class);
		Constructor<MapperUtils> constructor = MapperUtils.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	@Data
	static class User {
		private String name;
	}
}