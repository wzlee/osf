package com.lvwang.osf.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.NotificationDAO;
import com.lvwang.osf.model.Notification;

@Service("notificationService")
public class NotificationService {
	
	@Autowired
	@Qualifier("notificationDao")
	private NotificationDAO notificationDao;
	
	/**
	 * save notification
	 * 
	 * @param notification
	 * @return notification id
	 */
	public int doNotify(Notification notification){
		int id = notificationDao.save(notification);
		refreshNotifications(notification.getNotified_user());
		return id;
	}
	
	public Map<String, Integer> getNotificationsCount(int user_id){
		return notificationDao.getNotificationsCount(user_id);
	}
	
	public void refreshNotifications(int user_id){
		notificationDao.refresh(user_id);
	}
}
