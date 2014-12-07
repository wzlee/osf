package com.lvwang.osf.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.UserDAO;
import com.lvwang.osf.model.User;
import com.lvwang.osf.util.CipherUtil;
import com.lvwang.osf.util.Property;

@Service
public class UserService {


	public static final int USERNAME_MAXLEN = 20;
		
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;
		
	private boolean ValidateEmail(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException e) {
			result = false;
		}
		return result;
	}
	
	public String confirmPwd(String user_name, String user_pwd) {
		if(user_pwd == null || user_name.length() == 0)
			return Property.ERROR_EMPTY_PWD;
		String pwd = userDao.getPwdByUsername(user_name);
		if(pwd.equals(user_pwd)) 
			return null;
		else
			return Property.ERROR_PWD_DIFF;
			
	}
	
	public User findByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	
	public User findByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
	
	public User findById(int id) {
		return userDao.getUserByID(id);
	}
	
	public String login(String email, String password) {
		//1 empty check
		if(email == null || email.length() <= 0)
			return Property.ERROR_EMPTY_EMAIL;
		if(password == null || password.length() <= 0)
			return Property.ERROR_EMPTY_PWD;
		
		//2 ValidateEmail 
		if(!ValidateEmail(email))
			return Property.ERROR_EMAIL_FORMAT;	

		//3 email exist?
		User user = findByEmail(email);
		if(user == null)
			return Property.ERROR_USERNAME_NOTEXIST;
		
		//4 password validate
		if(!CipherUtil.validatePassword(user.getUser_pwd(), password))
			return Property.ERROR_PWD_DIFF;
		
		return Property.SUCCESS_LOGIN; 
	}
	
	
	public String register(String email, String password, String conformPwd) {
		//1 empty check
		if(email == null || email.length() <= 0)
			return Property.ERROR_EMPTY_EMAIL;
		if(password == null || password.length() <= 0)
			return Property.ERROR_EMPTY_PWD;
		if(conformPwd == null || conformPwd.length() <= 0)
			return Property.ERROR_EMPTY_CFMPWD;
		
		//2 pwd == conformPwd ?
		if(!password.equals(conformPwd))
			return Property.ERROR_PWD_NOT_AGREE;
		
		//3 password format validate
		String vpf_rs = CipherUtil.validatePasswordFormat(password);
		if(vpf_rs != Property.SUCCESS_PWD_FORMAT)
			return vpf_rs;
			
		//4 ValidateEmail
		if(!ValidateEmail(email))
			return Property.ERROR_EMAIL_FORMAT;
		
		//5 email exist?
		User user = findByEmail(email);
		if(user != null)
			return Property.ERROR_USERNAME_EXIST;
		
		user = new User();
		user.setUser_pwd(CipherUtil.generatePassword(password));
		user.setUser_email(email);
		userDao.save(user);
		
		return Property.SUCCESS_REG;
		
	}
	
}
