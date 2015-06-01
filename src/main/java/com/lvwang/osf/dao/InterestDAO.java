package com.lvwang.osf.dao;

import java.util.List;

public interface InterestDAO {
	
	void saveInterest(int user_id, int tag_id);
	void delInterest(int user_id, int tag_id);
	List<Integer> getUsersInterestInTag(int tag_id);
}
