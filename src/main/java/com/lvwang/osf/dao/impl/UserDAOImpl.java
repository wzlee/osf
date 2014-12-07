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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.UserDAO;
import com.lvwang.osf.model.User;

@Repository("userDao")
public class UserDAOImpl implements UserDAO{

	private static final String TABLE = "osf_users"; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private User getUser(String sql, Object condition ) {
		User user = jdbcTemplate.queryForObject(sql, new Object[]{condition}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				User user = new User();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_registered_date(rs.getDate("user_registered_date"));
				user.setUser_status(rs.getInt("user_status"));
				return user;
				
			}
	
		});

		return user;
	}
	
	@Override
	public User getUserByID(int id) {
		String sql = "select * from "+TABLE + " where id=?";
		User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, User.class);
		/*
		User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setUser_name(rs.getString("user_name"));
						user.setUser_email(rs.getString("user_email"));
						user.setUser_registered_date(rs.getDate("user_registered_date"));
						user.setUser_status(rs.getInt("user_status"));
						return user;
						
					}
			
		});
		*/
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "select * from " + TABLE + " where user_email=?";

		User user = jdbcTemplate.query(sql, new Object[]{email}, new ResultSetExtractor<User>(){
			@Override
			public User extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				User user = null;
				if(rs.next()) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setUser_name(rs.getString("user_name"));
					user.setUser_email(rs.getString("user_email"));
					user.setUser_pwd(rs.getString("user_pwd"));
					user.setUser_registered_date(rs.getDate("user_registered_date"));
					user.setUser_status(rs.getInt("user_status"));	
				}
				return user;
			}
		});
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "select * from " + TABLE + " where user_name=?";
		User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				
				user.setUser_name(rs.getString("user_name"));
				user.setUser_email(rs.getString("user_email"));
				user.setUser_registered_date(rs.getDate("user_registered_date"));
				user.setUser_status(rs.getInt("user_status"));
				return user;
			}
		});
		
		return user;
	}

	@Override
	public String getPwdByUsername(String username) {
		
		return null;
	}

	//返回生成主键 user id
	@Override
	public int save(final User user) {
		final String sql = "insert into " + TABLE + 
					 "(user_email, user_pwd) values(?,?)";
		//jdbcTemplate.update(sql);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUser_email());
				ps.setString(2, user.getUser_pwd());
				return ps;
			}
		}, keyHolder );
		return keyHolder.getKey().intValue();
		
	}

	//delete user by user id
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from " + TABLE + " where id=";
		jdbcTemplate.update(sql, id);
		
		
	}
	
}
