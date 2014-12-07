package com.lvwang.osf.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lvwang.osf.service.UserService;

@Controller
@RequestMapping("/account")
public class Account {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(@RequestParam("user_name") String user_name,
					  @RequestParam("user_pwd") String user_pwd ) {
		
	}
	
	
	@RequestMapping("/")
	
}
