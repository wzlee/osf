package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.TagDAO;

@Repository("tagDao")
public class TagDAOImpl implements TagDAO{

	private static final String TABLE = "osf_tags";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(final String tag) {
		final String sql = "insert into "+TABLE + "(tag) values(?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setString(1, tag);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public String getTagByID(int id) {
		
		return null;
	}
	
	public int getTagID(final String tag) {
		final String sql = "select id from "+TABLE+" where tag=?";
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, tag);
				return ps;
			}
		}, new ResultSetExtractor<Integer>() {

			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return rs.getInt("id");
				}
				return 0;
			}
		});
	}
}
