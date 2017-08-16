package me.wonwoo.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertUtilsTests {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void assertNotNullIsNull() {
		exception.expect(NullPointerException.class);
		AssertUtils.assertNotNull(null, "name");
	}

	@Test
	public void assertNotNull() {
		String name = AssertUtils.assertNotNull("wonwoo", "name");
		assertThat(name).isEqualTo("wonwoo");
	}
}