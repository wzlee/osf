package com.lvwang.osf;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lvwang.osf.dao.PostDAO;
import com.lvwang.osf.model.Post;
import com.lvwang.osf.service.UserService;

public class PostDAOTest {

	private static UserService userService;
	private static PostDAO postDao;
	
	
	@BeforeClass
	public static void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
		userService = (UserService) context.getBean("userService");
		postDao = (PostDAO) context.getBean("postDao");
	}
	
	@Test
	public void test() {
		
		//userService.register("kevin@gmail.com", "12345678", "12345678");
		
		Post post = new Post();
		post.setPost_author(1);
		post.setPost_title("title");
		post.setPost_content("content");
		post.setPost_excerpt("post_excerpt");
		post.setPost_status("publish");
		post.setComment_status("open");

		
		int id = postDao.save(post);
		System.out.println(id);
	}
}
