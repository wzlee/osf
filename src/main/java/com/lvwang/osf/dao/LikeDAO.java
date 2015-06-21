package com.lvwang.osf.dao;

import java.util.List;

public interface LikeDAO {

	void like(int user_id, int object_type, int object_id);
	void undoLike(int user_id, int object_type, int object_id);
	boolean isLike(int user_id, int object_type, int object_id);
	long likersCount(int object_type, int object_id);
	List<Integer> getLikers(int object_type, int object_id); 
}
