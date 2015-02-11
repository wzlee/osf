package com.lvwang.osf.control;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Post;
import com.lvwang.osf.service.PostService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("postService")
	private PostService postService;
	
	@RequestMapping("/{id}")
	public ModelAndView collection(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView();
		List<Post> posts = postService.findPostsOfUser(id);
		mav.addObject("posts", posts);
		
		
		mav.setViewName("user/index");
		return mav;
	}
}
