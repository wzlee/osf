package com.lvwang.osf.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.EventDAO;
import com.lvwang.osf.model.Post;
import com.lvwang.osf.util.Dic;

@Service("eventService")
public class EventService {
	
	@Autowired
	@Qualifier("eventDao")
	private EventDAO eventDao; 
	
	public void newEvent(int object_type, int object_id,
						 Date ts,
						 int user_id, String user_name, String user_avatar,
						 int like_count, int share_count, int comment_count,
						 String title, String summary, String content, String tags,
						 int following_user_id, String following_user_name,
						 int follower_user_id, String follower_user_name) {
		
	}
	
	public void newEvent(int object_type, Object obj, Object... others) {
		if(Dic.OBJECT_TYPE_POST == object_type) {
			int id = eventDao.savePostEvent((Post)obj, others!=null?(String)others[0]:null);
		} else if(Dic.OBJECT_TYPE_ALBUM == object_type) {
			
		} else if(Dic.OBJECT_TYPE_PHOTO == object_type) {
			
		}
	}
}
