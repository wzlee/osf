package com.lvwang.osf.dao;

import com.lvwang.osf.model.Post;

public interface EventDAO {
	int savePostEvent(Post post, String tags);
	int saveAlbumEvent();
	int savePhotoEvent();
	int saveFollowingEvent();
	int saveFollowedEvent();
}
