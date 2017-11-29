
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseTrucksmasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO trucksmaster( created_time, updated_time, trucknumber, registrationexpirydate, civildefensecardexpirydate, servicedue, make, description, capacityoftruck, lponumber, status,typeOfService) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TrucksmasterBean trucksmaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(trucksmaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(trucksmaster.getCreatedTime() == null)
					{
					trucksmaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(trucksmaster.getCreatedTime().getTime()); 
							
					if(trucksmaster.getUpdatedTime() == null)
					{
					trucksmaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(trucksmaster.getUpdatedTime().getTime()); 
							
					if(trucksmaster.getRegistrationexpirydate() == null)
					{
					trucksmaster.setRegistrationexpirydate( new Date());
					}
					java.sql.Timestamp registrationexpirydate = 
						new java.sql.Timestamp(trucksmaster.getRegistrationexpirydate().getTime()); 
							
					if(trucksmaster.getCivildefensecardexpirydate() == null)
					{
					trucksmaster.setCivildefensecardexpirydate( new Date());
					}
					java.sql.Timestamp civildefensecardexpirydate = 
						new java.sql.Timestamp(trucksmaster.getCivildefensecardexpirydate().getTime()); 
					
					if(trucksmaster.getServicedue()== null)
					{
					trucksmaster.setServicedue( new Date());
					}
					java.sql.Timestamp servicedue = 
						new java.sql.Timestamp(trucksmaster.getCivildefensecardexpirydate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, trucksmaster.getTrucknumber());
ps.setTimestamp(4, registrationexpirydate);
ps.setTimestamp(5, civildefensecardexpirydate);
ps.setTimestamp(6, servicedue);
ps.setString(7, trucksmaster.getMake());
ps.setString(8, trucksmaster.getDescription());
ps.setString(9, trucksmaster.getCapacityoftruck());
ps.setString(10, trucksmaster.getLponumber());
ps.setString(11, trucksmaster.getStatus());
ps.setString(12, trucksmaster.getTypeOfService());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				trucksmaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE trucksmaster  set trucknumber = ? ,registrationexpirydate = ? ,civildefensecardexpirydate = ? ,servicedue = ? ,make = ? ,description = ? ,capacityoftruck = ? ,lponumber = ? ,status = ?,typeOfService=?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{trucksmaster.getTrucknumber(),trucksmaster.getRegistrationexpirydate(),trucksmaster.getCivildefensecardexpirydate(),trucksmaster.getServicedue(),trucksmaster.getMake(),trucksmaster.getDescription(),trucksmaster.getCapacityoftruck(),trucksmaster.getLponumber(),trucksmaster.getStatus(),trucksmaster.getTypeOfService(), trucksmaster.getId()});
		}
	}
		
	@Transactional
	public boolean delete(int id,String status) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		try{
			String sql = "update trucksmaster set status='"+status+"' where id = ?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{id});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}
	
		

	 public TrucksmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from trucksmaster where id = ? ";
			List<TrucksmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(TrucksmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
