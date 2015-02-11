package com.lvwang.osf;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ControllerTest {
	
	@Test
	public void test() {
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String, String> user = new LinkedMultiValueMap<String, String>();
		user.add("email", "kevin@gmail.com");
		user.add("password", "123456");
		rest.postForLocation("http://localhost:8080/com.lvwang.osf/account/login", user);
		
	}
	
}
