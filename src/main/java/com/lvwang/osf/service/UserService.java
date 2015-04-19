package com.lvwang.osf.service;

import java.net.URLEncoder;
import java.util.Map;

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
	public static final int STATUS_USER_NORMAL = 0;				//正常
	public static final int STATUS_USER_INACTIVE = 1;			//待激活
	public static final int STATUS_USER_LOCK = 2;				//锁定
	public static final int STATUS_USER_CANCELLED = 3;			//注销
	
	
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
			return Property.ERROR_PWD_EMPTY;
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
			return Property.ERROR_EMAIL_EMPTY;
		if(password == null || password.length() <= 0)
			return Property.ERROR_PWD_EMPTY;
		
		//2 ValidateEmail 
		if(!ValidateEmail(email))
			return Property.ERROR_EMAIL_FORMAT;	

		//3 email exist?
		User user = findByEmail(email);
		if(user == null)
			return Property.ERROR_USERNAME_NOTEXIST;
		else {
			//4 check user status
			if(STATUS_USER_NORMAL != user.getUser_status())
				return String.valueOf(user.getUser_status());	
		}
		
		//5 password validate
		if(!CipherUtil.validatePassword(user.getUser_pwd(), password))
			return Property.ERROR_PWD_DIFF;
		
		return Property.SUCCESS_ACCOUNT_LOGIN; 
	}
	
	
	@SuppressWarnings("deprecation")
	public String register(String username, String email, String password, String conformPwd, Map<String, String> map) {
		//1 empty check
		if(username == null || username.length() == 0) 
			return Property.ERROR_USERNAME_EMPTY;
		if(email == null || email.length() <= 0)
			return Property.ERROR_EMAIL_EMPTY;
		if(password == null || password.length() <= 0)
			return Property.ERROR_PWD_EMPTY;
		if(conformPwd == null || conformPwd.length() <= 0)
			return Property.ERROR_CFMPWD_EMPTY;
		
		//username exist check
		if(findByUsername(username) != null) {
			return Property.ERROR_USERNAME_EXIST;
		}
		//5 email exist?
		User user = findByEmail(email);
		if(user != null) {
						
			//6 user status check
			if(STATUS_USER_NORMAL == user.getUser_status())
				return Property.ERROR_ACCOUNT_EXIST;
			else if(STATUS_USER_INACTIVE == user.getUser_status()){
				map.put("activationKey", URLEncoder.encode(user.getUser_activationKey()));
				return Property.ERROR_ACCOUNT_INACTIVE;
			}
			else if(STATUS_USER_LOCK == user.getUser_status())
				return Property.ERROR_ACCOUNT_LOCK;
			else if(STATUS_USER_CANCELLED == user.getUser_status()) 
				return Property.ERROR_ACCOUNT_CANCELLED;
		}
		
		
		//2 pwd == conformPwd ?
		if(!password.equals(conformPwd))
			return Property.ERROR_CFMPWD_NOTAGREE;
		
		//3 password format validate
		String vpf_rs = CipherUtil.validatePasswordFormat(password);
		if(vpf_rs != Property.SUCCESS_PWD_FORMAT)
			return vpf_rs;
			
		//4 ValidateEmail
		if(!ValidateEmail(email))
			return Property.ERROR_EMAIL_FORMAT;
		

		
		user = new User();
		user.setUser_name(username);
		user.setUser_pwd(CipherUtil.generatePassword(password));
		user.setUser_email(email);
		user.setUser_status(STATUS_USER_INACTIVE);
		String activationKey = CipherUtil.generateActivationUrl(email, password);
		user.setUser_activationKey(activationKey);
		int id =userDao.save(user);
		
		map.put("id", String.valueOf(id));
		map.put("activationKey", URLEncoder.encode(activationKey));
		return Property.SUCCESS_ACCOUNT_REG;
		
	}
	
	public String activateUser(String key) {
		User user = findByActivationKey(key);
		if(user == null)
			return Property.ERROR_ACCOUNT_ACTIVATION_NOTEXIST;
		else {
			user.setUser_activationKey(null);
			user.setUser_status(STATUS_USER_NORMAL);
			
			int effRows = userDao.activateUser(user);
			if(effRows != 1)
				return Property.ERROR_ACCOUNT_ACTIVATION;
		}
		return Property.SUCCESS_ACCOUNT_ACTIVATION;
	}
	
	public String findActivationKeyOfUser(int id) {
		return null;
	}
	
	private User findByActivationKey(String key) {
		return userDao.getUser("user_activationKey", new Object[]{key});
	}
	
	public int getStatus(String email) {
		User user = userDao.getUserByEmail(email);
		return user.getUser_status();
	}
	
}
