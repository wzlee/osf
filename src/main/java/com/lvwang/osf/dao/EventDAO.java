package com.lvwang.osf.dao;

import java.util.List;
import java.util.Map;

import com.lvwang.osf.model.Event;


public interface EventDAO {
	int save(Event event);
	List<Event> getEventsWithIDs(List<Integer> event_ids);
	List<Event> getEventsWithRelations(Map<Integer, List<Integer>> relations);
	List<Event> getEventsOfUser(int user_id, int count);
	void delete(int id);
	void delete(int object_type, int object_id);
	Event getEvent(int object_type, int object_id);
}
