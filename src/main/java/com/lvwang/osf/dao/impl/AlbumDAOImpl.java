package com.lvwang.osf.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.lvwang.osf.dao.AlbumDAO;
import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;

@Repository("albumDao")
public class AlbumDAOImpl implements AlbumDAO{

	private static final String TABLE_ALBUM = "osf_albums";
	private static final String TABLE_PHOTO = "osf_photos";
	
	private static final String accessKeyID = "UoWSYNNNLNaCv1qp";
	private static final String accessKeySecret = "cTUhzFOj1jQVShx1FocJ9WNhJf6YjT";
	private static final String endpoint = "http://oss.aliyuncs.com";
	private static final String bucket = "osfimgs";
	private OSSClient client = new OSSClient(endpoint, accessKeyID, accessKeySecret);
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	
	public int saveAlbum(final Album album) {
		final String sql = "insert into " + TABLE_ALBUM + "(user_id, album_title, album_desc) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, album.getUser_id());
				ps.setString(2, album.getAlbum_title());
				ps.setString(3, album.getAlbum_desc());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int savePhoto(final Photo photo) {
		final String sql = "insert into " + TABLE_PHOTO + "(`key`, album_id, `desc`) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setString(1, photo.getKey());
				ps.setInt(2, photo.getAlbum_id());
				ps.setString(3, photo.getDesc());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public String uploadPhoto(MultipartFile img, Photo details) {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(img.getSize());
		
		try {
			//上传到图片服务器
			PutObjectResult result = client.putObject(bucket, details.getKey(), img.getInputStream(), meta);
			return result.getETag();

		} catch (OSSException e) {
			e.printStackTrace();
			return null;
		} catch (ClientException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	public int getAlbumUser(int id) {
		String sql = "select user_id from "+TABLE_ALBUM + " where id=?";
		int user_id = (Integer)jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
		return user_id;
	}


}
