package com.lvwang.osf.util;

public class Property {
		
	public static final String ERROR_USERNAME_NOTEXIST = "001";	//"用户名不存在";
	public static final String ERROR_USERNAME_OUTOFMAX = "002";	//"用户名过长";
	public static final String ERROR_USERNAME_EXIST = "003";		//"用户名已存在";
	public static final String ERROR_EMAIL_EXIST = "004";			//"该用户已注册";
	public static final String ERROR_EMPTY_EMAIL = "005";			//"请输入邮箱";
	public static final String ERROR_EMPTY_USERNAME = "006";		//"用户名空";
	public static final String ERROR_EMPTY_PWD = "007";			//"请输入密码";
	public static final String ERROR_EMPTY_CFMPWD = "008";		//"请输入确认密码";
	public static final String ERROR_PWD_NOT_AGREE = "009";		//"密码输入不一致";
	public static final String ERROR_PWD_DIFF = "010";			//"密码错误";
	public static final String ERROR_EMAIL_FORMAT = "011";		//"请输入正确的邮箱地址";
	public static final String ERROR_PWD_SHORT = "012";			//"密码太短";
	public static final String ERROR_PWD_LONG = "013";			//"密码太长";
	
	public static final String SUCCESS_REG = "101";				//"注册成功";
	public static final String SUCCESS_LOGIN = "102";				//"登陆成功";
	public static final String SUCCESS_PWD_FORMAT = "103";			//密码格式正确
}
