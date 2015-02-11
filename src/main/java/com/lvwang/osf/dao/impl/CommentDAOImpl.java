package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.CommentDAO;
import com.lvwang.osf.model.Comment;
import com.lvwang.osf.service.CommentService;


@Repository("commentDao")
public class CommentDAOImpl implements CommentDAO{
	
	private static final String TABLE = "osf_comments";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Comment getCommentByID(int id) {
		String sql = "select * from " + TABLE + " where id=?";
		Comment comment = jdbcTemplate.queryForObject(sql, new Object[]{id}, Comment.class);
		return comment;
	}

	private Comment generateComment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setComment_author(rs.getInt("comment_author"));
		comment.setComment_author_email(rs.getString("comment_author_email"));
		comment.setComment_object_id(rs.getInt("comment_object_id"));
		comment.setComment_content(rs.getString("comment_content"));
		comment.setComment_object_type(rs.getInt("comment_object_type"));
		comment.setComment_parent(rs.getInt("comment_parent"));
		comment.setComment_parent_email(rs.getString("comment_parent_email"));
		comment.setComment_ts(rs.getTimestamp("comment_ts"));
		
		return comment;
	}
	
	public List<Comment> getCommentsOfPost(int id) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=?";
		//List<Comment> comments = jdbcTemplate.queryForList(sql, new Object[]{CommentService.COMMENT_TYPE_POST, id}, Comment.class);
		List<Comment> comments = jdbcTemplate.query(sql, new Object[]{CommentService.COMMENT_TYPE_POST, id}, 
				new RowMapper<Comment>() {

					public Comment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return generateComment(rs);
					}
			
		});
		return comments;
	}

	public List<Comment> getCommentsOfPhoto(int id) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=?";
		List<Comment> comments = jdbcTemplate.queryForList(sql, new Object[]{CommentService.COMMENT_TYPE_PHOTO, id}, Comment.class);
		return comments;
	}

	public List<Comment> getCommentsOfAlbum(int id) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=?";
		List<Comment> comments = jdbcTemplate.queryForList(sql, new Object[]{CommentService.COMMENT_TYPE_ALBUM, id}, Comment.class);
		return comments;
	}

	public int save(final Comment comment) {
		final String sql = "insert into " + TABLE + "(comment_object_type, comment_object_id,"
											+ "comment_author, comment_author_email,"
											+ "comment_content, comment_parent, comment_parent_email) values(?,?,?,?,?,?,?)";
		System.out.println("parent:"+comment.getComment_parent());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, comment.getComment_object_type());
				ps.setInt(2, comment.getComment_object_id());
				ps.setInt(3, comment.getComment_author());
				ps.setString(4, comment.getComment_author_email());
				ps.setString(5, comment.getComment_content());
				ps.setInt(6, comment.getComment_parent());
				ps.setString(7, comment.getComment_parent_email());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}


}
