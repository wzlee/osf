package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.TagDAO;
import com.lvwang.osf.model.Tag;
import com.lvwang.osf.util.Property;

@Service("tagService")
public class TagService {
	
	@Autowired
	@Qualifier("tagDao")
	private TagDAO tagDao;
	
	private String check(String tag) {
		if(tag == null || tag.length() == 0) {
			return Property.ERROR_TAG_EMPTY;
		}
		return Property.SUCCESS_TAG_FORMAT;
	}
	
	public List<String> toList(String tags) {
		String[] tmp = tags.split(" ");
		return new ArrayList<String>(Arrays.asList(tmp));
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
	
	public Map<String, Object> newTags(List<String> tags) {
		Map<String, Object> ret = new HashMap<String, Object>();
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
}
