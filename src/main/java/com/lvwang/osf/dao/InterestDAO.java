package com.lvwang.osf.dao;

public interface InterestDAO {
	
	void saveInterest(int user_id, int tag_id);
	void delInterest(int user_id, int tag_id);
}
