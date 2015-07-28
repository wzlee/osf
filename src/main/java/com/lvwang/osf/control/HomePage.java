package com.lvwang.osf.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.EventService;
import com.lvwang.osf.service.FeedService;
import com.lvwang.osf.service.FollowService;
import com.lvwang.osf.service.UserService;
import com.lvwang.osf.util.Dic;
import com.lvwang.osf.util.Property;


@Controller
public class HomePage {

	@Autowired
	@Qualifier("eventService")
	private EventService eventService;
	
	@Autowired
	@Qualifier("feedService")
	private FeedService feedService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("followService")
	private FollowService followService;
	
	@RequestMapping("/")
	public ModelAndView showHomePage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return mav;
		}
		mav.addObject("counter", userService.getCounterOfFollowAndShortPost(user.getId()));
		List<Event> feeds = feedService.getFeeds(user.getId());
		mav.addObject("feeds", feeds);
		
		List<User> rec_users = userService.getRecommendUsers(user==null?0:user.getId(), 4);
		mav.addObject("isFollowings", followService.isFollowing(user==null?0:user.getId(), rec_users));
		mav.addObject("popusers", rec_users);
		
		mav.addObject("dic", new Dic());
		return mav;
		
	}
	
	@RequestMapping("/popup_usercard/{user_id}")
	public ModelAndView getPopupUserCard(@PathVariable("user_id") String user_id){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("popup_usercard");
		User user = userService.findById(Integer.valueOf(user_id));
		if(user != null) {
			mav.addObject("u", user);
			mav.addObject("counter", userService.getCounterOfFollowAndShortPost(Integer.valueOf(user_id)));
		}
		
		return mav;
	}

	@RequestMapping("/page/{num}")
	public ModelAndView nextPage(@PathVariable("num") String num_str, HttpSession session) {
		System.out.println(num_str);
		
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return null;
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("nextpage");
		
		int num = Integer.parseInt(num_str);
		List<Event> feeds = feedService.getFeedsOfPage(user.getId(), num);
		mav.addObject("feeds", feeds);
		mav.addObject("dic", new Dic());
		return mav;
	}
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	
	
	
	
	
}
