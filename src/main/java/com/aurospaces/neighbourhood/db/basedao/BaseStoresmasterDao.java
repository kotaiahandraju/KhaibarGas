

package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.CustomermasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseStoresmasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO storesmaster( created_time, updated_time, storeid, storename, location, status) values (?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StoresmasterBean storesmaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(storesmaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(storesmaster.getCreatedTime() == null)
					{
					storesmaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(storesmaster.getCreatedTime().getTime()); 
							
					if(storesmaster.getUpdatedTime() == null)
					{
					storesmaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(storesmaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, storesmaster.getStoreid());
ps.setString(4, storesmaster.getStorename());
ps.setString(5, storesmaster.getLocation());
ps.setString(6, storesmaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				storesmaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE storesmaster  set storeid = ? ,storename = ? ,location = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{storesmaster.getStoreid(),storesmaster.getStorename(),storesmaster.getLocation(),storesmaster.getStatus(),storesmaster.getId()});
		}
	}
		
	@Transactional
	public Boolean delete(int id,String status) {
		boolean result=false;
		Integer results=null;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "update storesmaster set status='"+status+"' where id = ?";
		jdbcTemplate.update(sql, new Object[]{id});
		  results=jdbcTemplate.update(sql, new Object[]{id});
			if(results !=null || result){
				result= true;
			}
			return result;
	}
		

	 public StoresmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from storesmaster where id = ? ";
			List<StoresmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(StoresmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	
	

}
