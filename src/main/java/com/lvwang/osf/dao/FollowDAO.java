package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Follower;
import com.lvwang.osf.model.Following;

public interface FollowDAO {
	
	int saveFollowing(Following following);
	int saveFollower(Follower follower);
	boolean delFollowing(Following following);
	boolean delFollower(Follower follower);
	
	List<Following> getFollowings(int user_id);
	List<Follower> getFollowers(int user_id);
}
