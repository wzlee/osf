package com.lvwang.osf.model;

import java.util.Date;

public class Comment {
	private int id;
	private int comment_object_type;
	private int comment_object_id;
	private int comment_author;
	private String comment_author_name;
	private String comment_author_email;
	private Date comment_ts;
	private String comment_content;
	private int comment_parent;
	private String comment_parent_email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComment_object_type() {
		return comment_object_type;
	}
	public void setComment_object_type(int comment_object_type) {
		this.comment_object_type = comment_object_type;
	}
	public int getComment_object_id() {
		return comment_object_id;
	}
	public void setComment_object_id(int comment_object_id) {
		this.comment_object_id = comment_object_id;
	}
	public int getComment_author() {
		return comment_author;
	}
	public void setComment_author(int comment_author) {
		this.comment_author = comment_author;
	}
	public String getComment_author_email() {
		return comment_author_email;
	}
	public void setComment_author_email(String comment_author_email) {
		this.comment_author_email = comment_author_email;
	}
	public Date getComment_ts() {
		return comment_ts;
	}
	public void setComment_ts(Date comment_ts) {
		this.comment_ts = comment_ts;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_parent() {
		return comment_parent;
	}
	public void setComment_parent(int comment_parent) {
		this.comment_parent = comment_parent;
	}
	public String getComment_author_name() {
		return comment_author_name;
	}
	public void setComment_author_name(String comment_author_name) {
		this.comment_author_name = comment_author_name;
	}
	public String getComment_parent_email() {
		return comment_parent_email;
	}
	public void setComment_parent_email(String comment_parent_email) {
		this.comment_parent_email = comment_parent_email;
	}
}
