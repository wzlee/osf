package com.lvwang.osf.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;

public interface AlbumDAO {
	
	int saveAlbum(Album album);
	int savePhoto(Photo photo);
	
	//return photo etag
	String uploadPhoto(MultipartFile img, Photo details);
	String uploadPhoto(byte[] img, String key);
	
	String getKey(int id);
	List<String> getKeys(List<Integer> ids);
	
	int getAlbumUser(int id);
	int getAlbum(int user_id, int status);
	
	int updateAlbumInfo(Album album);
	int updateAlbumDesc(int album_id, String album_desc, int album_status);
	int updateAlbumCover(int album_id, String cover);
	int updatePhotosCount(int album_id, int count);
	int updatePhotoDesc(int photo_id, String photo_desc);
	
	Album getAlbum(int id);
	
	List<Album> getAlbumsOfUser(int id);
	List<Photo> getPhotos(int album_id);
	
	int getAuthorOfAlbum(int id);
	Album getAlbumContainPhoto(int photo_id);
	
	void delPhoto(int id);
	void delPhotoInBucket(String key);
}
