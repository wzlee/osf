package com.lvwang.osf.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Tag;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.InterestService;
import com.lvwang.osf.service.TagService;

@Controller
@RequestMapping("/explore")
public class ExploreController {
	
	@Autowired
	@Qualifier("interestService")
	private InterestService interestService;
	
	@Autowired
	@Qualifier("tagService")
	private TagService tagService;
	
	@RequestMapping("")
	public ModelAndView explore(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("explore");
		
		User user = (User) session.getAttribute("user");
		
		List<Tag> tags_recommend = tagService.getRecommendTags(user==null?0:user.getId());
		mav.addObject("tags", tags_recommend);
		mav.addObject("isInterests", interestService.hasInterestInTags(user==null?0:user.getId(), tags_recommend));
		return mav;
	}
}
