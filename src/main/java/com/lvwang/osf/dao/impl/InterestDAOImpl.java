package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.InterestDAO;
import com.lvwang.osf.model.Tag;

@Repository("interestDao")
public class InterestDAOImpl implements InterestDAO{

	public static String TABLE_INTEREST = "osf_interests";
	public static String TABLE_TAG 		= "osf_tags";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	
	public void saveInterest(final int user_id, final int tag_id) {
		final String sql = "insert into " + TABLE_INTEREST + " (user_id, tag_id) values(?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, tag_id);
				return ps;
			}
		});
		
		
	}

	public void delInterest(final int user_id, final int tag_id) {
		final String sql = "delete from " + TABLE_INTEREST +" where user_id=? and tag_id=?";
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, tag_id);
				return ps;
			}
		});
		
	}

	public List<Integer> getUsersInterestInTag(int tag_id) {
		String sql = "select * from " + TABLE_INTEREST + " where tag_id=?";
		List<Integer> users = jdbcTemplate.query(sql, new Object[]{tag_id}, new RowMapper<Integer>(){
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("user_id");
			}		
		});
		return users;
	}

	public boolean hasInterestInTag(int user_id, int tag_id) {
		String sql = "select count(*) count from " +TABLE_INTEREST + " where user_id=? and tag_id=?";
		int count = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return rs.getInt("count");
				}
				return 0;
			}
			
		}, new Object[]{user_id, tag_id});
		return count==0?false:true;
	}
	
	private void initCheckResult(Map<Integer, Boolean> map, List<Integer> tags_id){
		for(Integer id: tags_id){
			map.put(id, false);
		}
	}
	
	public Map<Integer, Boolean> hasInterestInTags(int user_id, List<Integer> tags_id){
		final Map<Integer, Boolean> result = new HashMap<Integer, Boolean>();
		initCheckResult(result, tags_id);
		
		String sql = "select tag_id from " + TABLE_INTEREST +" where user_id=:user_id and tag_id in (:tags_id)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("tags_id", tags_id);
		namedParaJdbcTemplate.query(sql, paramMap, new ResultSetExtractor<Boolean>() {

			public Boolean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				while(rs.next()){
					result.put(rs.getInt("tag_id"), true);
				}
				
				return true;
			}
			
		});
		return result;
	}

	public List<Tag> getTagsUserInterestedIn(int user_id) {
		
		String sql = "select t2.* from " + TABLE_INTEREST + " t1, " + TABLE_TAG + " t2 " +
					 " where t1.user_id=? and t1.tag_id=t2.id";
		return jdbcTemplate.query(sql, new Object[]{user_id}, new RowMapper<Tag>(){

			public Tag mapRow(ResultSet rs, int row) throws SQLException {
				Tag tag = new Tag();
				tag.setId(rs.getInt("id"));
				tag.setAdd_ts(rs.getTimestamp("add_ts"));
				tag.setCover(rs.getString("cover"));
				tag.setTag(rs.getString("tag"));
				return tag;
			}
			
		});
	}
	
}
