package com.lvwang.osf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lvwang.osf.dao.RelationDAO;
import com.lvwang.osf.model.Relation;

@Repository("relationDao")
public class RelationDAOImpl implements RelationDAO{

	private static final String TABLE = "osf_relations";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	
	public int save(final int object_type, final int object_id, final int tag_id) {
		final String sql = "insert into "+ TABLE + "(object_type, object_id, tag_id) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
				ps.setInt(1, object_type);
				ps.setInt(2, object_id);
				ps.setInt(3, tag_id);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int save(final int object_type, final int object_id, final int[] tags_id) {
		
		String sql = "insert into "+ TABLE + "(object_type, object_id, tag_id) values(?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setInt(1, object_type);
				ps.setInt(2, object_id);
				ps.setInt(3, tags_id[i]);
			}

			public int getBatchSize() {
				return tags_id.length;
			}
			
		});
		return 0;
	}

	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Relation> get(final int tag_id) {
		final String sql = "select * from " + TABLE + " where tag_id = ? order by add_ts";
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, tag_id);
				return ps;
			}
		}, new RowMapper<Relation>() {

			public Relation mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Relation relation  = new Relation();
				relation.setObject_type(rs.getInt("object_type"));
				relation.setObject_id(rs.getInt("object_id"));
				return relation;
			}
		});
	}

	public List<Relation> getRelationsInTags(List<Integer> tags_id) {
		String sql = "select * from " + TABLE + " where tag_id in (:ids)";
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ids", tags_id);
		return namedParaJdbcTemplate.query(sql, paramMap, new RowMapper<Relation>() {

			public Relation mapRow(ResultSet rs, int row)
					throws SQLException {
				Relation relation = new Relation();
				relation.setId(rs.getInt("id"));
				relation.setAdd_ts(rs.getTimestamp("add_ts"));
				relation.setObject_type(rs.getInt("object_type"));
				relation.setObject_id(rs.getInt("object_id"));
				relation.setTag_id(rs.getInt("tag_id"));
				return relation;
			}
		});
		
	}
	
}
