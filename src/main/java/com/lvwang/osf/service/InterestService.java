package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.InterestDAO;

@Service("interestService")
public class InterestService {
	
	@Autowired
	@Qualifier("interestDao")
	private InterestDAO interestDao;
	
	
	public void interestInTag(int user_id, int tag_id) {
		interestDao.saveInterest(user_id, tag_id);
	}
	
	public void undoInterestInTag(int user_id, int tag_id){
		interestDao.delInterest(user_id, tag_id);
	}
	
	public List<Integer> getUsersInterestedInTag(int tag_id) {
		return interestDao.getUsersInterestInTag(tag_id);
	}
}
