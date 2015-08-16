package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Relation;

public interface RelationDAO {
	int save(int object_type, int object_id, int tag_id);
	int save(int object_type, int object_id, int[] tags_id);
	boolean delete();
	List<Relation> get(int tag_id) ;
	List<Relation> getRelationsInTags(List<Integer> tags_id);
}
