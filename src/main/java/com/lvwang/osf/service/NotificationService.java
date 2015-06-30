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
		return notificationDao.save(notification);
	}
	
	public Map<String, Integer> getNotificationsCount(int user_id){
		return notificationDao.getNotificationsCount(user_id);
	}
}
