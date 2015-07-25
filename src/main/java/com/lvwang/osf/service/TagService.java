package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lvwang.osf.dao.TagDAO;
import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.Tag;
import com.lvwang.osf.util.Property;

@Service("tagService")
public class TagService {
	
	@Autowired
	@Qualifier("eventService")
	private EventService eventService;
	
	@Autowired
	@Qualifier("relationService")
	private RelationService relationService;

	@Autowired
	@Qualifier("feedService")
	private FeedService feedService;
	
	@Autowired
	@Qualifier("tagDao")
	private TagDAO tagDao;
	
	private String check(String tag) {
		if(tag == null || tag.length() == 0) {
			return Property.ERROR_TAG_EMPTY;
		}
		return Property.SUCCESS_TAG_FORMAT;
	}
	
	public static List<String> toList(String tags) {
		if(tags == null || tags.length() == 0)
			return new ArrayList<String>();
		String[] tmp = tags.split(" ");
		return new ArrayList<String>(Arrays.asList(tmp));
	}
	
	public static String toString(List<String> tags) {
		if(tags == null || tags.size() == 0)
			return null;
		StringBuffer buffer = new StringBuffer();
		for(String tag: tags) {
			buffer.append(tag+" ");
		}
		return buffer.toString();
	}
	
	public Map<String, Object> newTag(String tag){
		Map<String, Object> ret = new HashMap<String, Object>();
		String status = check(tag);
		ret.put("status", status);
		if(!status.equals(Property.SUCCESS_TAG_FORMAT)) {
			return ret;
		}
		
		int id = tagDao.getTagID(tag);
		if(id != 0) {
			Tag tg = new Tag();
			tg.setId(id);
			tg.setTag(tag);
			ret.put("tag", tg);
			return ret;
		}
	
		id = tagDao.save(tag);
		if(id != 0) {
			Tag tg = new Tag();
			tg.setId(id);
			tg.setTag(tag);
			ret.put("tag", tg);
		}
		return ret;
	}
	
	@Transactional
	public Map<String, Object> newTags(List<String> tags) {
				
		Map<String, Object> ret = new HashMap<String, Object>();
		if(tags == null || tags.size() == 0) {
			ret.put("status", Property.SUCCESS_TAG_CREATE);
			return ret;
		}
		
		List<Tag> taglist = new ArrayList<Tag>();
		ret.put("tags", taglist);
		
		for(String tag: tags) {
			String status = check(tag);
			if(!status.equals(Property.SUCCESS_TAG_FORMAT)) {
				return ret;
			}
			
			int id = tagDao.getTagID(tag);
			if(id != 0) {
				Tag tg = new Tag();				
				tg.setId(id);
				tg.setTag(tag);
				taglist.add(tg);
				continue;
			}
						
			id = tagDao.save(tag);
			if(id != 0) {
				Tag tg = new Tag();	
				tg.setId(id);
				tg.setTag(tag);
				taglist.add(tg);
			}
		}
		ret.put("status", Property.SUCCESS_TAG_CREATE);
		return ret;
	}
	
	public int getID(String tag){
		return tagDao.getTagID(tag);
	}
	
	/**
	 * 需重构，迁移到feed或event
	 * @param tag
	 * @return
	 */
	public List<Event> getWithTag(String tag) {
		List<Event> events = eventService.getEventsWithRelations(relationService.getRelationsWithTag(tag));
		feedService.addUserInfo(events);
		return events;
	}
	
	/**
	 * 获取推荐tag
	 * @param user_id
	 * @return
	 */
	public List<Tag> getRecommendTags(int user_id){
		List<Integer> tags_rec = new ArrayList<Integer>();
		for(int i=00; i<10; i++){
			tags_rec.add(i);
		}
		return tagDao.getTags(tags_rec);
	}
}
