package com.lvwang.osf.model;

import java.util.Date;

public class User {
	private int id;
	private String user_name;
	private String user_email;
	private String user_pwd;
	private String user_nicename;
	private Date user_registered_date;
	private int user_status;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_nicename() {
		return user_nicename;
	}
	public void setUser_nicename(String user_nicename) {
		this.user_nicename = user_nicename;
	}
	public Date getUser_registered_date() {
		return user_registered_date;
	}
	public void setUser_registered_date(Date user_registered_date) {
		this.user_registered_date = user_registered_date;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
