package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.AlbumDAO;
import com.lvwang.osf.dao.EventDAO;
import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.Photo;
import com.lvwang.osf.model.Post;
import com.lvwang.osf.util.Dic;

@Service("eventService")
public class EventService {
	
	@Autowired
	@Qualifier("eventDao")
	private EventDAO eventDao; 
	
	@Autowired
	@Qualifier("albumDao")
	private AlbumDAO albumDao;
	
	private Event toEvent(int object_type, Object obj) {
		Event event = new Event();
		if(Dic.OBJECT_TYPE_POST == object_type) {
			Post post = (Post)obj;
			event.setObject_type(Dic.OBJECT_TYPE_POST);
			event.setObject_id(post.getId());
			event.setUser_id(post.getPost_author());
			event.setTitle(post.getPost_title());
			event.setSummary(post.getPost_excerpt());
			event.setContent(post.getPost_content());
			event.setLike_count(post.getLike_count());
			event.setShare_count(post.getShare_count());
			event.setComment_count(post.getComment_count());
			event.setTags(post.getPost_tags());
			
		} else if(Dic.OBJECT_TYPE_ALBUM == object_type) {
			Album album = (Album)obj;
			event.setObject_type(Dic.OBJECT_TYPE_ALBUM);
			event.setObject_id(album.getId());
			event.setUser_id(album.getUser_id());
			event.setSummary(album.getAlbum_desc());
			
			List<Photo> photos = album.getPhotos();
			List<Integer> ids = new ArrayList<Integer>();
			for(Photo photo:photos) {
				ids.add(photo.getId());
			}
			List<String> keys = albumDao.getKeys(ids);
			StringBuffer buffer = new StringBuffer();
			for(String key: keys) {
				buffer.append(key+":");
			}
			event.setContent(buffer.toString());
			event.setLike_count(0);
			event.setShare_count(0);
			event.setComment_count(0);
			
		} else if(Dic.OBJECT_TYPE_PHOTO == object_type) {
			//event_id = eventDao.savePhotoEvent((Photo)obj);
		}
		return event;
	}
	
	
	public int newEvent(int object_type, Object obj) {
		int event_id = eventDao.save(toEvent(object_type, obj));
		return event_id;
	}
}
