package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.User;

public interface UserDAO {
	User getUserByID(int id);
	User getUserByEmail(String email);
	User getUserByUsername(String username);
	String getPwdByUsername(String username);
	List<User> getUsersByIDs(int[] ids);
	User getUser(String condition, Object[] args);
	
	int save(User user);
	int activateUser(User user);
	boolean delete(int id);
	
}
