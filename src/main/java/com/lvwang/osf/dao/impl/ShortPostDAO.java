package com.lvwang.osf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository("shortPostDao")
public class ShortPostDAO extends PostDAOImpl{

	@Override
	public long count(int user_id) {
		final String sql = "select count(1) counter from " + TABLE + " where post_author=? and post_title is null";
		return jdbcTemplate.query(sql, new Object[]{user_id}, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				int user_id = 0;
				if(rs.next()) {
					user_id = rs.getInt("counter");
				}
				return user_id;
			}
			
		});
	}
}
