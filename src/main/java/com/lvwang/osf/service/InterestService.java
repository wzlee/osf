package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.InterestDAO;
import com.lvwang.osf.model.Tag;

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
	
	public boolean hasInterestInTag(int user_id, int tag_id) {
		return interestDao.hasInterestInTag(user_id, tag_id);
	}
	

	
	public Map<Integer, Boolean> hasInterestInTags(int user_id, List<Tag> tags){
		if(tags == null || tags.size() == 0 ){
			return null;
		}
		List<Integer> tags_id = new ArrayList<Integer>();
		for(Tag tag: tags){
			tags_id.add(tag.getId());
		}
		
		return interestDao.hasInterestInTags(user_id, tags_id);
		
	}
}
