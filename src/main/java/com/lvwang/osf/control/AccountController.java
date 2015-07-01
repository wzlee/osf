package com.lvwang.osf.control;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lvwang.osf.model.User;
import com.lvwang.osf.service.MailService;
import com.lvwang.osf.service.UserService;
import com.lvwang.osf.util.Property;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("mailService")
	private MailService mailService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "account/login";
	}
	
	@RequestMapping(value="/setting/info")
	public String settingInfo(HttpSession session){
		return "account/setting/info";
	}
	
	@RequestMapping(value="/setting/avatar")
	public String settingAvatar(HttpSession session){
		return "account/setting/avatar";
	}
	
	@RequestMapping(value="/setting/security")
	public String settingSecurity(HttpSession session){
		return "account/setting/security";
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,
					    @RequestParam("password") String password,
					    HttpSession session) {
		/*
		String status = userService.login(email, password);
		if(Property.SUCCESS_ACCOUNT_LOGIN.equals(status)) {
			User user = userService.findByEmail(email);
			session.setAttribute("user", user);			
		}
		*/
		Map<String, Object> ret = userService.login(email, password);
		String status = (String) ret.get("status");
		if(Property.SUCCESS_ACCOUNT_LOGIN.equals(status)) {
			session.setAttribute("user", (User)ret.get("user"));			
		}
		return status;		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "account/register";
	}
	
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public Map<String, String> register(@RequestParam("username") String username,
										@RequestParam("email") String email,
						 				@RequestParam("password") String password,
						 				@RequestParam("cfmPwd") String cfmPwd) {
		System.out.println("resister....");
		Map<String, String> map = new HashMap<String, String>();
		String status = userService.register(username, email, password, cfmPwd, map);
		if(Property.SUCCESS_ACCOUNT_REG.equals(status)){
			mailService.sendMail(email, "OSF 激活", "hello");
		} 
		map.put("status", status);
		return map;
	}
	
	@RequestMapping("/activation/mail/send")
	public ModelAndView actication(@RequestParam("email") String email) {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("account/activation");
		User user = userService.findByEmail(email);
		if(user != null) {
			if(user.getUser_status() == UserService.STATUS_USER_NORMAL) {
				mav.setViewName("/account/login");
			} else if(user.getUser_status() == UserService.STATUS_USER_INACTIVE) {
				
			}
			
			//TO-DO
			//send email
			mav.addObject("email", email);
		}
		return mav;
	}
	
	@RequestMapping("/activation/mail/resend")
	public String acticationMailResend(@RequestParam("email") String email) {
		return "account/activation";
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
	
	@RequestMapping("/completeinfo")
	public void completeUserInfo(HttpSession session) {
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "account/login";
	}
}
