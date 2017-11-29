
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

import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseFillingstationmasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO fillingstationmaster( created_time, updated_time, gasavailability, numberoffillingmachines, quantity, gascapacity, availablegas, status,stationname,unitpoint) values (?, ?, ?, ?, ?, ?,?,? ,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final FillingstationmasterBean fillingstationmaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(fillingstationmaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(fillingstationmaster.getCreatedTime() == null)
					{
					fillingstationmaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(fillingstationmaster.getCreatedTime().getTime()); 
							
					if(fillingstationmaster.getUpdatedTime() == null)
					{
					fillingstationmaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(fillingstationmaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, fillingstationmaster.getGasavailability());
ps.setString(4, fillingstationmaster.getNumberoffillingmachines());
ps.setString(5, fillingstationmaster.getQuantity());
ps.setString(6, fillingstationmaster.getGascapacity());
ps.setString(7, fillingstationmaster.getAvailablegas());
ps.setString(8, fillingstationmaster.getStatus());
ps.setString(9, fillingstationmaster.getStationname());
ps.setString(10, fillingstationmaster.getUnitpoint());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				fillingstationmaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE fillingstationmaster  set gasavailability = ? ,numberoffillingmachines = ? ,quantity = ? ,gascapacity = ? ,availablegas = ? ,status = ? ,stationname =?,unitpoint =? where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{fillingstationmaster.getGasavailability(),fillingstationmaster.getNumberoffillingmachines(),fillingstationmaster.getQuantity(),fillingstationmaster.getGascapacity(),fillingstationmaster.getAvailablegas(),fillingstationmaster.getStatus(),fillingstationmaster.getStationname(),fillingstationmaster.getUnitpoint(),fillingstationmaster.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM fillingstationmaster WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public FillingstationmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from fillingstationmaster where id = ? ";
			List<FillingstationmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(FillingstationmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
