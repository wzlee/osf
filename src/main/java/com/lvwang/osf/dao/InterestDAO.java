package com.lvwang.osf.dao;

import java.util.List;
import java.util.Map;

import com.lvwang.osf.model.Tag;

public interface InterestDAO {
	
	void saveInterest(int user_id, int tag_id);
	void delInterest(int user_id, int tag_id);
	List<Integer> getUsersInterestInTag(int tag_id);
	boolean hasInterestInTag(int user_id, int tag_id);
	Map<Integer, Boolean> hasInterestInTags(int user_id, List<Integer> tags_id);
	List<Tag> getTagsUserInterestedIn(int user_id);
}
