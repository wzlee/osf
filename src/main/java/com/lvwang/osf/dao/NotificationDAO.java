package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Notification;

public interface NotificationDAO {
	int save(Notification notification);
	void delete(int id);
	Notification get(int notification_id);
	List<Notification> getAllOfUser(int user_id);
}
