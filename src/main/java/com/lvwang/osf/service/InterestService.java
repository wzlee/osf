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
	
	/**
	 * 关注tag
	 * 
	 * @param user_id
	 * @param tag_id
	 */
	public void interestInTag(int user_id, int tag_id) {
		interestDao.saveInterest(user_id, tag_id);
	}
	
	
	/**
	 * 撤销关注tag
	 * 
	 * @param user_id
	 * @param tag_id
	 */
	public void undoInterestInTag(int user_id, int tag_id){
		interestDao.delInterest(user_id, tag_id);
	}
	
	/**
	 * 获取关注tag_id的用户列表
	 * 
	 * @param tag_id
	 * @return
	 */
	public List<Integer> getUsersInterestedInTag(int tag_id) {
		return interestDao.getUsersInterestInTag(tag_id);
	}
	
	/**
	 * 判断用户对tag是否已经关注
	 * 
	 * @param user_id
	 * @param tag_id
	 * @return
	 */
	public boolean hasInterestInTag(int user_id, int tag_id) {
		return interestDao.hasInterestInTag(user_id, tag_id);
	}
	

	/**
	 * 判断用户对列表中的tag是否已经关注
	 * 
	 * @param user_id
	 * @param tags
	 * @return
	 */
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
	
	/**
	 * 获取用户关注的tag列表
	 * 
	 * @param user_id
	 * @param tag_id
	 * @return
	 */
	public List<Tag> getTagsUserInterestedIn(int user_id){
		return interestDao.getTagsUserInterestedIn(user_id);
	}
}
