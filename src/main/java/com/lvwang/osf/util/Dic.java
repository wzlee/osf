package com.lvwang.osf.util;

public class Dic {
	public static final int OBJECT_TYPE_POST = 0;
	public static final int OBJECT_TYPE_PHOTO = 1;
	public static final int OBJECT_TYPE_ALBUM = 2;
	
	public static final int OBJECT_TYPE_FOLLOWING = 3;
	
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

}
