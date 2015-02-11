package com.lvwang.osf.dao;

import org.springframework.web.multipart.MultipartFile;

import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;

public interface AlbumDAO {
	
	int saveAlbum(Album album);
	int savePhoto(Photo photo);
	
	//return photo etag
	String uploadPhoto(MultipartFile img, Photo details);
	
	int getAlbumUser(int id);
}
