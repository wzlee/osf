package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.NotificationDAO;
import com.lvwang.osf.model.Notification;
import com.lvwang.osf.util.Dic;

@Repository("notificationDao")
public class NotificationDAOImpl implements NotificationDAO{

	private static final String TABLE = "osf_notifications";
	
	private static final String NOTIFY_KEY = "notification:";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, String> redisTemplate; 
	
	@Resource(name="redisTemplate")
	private HashOperations<String, String, Integer> hashOps;
	

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

	public List<Notification> getNotificationsOfType(int user_id,
			int notify_type) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initNotification(Map<String, Integer> notifications){
		notifications.put("comment", 0);
		notifications.put("comment_reply", 0);
		notifications.put("follow", 0);
		notifications.put("like", 0);
		notifications.put("system", 0);
	}
	
	private Map<String, Integer> getNotifications(final Map<String, Integer> notifications, int user_id){
		initNotification(notifications);
		String sql = "select notified_user,notify_type,count(*) count from " 
				 + TABLE + " where notified_user=? group by notified_user,notify_type";			
	
		jdbcTemplate.query(sql, new Object[]{user_id}, new ResultSetExtractor<Integer>(){
	
			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while(rs.next()){
					notifications.put(Dic.toNotifyTypeDesc(rs.getInt("notify_type")), rs.getInt("count"));
				}
				return null;
			}
			
		});
		return notifications;
	}
	
	public void refresh(int user_id){
		Map<String, Integer> notifications = new HashMap<String, Integer>();
		hashOps.putAll(NOTIFY_KEY+user_id, getNotifications(notifications, user_id));
	}
	
	public Map<String, Integer> getNotificationsCount(int user_id) {
		final Map<String, Integer> notifications = new HashMap<String, Integer>();
		
		if(!redisTemplate.hasKey(NOTIFY_KEY+user_id)){
			
			hashOps.putAll(NOTIFY_KEY+user_id, getNotifications(notifications, user_id));

		} else{
			for(String key: hashOps.keys(NOTIFY_KEY+user_id)){
				notifications.put(key, hashOps.get(NOTIFY_KEY+user_id, key));
			}
		}
		return notifications;
	}

}
