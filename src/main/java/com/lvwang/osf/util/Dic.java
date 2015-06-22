package com.lvwang.osf.util;

public class Dic {
	public static final int OBJECT_TYPE_POST = 0;
	public static final int OBJECT_TYPE_PHOTO = 1;
	public static final int OBJECT_TYPE_ALBUM = 2;
	
	public static final int OBJECT_TYPE_FOLLOWING = 3;
	
	private int object_type_post = 0;
	private int object_type_photo = 1;
	private int object_type_album = 2;
	private int object_type_following = 3;
	
	public static String checkType(int object_type){
		if(object_type == OBJECT_TYPE_ALBUM){
			return "album";
		} else if(object_type == OBJECT_TYPE_PHOTO){
			return "photo";
		} else if(object_type == OBJECT_TYPE_POST) {
			return "post";
		} else {
			return "following";
		}
	}

	public int getObject_type_post() {
		return object_type_post;
	}

	public void setObject_type_post(int object_type_post) {
		this.object_type_post = object_type_post;
	}

	public int getObject_type_photo() {
		return object_type_photo;
	}

	public void setObject_type_photo(int object_type_photo) {
		this.object_type_photo = object_type_photo;
	}

	public int getObject_type_album() {
		return object_type_album;
	}

	public void setObject_type_album(int object_type_album) {
		this.object_type_album = object_type_album;
	}

	public int getObject_type_following() {
		return object_type_following;
	}

	public void setObject_type_following(int object_type_following) {
		this.object_type_following = object_type_following;
	}

}
