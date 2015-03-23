package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Event;


public interface EventDAO {
	int save(Event event);
	List<Event> getEvents(List<Integer> event_ids);
}
