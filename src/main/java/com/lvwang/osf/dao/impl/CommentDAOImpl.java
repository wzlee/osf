package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.CommentDAO;
import com.lvwang.osf.model.Comment;
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.CommentService;
import com.lvwang.osf.util.Dic;


@Repository("commentDao")
public class CommentDAOImpl implements CommentDAO{
	
	private static final String TABLE = "osf_comments";
	
	private static final String COUNTER = "counter";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, String> redisTemplate; 
	
	@Resource(name="redisTemplate")
	private HashOperations<String, String, Integer> hashOps;
	
	public User getCommentAuthor(int comment_id){
		String sql = "select comment_author,comment_author_name from " + TABLE + " where id=?";
		return jdbcTemplate.query(sql, new Object[]{comment_id}, new ResultSetExtractor<User>(){

			public User extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				User user = new User();
				if(rs.next()){
					user.setId(rs.getInt("comment_author"));
					user.setUser_name(rs.getString("comment_author_name"));
				} 
				return user;
			}
			
		});
	}
	
	public Comment getCommentByID(int id) {
		String sql = "select * from " + TABLE + " where id=?";
		Comment comment = jdbcTemplate.query(sql, new Object[]{id}, new ResultSetExtractor<Comment>(){

			public Comment extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()){
					return generateComment(rs);
				} else{
					return null;
				}
			}
			
		});
		return comment;
	}

	private Comment generateComment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setComment_author(rs.getInt("comment_author"));
		comment.setComment_author_name(rs.getString("comment_author_name"));
		comment.setComment_object_id(rs.getInt("comment_object_id"));
		comment.setComment_content(rs.getString("comment_content"));
		comment.setComment_object_type(rs.getInt("comment_object_type"));
		comment.setComment_parent(rs.getInt("comment_parent"));
		comment.setComment_parent_author(rs.getInt("comment_parent_author"));
		comment.setComment_parent_author_name(rs.getString("comment_parent_author_name"));
		comment.setComment_ts(rs.getTimestamp("comment_ts"));
		
		return comment;
	}
	
	public List<Comment> getCommentsOfPost(int id, int offset, int count) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=? order by comment_ts desc limit ?,?";
		List<Comment> comments = jdbcTemplate.query(sql, new Object[]{CommentService.COMMENT_TYPE_POST, id, offset, count}, 
				new RowMapper<Comment>() {

					public Comment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return generateComment(rs);
					}
			
		});
		return comments;
	}

	public List<Comment> getCommentsOfShortPost(int id, int offset, int count) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=? order by comment_ts desc limit ?,?";
		List<Comment> comments = jdbcTemplate.query(sql, new Object[]{Dic.OBJECT_TYPE_SHORTPOST, id, offset, count}, 
				new RowMapper<Comment>() {

					public Comment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return generateComment(rs);
					}
			
		});
		return comments;
	}
	
	public List<Comment> getCommentsOfPhoto(int id, int offset, int count) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=? order by comment_ts desc  limit ?,?";
		List<Comment> comments = jdbcTemplate.query(sql, new Object[]{CommentService.COMMENT_TYPE_PHOTO, id, offset, count}, 
				new RowMapper<Comment>() {

					public Comment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return generateComment(rs);
					}
			
		});
		return comments;
	}

	public List<Comment> getCommentsOfAlbum(int id, int offset, int count) {
		String sql = "select * from " + TABLE + " where comment_object_type=? and comment_object_id=? order by comment_ts desc  limit ?,?";
		List<Comment> comments = jdbcTemplate.query(sql, new Object[]{CommentService.COMMENT_TYPE_ALBUM, id, offset, count}, 
				new RowMapper<Comment>() {

					public Comment mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return generateComment(rs);
					}
			
		});
		return comments;
	}

	public int save(final Comment comment) {
		final String sql = "insert into " + TABLE + "(comment_object_type, comment_object_id,"
											+ "comment_author, comment_author_name,"
											+ "comment_content, comment_parent, comment_parent_author, comment_parent_author_name) values(?,?,?,?,?,?,?,?)";
		System.out.println("parent:"+comment.getComment_parent());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, comment.getComment_object_type());
				ps.setInt(2, comment.getComment_object_id());
				ps.setInt(3, comment.getComment_author());
				ps.setString(4, comment.getComment_author_name());
				ps.setString(5, comment.getComment_content());
				ps.setInt(6, comment.getComment_parent());
				ps.setInt(7, comment.getComment_parent_author());
				ps.setString(8, comment.getComment_parent_author_name());
				return ps;
			}
		}, keyHolder);
		
		String key = "comment:"+Dic.checkType(comment.getComment_object_type())+":"+comment.getComment_object_id();
		Integer count = hashOps.get(COUNTER, key);
		
		if(count == null) {
			count = 0;
		}
		hashOps.put(COUNTER, key, count+1);
		
		return keyHolder.getKey().intValue();
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public int commentsCount(int object_type, int object_id){
		String type = Dic.checkType(object_type);
		if(type == null)
			return 0;
		Integer count = hashOps.get(COUNTER, "comment:"+type+":"+object_id);
		return count==null ? 0 : count;
	}
}
