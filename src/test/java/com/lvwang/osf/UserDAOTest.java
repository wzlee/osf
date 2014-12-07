package com.lvwang.osf;

import com.lvwang.osf.dao.UserDAO;
import com.lvwang.osf.dao.impl.UserDAOImpl;
import com.lvwang.osf.model.User;

public class UserDAOTest {
	public static void main(String args) {
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserByID(1);
		System.out.println(user.getUser_name());
	}
}
