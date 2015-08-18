package com.lvwang.osf.dao.impl;

import java.io.IOException;
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

import com.lvwang.osf.dao.AlbumDAO;
import com.lvwang.osf.model.Album;
import com.lvwang.osf.model.Photo;
import com.lvwang.osf.service.TagService;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

@Repository("albumDao")
public class AlbumDAOImpl implements AlbumDAO{

	private static final String TABLE_ALBUM = "osf_albums";
	private static final String TABLE_PHOTO = "osf_photos";
	
//	private static final String accessKeyID = "UoWSYNNNLNaCv1qp";
//	private static final String accessKeySecret = "cTUhzFOj1jQVShx1FocJ9WNhJf6YjT";
//	private static final String endpoint = "http://oss.aliyuncs.com";
//	private static final String bucket = "osfimgs";
//	private OSSClient client = new OSSClient(endpoint, accessKeyID, accessKeySecret);
	
	private static final String AK = "1mAeoCNoX25n_QiPGK-aS8895kQ4RedWWYb6LCpK";
	private static final String SK = "kJBUkzruYDjmnmx8UDsjMHD2OEw5SzTi36WP2BD4";
	private static final String bucket = "osfimg";
	private Auth auth = Auth.create(AK, SK);
	private BucketManager bucketManager = new BucketManager(auth);
	
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
		UploadManager uploadManager = new UploadManager();
		try {
			uploadManager.put(img.getBytes(), details.getKey(), getUpToken());
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return details.getKey();
		
//		ObjectMetadata meta = new ObjectMetadata();
//		meta.setContentLength(img.getSize());
//		
//		try {
//			//上传到图片服务器
//			PutObjectResult result = client.putObject(bucket, details.getKey(), img.getInputStream(), meta);
//			return result.getETag();
//
//		} catch (OSSException e) {
//			e.printStackTrace();
//		} catch (ClientException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;				
	}
	public String uploadPhoto(byte[] img, String key){
		
		UploadManager uploadManager = new UploadManager();
		try {
			uploadManager.put(img, key, getUpToken());
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
		
//		ObjectMetadata meta = new ObjectMetadata();
//		try {
//			meta.setContentLength(img.available());
//			
//			//上传到图片服务器
//			PutObjectResult result = client.putObject(bucket, key, img, meta);
//			return result.getETag();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
	}
	
	private String getUpToken(){
	    return auth.uploadToken(bucket, null, 3600, new StringMap().
	    		putNotEmpty("returnBody", "{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}"));
	}
	
	public void delPhotoInBucket(String key){
		//client.deleteObject(bucket, key);
		try {
			bucketManager.delete(bucket, key);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
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
		final String sql = "select * from " + TABLE_PHOTO +" where album_id=? order by ts asc";
		List<Photo> photos = jdbcTemplate.query(sql, new Object[]{album_id}, new RowMapper<Photo>(){

			public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Photo photo = new Photo();
				photo.setId(rs.getInt("id"));
				photo.setAlbum_id(rs.getInt("album_id"));
				photo.setDesc(rs.getString("desc"));
				photo.setKey(rs.getString("key"));
				photo.setTs(rs.getTimestamp("ts"));
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

	public void delPhoto(int id) {
		String sql = "delete from " + TABLE_PHOTO + " where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
		
	}

	public Album getAlbumContainPhoto(int photo_id) {
		String sql = "select * from " + TABLE_PHOTO + " t1," + TABLE_ALBUM + " t2 where t1.id=? and t1.album_id=t2.id";
		return jdbcTemplate.query(sql, new Object[]{photo_id}, new ResultSetExtractor<Album>(){

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
			}});
	}
}
