package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.EventDAO;
import com.lvwang.osf.model.Post;
import com.lvwang.osf.util.Dic;

@Repository("eventDao")
public class EventDAOImpl implements EventDAO{

	private static final String TABLE = "osf_events";
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public int savePostEvent(final Post post, final String tags) {
		final String sql = "insert into " + TABLE + "(object_type, object_id, "
					+ "user_id, "
					+ "like_count, share_count, comment_count, "
					+ "title, summary, content,tags) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, Dic.OBJECT_TYPE_POST);
				ps.setInt(2, post.getId());
				ps.setInt(3, post.getPost_author());
				ps.setInt(4, post.getLike_count());
				ps.setInt(5, post.getShare_count());
				ps.setInt(6, post.getComment_count());
				ps.setString(7, post.getPost_title());
				ps.setString(8, post.getPost_excerpt());
				ps.setString(9, null);
				ps.setString(10, tags);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int saveAlbumEvent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int savePhotoEvent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int saveFollowingEvent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int saveFollowedEvent() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
