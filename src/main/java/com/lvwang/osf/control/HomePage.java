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
	
	@RequestMapping("/")
	public ModelAndView showHomePage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return mav;
		}
		
		List<Event> feeds = feedService.getFeeds(user.getId());
		mav.addObject("feeds", feeds);
		mav.addObject("dic", new Dic());
		mav.addObject("imgBaseUrl", Property.IMG_BASE_URL);
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
		mav.addObject("imgBaseUrl", Property.IMG_BASE_URL);
		return mav;
	}
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	
	
	
	
	
}
