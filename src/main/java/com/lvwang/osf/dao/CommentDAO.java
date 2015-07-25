package com.lvwang.osf.dao;

import java.util.List;

import com.lvwang.osf.model.Comment;
import com.lvwang.osf.model.User;

public interface CommentDAO {
	Comment getCommentByID(int id);
	User getCommentAuthor(int comment_id);
	List<Comment> getCommentsOfPost(int id);
	List<Comment> getCommentsOfPhoto(int id);
	List<Comment> getCommentsOfAlbum(int id);

	int save(Comment comment);
	boolean delete(int id);
}
