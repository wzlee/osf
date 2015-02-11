package com.lvwang.osf.dao;

public interface TagDAO {

	int save(String tag);
	String getTagByID(int id);
	int getTagID(String tag);
}
