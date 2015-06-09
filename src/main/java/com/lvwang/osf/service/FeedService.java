package com.lvwang.osf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvwang.osf.dao.EventDAO;
import com.lvwang.osf.dao.FeedDAO;
import com.lvwang.osf.model.Event;
import com.lvwang.osf.model.Follower;
import com.lvwang.osf.model.User;

@Service("feedService")
public class FeedService {

	@Autowired
	@Qualifier("followService")
	private FollowService followService;
	
	@Autowired
	@Qualifier("feedDao")
	private FeedDAO feedDao;
	
	@Autowired
	@Qualifier("eventService")
	private EventService eventService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	public void push(int user_id, int event_id) {
		List<Integer> followers = followService.getFollowerIDs(user_id);
		followers.add(user_id);	//add self
		if(followers != null && followers.size()!=0) {
			for(Integer follower: followers) {
				feedDao.save("feed:user:"+follower, event_id);
			}
		}
	}
	
	private List<Integer> getEventIDs(int user_id) {
		return feedDao.fetch("feed:user:"+user_id);
	}
	
	public List<Event> getFeeds(int user_id) {
		List<Integer> event_ids = getEventIDs(user_id);
		if(event_ids != null && event_ids.size()!=0 ) {
			List<Event> events = eventService.getEventsWithIDs(event_ids);
			if(events == null)
				events = new ArrayList<Event>();
			addUserInfo(events);
			return events;
		}
		else 
			return new ArrayList<Event>();
	}
	
	public void addUserInfo(List<Event> events) {
		if(events == null || events.size() == 0)
			return;
		for(Event event : events) {
			User user = userService.findById(event.getUser_id());
			event.setUser_name(user.getUser_name());
			event.setUser_avatar(user.getUser_avatar());
		}
	}
	
	public void pull() {
		
	}
	
}
