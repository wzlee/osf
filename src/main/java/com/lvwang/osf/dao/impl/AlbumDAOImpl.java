package com.lvwang.osf.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
import com.lvwang.osf.model.User;
import com.lvwang.osf.service.TagService;

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
	
	@Autowired
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	
	public int saveAlbum(final Album album) {
		final String sql = "insert into " + TABLE_ALBUM + 
							"(user_id, album_title, album_desc, status, cover) values(?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, album.getUser_id());
				ps.setString(2, album.getAlbum_title());
				ps.setString(3, album.getAlbum_desc());
				ps.setInt(4, album.getStatus());
				ps.setString(5, album.getCover());
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
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;				
	}
	public String uploadPhoto(InputStream img, String key){
		ObjectMetadata meta = new ObjectMetadata();
		try {
			meta.setContentLength(img.available());
			
			//上传到图片服务器
			PutObjectResult result = client.putObject(bucket, key, img, meta);
			return result.getETag();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void delPhotoInBucket(String key){
		client.deleteObject(bucket, key);
	}
	

	public int getAlbumUser(int id) {
		String sql = "select user_id from "+TABLE_ALBUM + " where id=?";
		int user_id = (Integer)jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
		return user_id;
	}

	public int getAlbum(int user_id, int status) {
		String sql = "select id from " + TABLE_ALBUM + " where user_id=? and status=?";
		return jdbcTemplate.query(sql, new Object[]{user_id, status}, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return rs.getInt("id");
				} 
				return 0;
			}
			
		});
	}

	public List<Photo> getPhotos(int album_id) {
		final String sql = "select * from " + TABLE_PHOTO +" where album_id=?";
		List<Photo> photos = jdbcTemplate.query(sql, new Object[]{album_id}, new RowMapper<Photo>(){

			public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Photo photo = new Photo();
				photo.setId(rs.getInt("id"));
				photo.setAlbum_id(rs.getInt("album_id"));
				photo.setDesc(rs.getString("desc"));
				photo.setKey(rs.getString("key"));
				return photo;
			}
			
		});
		return photos;
	}

	public int updateAlbumDesc(int album_id, String album_desc, int album_status) {
		String sql = "update "+ TABLE_ALBUM + " set album_desc=?, status=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{album_desc, album_status, album_id});
	}

	public int updatePhotoDesc(int photo_id, String photo_desc) {
		String sql = "update " + TABLE_PHOTO+ " set `desc`=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{photo_desc, photo_id});
	}

	public Album getAlbum(final int id) {
		final String sql = "select * from " + TABLE_ALBUM + " where id=?";
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				return ps;
			}
		}, new ResultSetExtractor<Album>() {

			public Album extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Album album = new Album();
				if(rs.next()) {
					album.setAlbum_desc(rs.getString("album_desc"));
					album.setAlbum_title(rs.getString("album_title"));
					album.setCover(rs.getString("cover"));
					album.setId(rs.getInt("id"));
					album.setUser_id(rs.getInt("user_id"));
					album.setLast_add_ts(rs.getTimestamp("last_add_ts"));
					album.setCreate_ts(rs.getTimestamp("create_ts"));
					album.setAlbum_tags(TagService.toList(rs.getString("album_tags")));
				}
				return album;
			}
		});
	}

	public int updateAlbumCover(int album_id, String cover) {
		String sql = "update " + TABLE_ALBUM + " set cover=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{cover, album_id});
	}

	public String getKey(int id) {
		String sql = "select `key` from " + TABLE_PHOTO + " where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
		
	}
	public List<String> getKeys(List<Integer> ids) {
		String sql = "select `key` from " + TABLE_PHOTO + " where id in (:ids)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ids", ids);
		return namedParaJdbcTemplate.queryForList(sql, paramMap, String.class);
	}
	
	
	public int updatePhotosCount(int album_id, int count) {
		String sql = "update " + TABLE_ALBUM + " set photos_count=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{count, album_id});
	}

	public List<Album> getAlbumsOfUser(final int id) {
		final String sql = "select * from " + TABLE_ALBUM + " where user_id=?";
		List<Album> albums = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Album>(){

			public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
				Album album = new Album();
				album.setAlbum_desc(rs.getString("album_desc"));
				album.setAlbum_title(rs.getString("album_title"));
				album.setCover(rs.getString("cover"));
				album.setId(rs.getInt("id"));
				album.setUser_id(rs.getInt("user_id"));
				album.setLast_add_ts(rs.getTimestamp("last_add_ts"));
				album.setCreate_ts(rs.getTimestamp("create_ts"));
				return album;
			}});
		return albums;
	}

	public int getAuthorOfAlbum(int id) {
		final String sql = "select * from " + TABLE_ALBUM + " where id=?";
		return jdbcTemplate.query(sql, new Object[]{id}, new ResultSetExtractor<Integer>(){

			public Integer extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				int user_id = 0;
				if(rs.next()) {
					user_id = rs.getInt("user_id");
				}
				return user_id;
			}
			
		});
	}

	public int updateAlbumInfo(Album album) {
		String sql = "update " + TABLE_ALBUM + 
					 " set album_desc=?, photos_count=?, status=?,cover=?,album_tags=? where id=?";
		return jdbcTemplate.update(sql, new Object[]{album.getAlbum_desc(),
													 album.getPhotos_count(),
													 album.getStatus(),
													 album.getCover(),
													 TagService.toString(album.getAlbum_tags()),
													 album.getId()});
	}
}
