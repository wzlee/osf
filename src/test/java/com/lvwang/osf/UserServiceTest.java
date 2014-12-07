package com.lvwang.osf;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lvwang.osf.model.User;
import com.lvwang.osf.service.UserService;

public class UserServiceTest {
	
	private static UserService userService;
	
	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
		userService = (UserService) context.getBean("userService");
		
	}
	
	@Test
	public void testfindByEmail() {
		//User user = userService.findByEmail("aif@gmail.com");
		//System.out.println(user.getUser_pwd());
		//System.out.println(userService.register("kevin@gmail", "12345678", "12345678"));
		System.out.println(userService.login("keverfin@gmail", "123456789"));
	}
	

}
