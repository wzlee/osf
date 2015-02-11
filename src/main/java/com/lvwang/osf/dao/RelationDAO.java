package com.lvwang.osf.dao;

public interface RelationDAO {
	int save(int object_type, int object_id, int tag_id);
	int save(int object_type, int object_id, int[] tags_id);
	boolean delete();
}
