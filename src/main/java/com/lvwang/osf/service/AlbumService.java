package com.lvwang.osf.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lvwang.osf.dao.AlbumDAO;
import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;
import com.lvwang.osf.util.Property;

@Service("albumService")
public class AlbumService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AlbumDAO albumDao;
	
	//图片格式校验
	public String validataImg(MultipartFile img) {
		
		return null;
	}
	
	public String getImgType(MultipartFile img) {
		String contentType = img.getContentType();
		return contentType.substring(contentType.indexOf('/')+1);
	}
	
	public Map<String, Object> newAlbum(int user_id, String title, String desc) {
		Map<String, Object> map = new HashMap<String, Object>();
		Album album = new Album();
		album.setUser_id(user_id);
		album.setAlbum_title(title);
		album.setAlbum_desc(desc);
		
		int id = albumDao.saveAlbum(album);
		if(id != 0){
			album.setId(id);
			map.put("album", album);
			map.put("status", Property.SUCCESS_ALBUM_CREATE);
		} else {
			map.put("status", Property.ERROR_ALBUM_CREATE);
		}
		return map;
	}
	
	public Map<String, Object> newPhoto(int album_id, MultipartFile img, String desc) {
		Map<String, Object> map = new HashMap<String, Object>();
		Photo details = new Photo();
		String key = UUID.randomUUID().toString()+"."+getImgType(img);
		details.setKey(key);
		details.setAlbum_id(album_id);
		details.setDesc(desc);
		String etag = albumDao.uploadPhoto(img, details);
		if(etag == null || etag.length() ==0) {
			map.put("status", Property.ERROR_PHOTO_CREATE);
			return map;
		} else {		
			map.put("status", Property.SUCCESS_PHOTO_CREATE);			
		}
		int photo_id = albumDao.savePhoto(details);
		if(photo_id == 0) {
			map.put("status", Property.ERROR_PHOTO_CREATE);
			return map;
		} 
		details.setId(photo_id);
		map.put("photo", details);		
		return map;
	}
	
	public String checkUserOfAlbum(int album_id, int user_id) {
		int id = albumDao.getAlbumUser(album_id);
		if(id != user_id) {
			return Property.ERROR_ALBUM_PERMISSIONDENIED;
		} else {
			return Property.SUCCESS_ALBUM_ALLOWED;
		}
	}
}
