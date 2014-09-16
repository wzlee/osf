package com.lvwang.osf.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomePage {

	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}
}
