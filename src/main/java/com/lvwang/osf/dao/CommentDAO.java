package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Comment;
import com.lvwang.osf.model.User;

public interface CommentDAO {
	Comment getCommentByID(int id);
	User getCommentAuthor(int comment_id);
	List<Comment> getCommentsOfPost(int id, int offset, int count);
	List<Comment> getCommentsOfPhoto(int id, int offset, int count);
	List<Comment> getCommentsOfAlbum(int id, int offset, int count);
	List<Comment> getCommentsOfShortPost(int id, int offset, int count);

	int save(Comment comment);
	boolean delete(int id);
	
	int commentsCount(int object_type, int object_id);
}
