package com.lvwang.osf.model;

import java.util.Date;

public class Album {
	
	private int id;
	private int user_id;
	private Date create_ts;
	private String album_title;
	private String album_desc;
	private Date last_add_ts;
	private int photos_count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getCreate_ts() {
		return create_ts;
	}
	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}
	public String getAlbum_title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	public String getAlbum_desc() {
		return album_desc;
	}
	public void setAlbum_desc(String album_desc) {
		this.album_desc = album_desc;
	}
	public Date getLast_add_ts() {
		return last_add_ts;
	}
	public void setLast_add_ts(Date last_add_ts) {
		this.last_add_ts = last_add_ts;
	}
	public int getPhotos_count() {
		return photos_count;
	}
	public void setPhotos_count(int photos_count) {
		this.photos_count = photos_count;
	}
}
