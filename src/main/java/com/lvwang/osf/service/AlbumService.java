package com.lvwang.osf.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lvwang.osf.dao.AlbumDAO;
import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;
import com.lvwang.osf.model.User;
import com.lvwang.osf.util.Property;

@Service("albumService")
public class AlbumService {
	
	public static final int ALBUM_STAUS_NORMAL = 0;
	public static final int ALBUM_STAUS_TOBERELEASED = 1; //待发布
	public static final String BASE_URL = "http://osfimgs.oss-cn-hangzhou.aliyuncs.com/";
	
	
	@Autowired
	private AlbumDAO albumDao;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	//图片格式校验
	public String validataImg(MultipartFile img) {
		
		return null;
	}
	
	public String getImgType(MultipartFile img) {
		String contentType = img.getContentType();
		return contentType.substring(contentType.indexOf('/')+1);
	}
	
	public Map<String, Object> newAlbum(int user_id, String title, String desc, int status, String cover) {
		Map<String, Object> map = new HashMap<String, Object>();
		Album album = new Album();
		album.setUser_id(user_id);
		album.setAlbum_title(title);
		album.setAlbum_desc(desc);
		album.setStatus(status);
		album.setCover(cover);
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
	
	public Map<String, Object> uploadPhoto(MultipartFile img) {
		Map<String, Object> map = new HashMap<String, Object>();
		Photo details = new Photo();
		String key = UUID.randomUUID().toString()+"."+getImgType(img);
		details.setKey(key);
		String etag = albumDao.uploadPhoto(img, details);
		if(etag == null || etag.length() ==0) {
			map.put("status", Property.ERROR_PHOTO_CREATE);
			return map;
		} else {	
			map.put("link", BASE_URL+key);
			map.put("status", Property.SUCCESS_PHOTO_CREATE);			
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
	
	//获取用户的待发布相册
	public int getToBeReleasedAlbum(int user_id) {
		return albumDao.getAlbum(user_id, ALBUM_STAUS_TOBERELEASED);
	}
	
	
	public List<Photo> getPhotosOfAlbum(int album_id) {
		return albumDao.getPhotos(album_id);
	}
	
	public String updateAlbumDesc(int album_id, String album_desc) {
		int effRows = albumDao.updateAlbumDesc(album_id, album_desc, ALBUM_STAUS_NORMAL);
		if(effRows==1) {
			return Property.SUCCESS_ALBUM_UPDATE;
		} else {
			return Property.ERROR_ALBUM_UPDDESC;
		}
	}
	
	public String updatePhotoDesc(List<Photo> photos) {
		for(Photo photo: photos) {
			albumDao.updatePhotoDesc(photo.getId(), photo.getDesc());
		}
		return Property.SUCCESS_ALBUM_UPDATE;
	}
	
	public String updateAlbumCover(int album_id, String cover) {
		int effRows = albumDao.updateAlbumCover(album_id, cover);
		if(effRows==1) {
			return Property.SUCCESS_ALBUM_UPDATE;
		} else {
			return Property.ERROR_ALBUM_UPDCOVER;
		}
	}
	
	public String updatePhotosCount(int album_id, int count) {
		int effRows = albumDao.updatePhotosCount(album_id, count);
		if(effRows==1) {
			return Property.SUCCESS_ALBUM_UPDATE;
		} else {
			return Property.ERROR_ALBUM_UPDCOVER;
		}
	}
	
	public List<Album> getAlbumsOfUser(int id) {
		return albumDao.getAlbumsOfUser(id);
	}
	
	public Album getAlbum(int id) {
		return albumDao.getAlbum(id);
	}
	
	public String getKeyofPhoto(int id) {
		return albumDao.getKey(id);
	}
	
	public User getAuthorOfALbum(int id) {
		int user_id = albumDao.getAuthorOfAlbum(id);
		return userService.findById(user_id);
	}
}
