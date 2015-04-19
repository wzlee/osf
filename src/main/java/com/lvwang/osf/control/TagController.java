package com.lvwang.osf.control;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.Event;
import com.lvwang.osf.service.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {

	@Autowired
	@Qualifier("tagService")
	private TagService tagService;
	
	@RequestMapping("/{tag}")
	public ModelAndView getFeedsWithTag(@PathVariable("tag") String tag) {
		try {
			tag = new String(tag.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tag/index");
		List<Event> feeds = tagService.getWithTag(tag);
		mav.addObject("feeds", feeds);
		return mav;
	}
}
