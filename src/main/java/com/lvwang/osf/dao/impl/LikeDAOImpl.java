package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.LikeDAO;
import com.lvwang.osf.util.Dic;

@Repository("likeDao")
public class LikeDAOImpl implements LikeDAO{

	private static final String TABLE = "osf_likes";
	
	private static final String LIKE_KEY = "like:";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, String> redisTemplate; 
	
	@Resource(name="redisTemplate")
	private SetOperations<String, Integer> setOps;
	

	public void like(final int user_id, final int object_type, final int object_id) {
		//save to db
		final String sql = "insert into " + TABLE + " (user_id, object_type, object_id) values(?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, object_type);
				ps.setInt(3, object_id);
				return ps;
			}
		});
		
		//cache
		cacheLikers(object_type, object_id);
		setOps.add(LIKE_KEY+Dic.checkType(object_type) + ":"+object_id,	user_id);
	}

	public void undoLike(final int user_id, final int object_type, final int object_id) {
		//remove from db
		final String sql = "delete from " + TABLE + " where user_id=? and object_type=? and object_id=?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, object_type);
				ps.setInt(3, object_id);
				return ps;
			}
		});
		//remove from cache
		cacheLikers(object_type, object_id);
		setOps.remove(LIKE_KEY+Dic.checkType(object_type) + ":"+object_id,	user_id);
	}
	
	
	public boolean isLike(int user_id, int object_type, int object_id) {	
		cacheLikers(object_type, object_id);
		return setOps.isMember(LIKE_KEY+Dic.checkType(object_type) + ":"+object_id, user_id);
	}

	public long likersCount(int object_type, int object_id) {
		cacheLikers(object_type, object_id);
		return setOps.size(LIKE_KEY+Dic.checkType(object_type) + ":"+object_id);
	}

	private void cacheLikers(int object_type, int object_id){
		getLikers(object_type, object_id);
	}
	
	public List<Integer> getLikers(int object_type, int object_id) {
		//key e.g like:post:1
		final String key = LIKE_KEY+Dic.checkType(object_type) + ":"+object_id;
		List<Integer> likers = null;
		if(!redisTemplate.hasKey(key) ){
			final String sql = "select user_id from " + TABLE + " where object_type=? and object_id=?";
			likers = jdbcTemplate.query(sql, new Object[]{object_type, object_id},  new RowMapper<Integer>(){

				public Integer mapRow(ResultSet rs, int row)
						throws SQLException {
					setOps.add(key, rs.getInt("user_id"));
					return rs.getInt("user_id");
				}
				
			});
			
		} 
		return likers;
	}


}
