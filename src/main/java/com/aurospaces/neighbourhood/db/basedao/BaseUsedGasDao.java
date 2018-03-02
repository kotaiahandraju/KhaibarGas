
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

import com.aurospaces.neighbourhood.bean.AccessoriesmasterBean;
import com.aurospaces.neighbourhood.bean.AddGasBean;
import com.aurospaces.neighbourhood.bean.UsedGasBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseUsedGasDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO usedgas( created_time, updated_time, fillingStationId,gasInKgs,closedgas,fillingstationname) values (?,?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final UsedGasBean addGasBean) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(addGasBean.getId() == 0)	{
		
		
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(addGasBean.getCreatedTime() == null)
					{
						addGasBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(addGasBean.getCreatedTime().getTime()); 
							
					if(addGasBean.getUpdatedTime() == null)
					{
						addGasBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(addGasBean.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, addGasBean.getFillingStationId());
ps.setString(4, addGasBean.getGasInKgs());
ps.setString(5, addGasBean.getClosedgas());
ps.setString(6, addGasBean.getFillingstationname());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				addGasBean.setId(unId.intValue());
				
		}
		else
		{
			
			String sql = "UPDATE addgas  set fillingStationId = ? ,gasInKgs = ?   where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{addGasBean.getFillingStationId(),addGasBean.getGasInKgs(),addGasBean.getId()});
		}
	}
		
		@Transactional
		public Boolean delete(int id,String status) {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update accessoriesmaster set status='"+status+"' where id = ?";
		 int results=jdbcTemplate.update(sql, new Object[]{id});
			if(results!=0){
				result= true;
			}
			return result;
		}
		

	 public AccessoriesmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from accessoriesmaster where id = ? ";
			List<AccessoriesmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(AccessoriesmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	

	

}