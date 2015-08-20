package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Tag;

public interface TagDAO {

	/**
	 * 
	 * @param tag
	 * @return
	 */
	int save(String tag);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Tag getTagByID(int id);
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	int getTagID(String tag);
	
	/**
	 * 
	 * @param tags_id
	 * @return
	 */
	List<Tag> getTags(List<Integer> tags_id);
	
	/**
	 * 获取有封面的tag
	 * @return
	 */
	List<Tag> getTagsHasCover();
	
	
}
