package com.lvwang.osf;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lvwang.osf.dao.UserDAO;
import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.FeedService;
import com.lvwang.osf.service.MailService;
import com.lvwang.osf.service.UserService;
import com.lvwang.osf.util.CipherUtil;

public class UserServiceTest {
	
	private static UserService userService;
	private static UserDAO userDao;
	private static MailService mailService;
	private static FeedService feedService;
	
	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
		//userDao = (UserDAO) context.getBean("userDao");
		//userService = (UserService) context.getBean("userService");
		//mailService = (MailService) context.getBean("mailService");
		feedService = (FeedService) context.getBean("feedService");
	}
	
	
	@Test
	public void test() {
		//feedService.push(9, 1);
		List<Event> events = feedService.getFeeds(1);
		for(Event event : events) {
			System.out.println(event.getTitle());
		}
	}
	
	
	//@Test
	public void testfindByEmail() {
		//User user = userService.findByEmail("aif@gmail.com");
		//sSystem.out.println(user.getUser_pwd());
		//System.out.println(userService.register("ooooihrt@gmail", "123456fwe78#_", "123456fwe78#_"));
		//System.out.println(userService.activateUser("MzHFXTcPGACuiMyM0zmecg=="));
		//System.out.println(userService.login("keverfin@gmail", "123456789"));
		
		/*
		List<User> users = userDao.getUsersByIDs(new int[]{2,3});
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			System.out.print(user.getUser_email());
		}
		*/
		//System.out.println("|"+CipherUtil.generateActivationUrl("kevinsdfefwe@gmail", "12332423")+"|");
		mailService.sendMail("lvwangbeta@gmail.com", "hehe", "hehe");
		
	}
	

}
