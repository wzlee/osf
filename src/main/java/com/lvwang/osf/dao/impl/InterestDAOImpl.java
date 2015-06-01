package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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

}
