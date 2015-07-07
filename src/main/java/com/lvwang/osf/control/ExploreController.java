package com.lvwang.osf.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.Tag;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.FeedService;
import com.lvwang.osf.service.InterestService;
import com.lvwang.osf.service.TagService;
import com.lvwang.osf.service.UserService;
import com.lvwang.osf.util.Property;

@Controller
@RequestMapping("/explore")
public class ExploreController {
	
	@Autowired
	@Qualifier("interestService")
	private InterestService interestService;
	
	@Autowired
	@Qualifier("tagService")
	private TagService tagService;
	
	@Autowired
	@Qualifier("feedService")
	private FeedService feedService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping("")
	public ModelAndView explore(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("explore");
		
		User user = (User) session.getAttribute("user");
		
		List<Tag> tags_recommend = tagService.getRecommendTags(user==null?0:user.getId());
		mav.addObject("tags", tags_recommend);
		mav.addObject("isInterests", interestService.hasInterestInTags(user==null?0:user.getId(), tags_recommend));
		
		List<User> rec_users = userService.getRecommendUsers(4);
		Map<User, List<Event>> feeds = new HashMap<User, List<Event>>();
		for(User rec_user: rec_users){
			feeds.put(rec_user, feedService.getFeeds(rec_user.getId(), 5));
		}
		mav.addObject("feeds", feeds);
		mav.addObject("imgBaseUrl", Property.IMG_BASE_URL);
		return mav;
	}
}
