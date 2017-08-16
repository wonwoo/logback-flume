package me.wonwoo.encoding;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lombok.Data;
import me.wonwoo.layout.JsonLayoutBase;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultFlumeMessageEncoderTests {

	@Test
	public void doEncode() {
		DefaultFlumeMessageEncoder<Account> accountFlumeMessageEncoder = new DefaultFlumeMessageEncoder<>();
		accountFlumeMessageEncoder.setLayout(new JsonLayoutBase<Account>() {
			@Override
			protected Map<String, Object> toJsonMap(Account account) {
				Map<String,Object> map = new HashMap<>();
				map.put("name", account.getName());
				return map;
			}
		});


		Account account = new Account();
		account.setName("wonwoo");
		String result = accountFlumeMessageEncoder.doEncode(account);
		assertThat(result).isEqualTo("{name=wonwoo}");

	}

	@Data
	static class Account {
		private String name;
	}

}