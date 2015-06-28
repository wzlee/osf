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

import com.lvwang.osf.dao.NotificationDAO;
import com.lvwang.osf.model.Notification;

@Repository("notificationDao")
public class NotificationDAOImpl implements NotificationDAO{

	private static final String TABLE = "osf_notifications";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(final Notification notification) {
		final String sql = "insert into "+ TABLE + " (notify_type,notify_id, object_type, object_id, notified_user, notifier) "
							+ " values(?,?,?,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, notification.getNotify_type());
				ps.setInt(2, notification.getNotify_id());
				ps.setInt(3, notification.getObject_type());
				ps.setInt(4, notification.getObject_id());
				ps.setInt(5, notification.getNotified_user());
				ps.setInt(6, notification.getNotifier());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public Notification get(int notification_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Notification> getAllOfUser(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
