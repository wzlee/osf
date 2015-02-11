package com.lvwang.osf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;







import com.lvwang.osf.dao.PostDAO;
import com.lvwang.osf.model.Post;
import com.lvwang.osf.util.Property;

@Service("postService")
public class PostService {

	public static final int POST_STATUS_PUB = 0;	//公开
	public static final int POST_STATUS_PRV = 1;	//私密
	public static final int POST_STATUS_SAVED = 2;	//保存
	public static final int POST_STATUS_EDIT = 3;	//编辑
	
	public static final int COMMENT_STATUS_ALLOWED = 0;		//允许评论
	public static final int COMMENT_STATUS_NOTALLOWED = 1;	//不允许评论
	
	@Autowired
	@Qualifier("postDao")
	private PostDAO postDao;
	
	public Map<String, Object> newPost(Integer author, String title, String content, 
						Integer post_status, Integer comment_status) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(author == null || 
		   title == null || title.length() == 0 ||
		   content == null || content.length() == 0) {
			map.put("status", Property.ERROR_POST_EMPTY);
			return map;
		}
		   
		
		if(post_status == null)
			post_status = POST_STATUS_PUB;
		if(post_status < 0 || post_status > 3) {
			map.put("status", Property.ERROR_POST_STATUS);
			return map;
		}
		
		if(comment_status == null)
			post_status = COMMENT_STATUS_ALLOWED;
		if(comment_status != 0 && comment_status != 1) {
			map.put("status", Property.ERROR_COMMENT_STATUS);
		}
		
		Post post = new Post();
		post.setPost_author(author);
		post.setPost_title(title);
		post.setPost_content(content);
		post.setPost_status(post_status);
		post.setComment_status(comment_status);
		post.setLike_count(0);
		post.setShare_count(0);
		post.setComment_count(0);
		int id = postDao.save(post);
		post.setId(id);
		map.put("post", post);
		map.put("status", Property.SUCCESS_POST_CREATE);
		return map;
	}
	
	public Post findPostByID(int id) {
		return postDao.getPostByID(id);
	}
	
	public List<Post> findPostsByIDs(int[] ids) {
		return null;
	}
	
	public List<Post> findPostsOfUser(int id) {
		return postDao.getPostsByUserID(id);
	}
	
	public List<Post> findPostsOfUser(int id, Object[] fromto) {
		return null;
	}
	
	public List<Post> findPostsOfUser(int id, String orderby, Object[] fromto) {
		return null;
	}
	
	
	
}
