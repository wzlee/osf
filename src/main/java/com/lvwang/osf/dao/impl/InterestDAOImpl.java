package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.InterestDAO;

@Repository("interestDao")
public class InterestDAOImpl implements InterestDAO{

	public static String TABLE_INTEREST = "osf_interests";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	
}
