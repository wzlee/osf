package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.FollowDAO;
import com.lvwang.osf.model.Follower;
import com.lvwang.osf.model.Following;
import com.lvwang.osf.util.OSFUtils;

@Repository("followDao")
public class FollowDAOImpl implements FollowDAO{

	private static final String TABLE_FOLLOWING = "osf_followings";
	private static final String TABLE_FOLLOWER = "osf_followers";
	
	private static final String FOLLOWING_KEY = "following:user:";
	private static final String FOLLOWER_KEY = "follower:user:";
	
	private static final int FOLLOW_SCAN_COUNT = 10;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, String> redisTemplate; 
	
	
	@Resource(name="redisTemplate")
	private ListOperations<String, Integer> listOps;
	
	@Resource(name="redisTemplate")
	private SetOperations<String, Integer> setOps;
	
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
		
		setOps.add(FOLLOWING_KEY+following.getUser_id(), following.getFollowing_user_id());
		
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
		
		setOps.add(FOLLOWER_KEY+follower.getUser_id() ,follower.getFollower_user_id());
		
		return keyHolder.getKey().intValue();
	}

	public long getFollowersCount(int user_id) {
		return setOps.size(FOLLOWER_KEY+user_id);
	}
	
	public long getFollowingsCount(int user_id) {
		return setOps.size(FOLLOWING_KEY+user_id);
	}
	
	public List<Integer> getFollowingIDs(int user_id) {
//		Cursor<Integer> cursor = setOps.scan(FOLLOWING_KEY+user_id, ScanOptions.scanOptions().count(FOLLOW_SCAN_COUNT).build());
//		//List<Integer> following_ids = listOps.range(FOLLOWING_KEY+user_id, 0, listOps.size(FOLLOWING_KEY+user_id)-1);
//		
//		
//		while(cursor.hasNext()) {
//			following_ids.add(cursor.next());
//		}
		Set<Integer> members = setOps.members(FOLLOWING_KEY+user_id);
		return OSFUtils.toList(members);
		
	}
	
	public List<Integer> getFollowerIDs(int user_id) {
//		Cursor<Integer> cursor = setOps.scan(FOLLOWER_KEY+user_id, ScanOptions.scanOptions().count(FOLLOW_SCAN_COUNT).build());
//		List<Integer> follower_ids = new ArrayList<Integer>();
//		while(cursor.hasNext()) {
//			follower_ids.add(cursor.next());
//		}
		Set<Integer> members = setOps.members(FOLLOWER_KEY+user_id);
		return OSFUtils.toList(members);
	}
	
	public List<Following> getFollowings(int user_id) {
		
		final String sql = "select * from " + TABLE_FOLLOWING + " where user_id=?";
		List<Following> followings =  jdbcTemplate.query(sql, new Object[]{user_id}, new RowMapper<Following>() {
			public Following mapRow(ResultSet rs, int rowNum) throws SQLException {
				Following following = new Following();
				following.setId(rs.getInt("id"));
				following.setUser_id(rs.getInt("user_id"));
				following.setFollowing_user_id(rs.getInt("following_user_id"));
				following.setFollowing_user_name(rs.getString("following_user_name"));
				following.setTs(rs.getTimestamp("ts"));
				return following;
			}			
		});
		return followings;
	}

	public List<Follower> getFollowers(final int user_id) {
		final String sql = "select * from " + TABLE_FOLLOWER + " where user_id=?";
		List<Follower> followers =  jdbcTemplate.query(sql, new Object[]{user_id}, new RowMapper<Follower>() {
			public Follower mapRow(ResultSet rs, int rowNum) throws SQLException {
				Follower follower = new Follower();
				follower.setId(rs.getInt("id"));
				follower.setUser_id(rs.getInt("user_id"));
				follower.setFollower_user_id(rs.getInt("follower_user_id"));
				follower.setFollower_user_name(rs.getString("follower_user_name"));
				follower.setTs(rs.getTimestamp("ts"));
				return follower;
			}			
		});
		return followers;
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
		setOps.remove(FOLLOWING_KEY+following.getUser_id(), following.getFollowing_user_id());
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
		
		setOps.remove(FOLLOWER_KEY+follower.getUser_id(), follower.getFollower_user_id());
		return effrows==1?true:false;	
	}

	public boolean hasFollowing(int user_a, int user_b) {
		return setOps.isMember(FOLLOWING_KEY+user_a, user_b);
	}
	
	public boolean hasFollower(int user_a, int user_b) {
		return setOps.isMember(FOLLOWER_KEY+user_a, user_b);
	}

	private void initCheckResult(Map<Integer, Boolean> map, List<Integer> following_ids){
		for(Integer id: following_ids){
			map.put(id, false);
		}
	}

	public Map<Integer, Boolean> isFollowingUsers(int user_id,
			List<Integer> following_ids) {
		
		final Map<Integer, Boolean> result = new HashMap<Integer, Boolean>();
		initCheckResult(result, following_ids);
		
		String sql = "select following_user_id from " 
					+ TABLE_FOLLOWING +" where user_id=:user_id and following_user_id in (:following_ids)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("following_ids", following_ids);
		namedParaJdbcTemplate.query(sql, paramMap, new ResultSetExtractor<Boolean>() {

			public Boolean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				while(rs.next()){
					result.put(rs.getInt("following_user_id"), true);
				}
				
				return true;
			}
			
		});
		return result;
	}
}
