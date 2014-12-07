package com.lvwang.osf.dao;

import com.lvwang.osf.model.User;

public interface UserDAO {
	User getUserByID(int id);
	User getUserByEmail(String email);
	User getUserByUsername(String username);
	String getPwdByUsername(String username);
	
	int save(User user);
	void delete(int id);
	
}
