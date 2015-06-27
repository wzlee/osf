package com.lvwang.osf.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/explore")
public class ExploreController {
	
	@RequestMapping("")
	public String explore(){
		return "explore";
	}
}
