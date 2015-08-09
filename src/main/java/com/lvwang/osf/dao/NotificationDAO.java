package com.lvwang.osf.dao;

import java.util.List;
import java.util.Map;

import com.lvwang.osf.model.Notification;

public interface NotificationDAO {
	int save(Notification notification);
	void delete(int id);
	Notification get(int notification_id);
	List<Notification> getAllOfUser(int user_id);
	List<Notification> getNotificationsOfType(int user_id, int notify_type);
	List<Notification> getNotificationsOfType(int user_id, Object... notify_types); 
			
	Map<String, Integer> getNotificationsCount(int user_id);
	void refresh(int user_id);
}
