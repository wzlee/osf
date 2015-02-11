package com.lvwang.osf.control;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvwang.osf.model.User;
import com.lvwang.osf.service.UserService;
import com.lvwang.osf.util.Property;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "account/login";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,
					    @RequestParam("password") String password,
					    HttpSession session) {
		String status = userService.login(email, password);
		if(Property.SUCCESS_ACCOUNT_LOGIN.equals(status)) {
			User user = userService.findByEmail(email);
			session.setAttribute("user", user);			
		}
		return status;		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "account/register";
	}
	
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public Map<String, String> register(@RequestParam("email") String email,
						 @RequestParam("password") String password,
						 @RequestParam("cfmPwd") String cfmPwd) {
		System.out.println("resister....");
		Map<String, String> map = new HashMap<String, String>();
		String status = userService.register(email, password, cfmPwd, map);
		if(Property.SUCCESS_ACCOUNT_REG.equals(status)){
			
		} else if(Property.ERROR_ACCOUNT_INACTIVE.equals(status)) {
		}
		map.put("status", status);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/activation/{key}")
	public String activation(@PathVariable("key") String key) {
		String status = null;
		try {
			status = userService.activateUser(URLDecoder.decode(key, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return status;		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "account/login";
	}
}
