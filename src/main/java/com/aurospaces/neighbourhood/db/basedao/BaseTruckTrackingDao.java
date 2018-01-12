
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

import com.aurospaces.neighbourhood.bean.TruckTrackingBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseTruckTrackingDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO truck_tracking( created_time, updated_time, truckId, outDate, location, ReadingKM, PetrolLevel, driverName,truckStatus,status,tableJoinId) values (?,?,?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TruckTrackingBean truckTracking) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(truckTracking.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(truckTracking.getCreatedTime() == null)
					{
					truckTracking.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(truckTracking.getCreatedTime().getTime()); 
							
					if(truckTracking.getUpdatedTime() == null)
					{
					truckTracking.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(truckTracking.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, truckTracking.getTruckId());
ps.setString(4, truckTracking.getOutDate());
ps.setString(5, truckTracking.getLocation());
ps.setString(6, truckTracking.getReadingKM());
ps.setString(7, truckTracking.getPetrolLevel());
ps.setString(8, truckTracking.getDriverName());
ps.setString(9, truckTracking.getTruckStatus());
ps.setString(10, truckTracking.getStatus());
ps.setString(11, truckTracking.getTableJoinId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				truckTracking.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE truck_tracking  set truckId = ? ,outDate = ? ,location = ? ,ReadingKM = ? ,PetrolLevel = ? ,driverName = ?,truckStatus=?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{truckTracking.getTruckId(),truckTracking.getOutDate(),truckTracking.getLocation(),truckTracking.getReadingKM(),truckTracking.getPetrolLevel(),truckTracking.getDriverName(),truckTracking.getTruckStatus(),truckTracking.getId()});
		}
	}
		
		@Transactional
		public boolean delete(int id,String status) {
			jdbcTemplate = custom.getJdbcTemplate();
			boolean delete = false;
			try{
				String sql = "update truck_tracking set status='"+status+"'  WHERE id=?";
				int intDelete = jdbcTemplate.update(sql, new Object[]{id});
				if(intDelete != 0){
					delete = true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return delete;
		}
		

	 public TruckTrackingBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from truck_tracking where id = ? ";
			List<TruckTrackingBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(TruckTrackingBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
