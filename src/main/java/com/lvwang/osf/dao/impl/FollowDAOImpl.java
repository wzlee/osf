package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.FollowDAO;
import com.lvwang.osf.model.Follower;
import com.lvwang.osf.model.Following;

@Repository("followDao")
public class FollowDAOImpl implements FollowDAO{

	private static final String TABLE_FOLLOWING = "osf_followings";
	private static final String TABLE_FOLLOWER = "osf_followers";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int saveFollowing(final Following following) {
		final String sql = "insert into " + TABLE_FOLLOWING + "(user_id, user_name, "
															+ "following_user_id, "
															+ "following_user_name) "
															+ "values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, following.getUser_id());
				ps.setString(2, following.getUser_name());
				ps.setInt(3, following.getFollowing_user_id());
				ps.setString(4, following.getFollowing_user_name());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int saveFollower(final Follower follower) {
		final String sql = "insert into " + TABLE_FOLLOWER + "(user_id, user_name, "
															+ "follower_user_id, "
															+ "follower_user_name) "
															+ "values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
		
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, follower.getUser_id());
				ps.setString(2, follower.getUser_name());
				ps.setInt(3, follower.getFollower_user_id());
				ps.setString(4, follower.getFollower_user_name());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<Following> getFollowings(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Follower> getFollowers(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delFollowing(final Following following) {
		final String sql = "delete from " + TABLE_FOLLOWING + " where user_id=? and following_user_id=?"; 
		int effrows = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, following.getUser_id());
				ps.setInt(2, following.getFollowing_user_id());
				return ps;
			}
		});
		return effrows==1?true:false;
	}

	public boolean delFollower(final Follower follower) {
		final String sql = "delete from " + TABLE_FOLLOWER + " where user_id=? and follower_user_id=?"; 
		int effrows = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, follower.getUser_id());
				ps.setInt(2, follower.getFollower_user_id());
				return ps;
			}
		});
		return effrows==1?true:false;
		
		
	}

}
