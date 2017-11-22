
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
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseAccessoriesmasterDao{
	

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO accessoriesmaster( created_time, updated_time, typeofaccessory, suppliername, madein, lponumber, accessoriesstatus, remarks, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final AccessoriesmasterBean accessoriesmaster) 
	{
		
		jdbcTemplate = custom.getJdbcTemplate();
	if(accessoriesmaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(accessoriesmaster.getCreatedTime() == null)
					{
					accessoriesmaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(accessoriesmaster.getCreatedTime().getTime()); 
							
					if(accessoriesmaster.getUpdatedTime() == null)
					{
					accessoriesmaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(accessoriesmaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, accessoriesmaster.getTypeofaccessory());
ps.setString(4, accessoriesmaster.getSuppliername());
ps.setString(5, accessoriesmaster.getMadein());
ps.setString(6, accessoriesmaster.getLponumber());
ps.setString(7, accessoriesmaster.getAccessoriesstatus());
ps.setString(8, accessoriesmaster.getRemarks());
ps.setString(9, accessoriesmaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				accessoriesmaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE accessoriesmaster  set typeofaccessory = ? ,suppliername = ? ,madein = ? ,lponumber = ? ,accessoriesstatus = ? ,remarks = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{accessoriesmaster.getTypeofaccessory(),accessoriesmaster.getSuppliername(),accessoriesmaster.getMadein(),accessoriesmaster.getLponumber(),accessoriesmaster.getAccessoriesstatus(),accessoriesmaster.getRemarks(),accessoriesmaster.getStatus(),accessoriesmaster.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM accessoriesmaster WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
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
