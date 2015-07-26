package com.lvwang.osf.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("thumbnail")
public class Thumbnail {
	
	@Value("${post_cover}")
	String post_cover;
	
	@Value("${album_thumbnail}")
	String album_thumbnail;
	
	public String getPost_cover(){
		return post_cover;
	}
	
	public String getAlbum_thumbnail(){
		return album_thumbnail;
	}
}
