package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Post;

public interface PostDAO {
	Post getPostByID(int id);
	List<Post> getPostsByIDs(int[] ids); 
	List<Post> getPostsByUserID(int id);
	
	int save(Post post);
	void delete(int id);
	int getAuthorOfPost(int id);
	
	long count(int user_id);
}
