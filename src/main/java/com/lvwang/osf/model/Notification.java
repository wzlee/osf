package com.lvwang.osf.model;

public class Notification {
	private int id;
	private int notify_type;	//通知类型 Dic里有定义
	private int notify_id;		//通告对象ID,如评论的ID
	private int object_type;	//被通告的对象类型 Dic里有定义
	private int object_id;		//被通告对象的ID 
	private int notified_user;	//被通告的用户
	private int notifier;		//通告者
	
	public Notification(){
		
	}
	
	public Notification(int notify_type, int notify_id, int object_type, int object_id,int notified_user, int notifier){
		this.notified_user = notified_user;
		this.notify_type = notify_type;
		this.notify_id = notify_id;
		this.object_id = object_id;
		this.object_type = object_type;
		this.notifier = notifier;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(int notify_type) {
		this.notify_type = notify_type;
	}
	public int getObject_type() {
		return object_type;
	}
	public void setObject_type(int object_type) {
		this.object_type = object_type;
	}
	public int getObject_id() {
		return object_id;
	}
	public void setObject_id(int object_id) {
		this.object_id = object_id;
	}
	public int getNotified_user() {
		return notified_user;
	}
	public void setNotified_user(int notified_user) {
		this.notified_user = notified_user;
	}
	public int getNotifier() {
		return notifier;
	}
	public void setNotifier(int notifier) {
		this.notifier = notifier;
	}

	public int getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(int notify_id) {
		this.notify_id = notify_id;
	}
	
}
