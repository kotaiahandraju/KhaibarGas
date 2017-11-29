
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

import com.aurospaces.neighbourhood.bean.StaffmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseStaffmasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO staffmaster( created_time, updated_time, staffcode, staffno, designation, nationality, firstname, lastname, mobile, customertype, documents, active, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StaffmasterBean staffmaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(staffmaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(staffmaster.getCreatedTime() == null)
					{
					staffmaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(staffmaster.getCreatedTime().getTime()); 
							
					if(staffmaster.getUpdatedTime() == null)
					{
					staffmaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(staffmaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, staffmaster.getStaffcode());
ps.setString(4, staffmaster.getStaffno());
ps.setString(5, staffmaster.getDesignation());
ps.setString(6, staffmaster.getNationality());
ps.setString(7, staffmaster.getFirstname());
ps.setString(8, staffmaster.getLastname());
ps.setString(9, staffmaster.getMobile());
ps.setString(10, staffmaster.getCustomertype());
ps.setString(11, staffmaster.getDocuments());
ps.setString(12, staffmaster.getActive());
ps.setString(13, staffmaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				staffmaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE staffmaster  set staffcode = ? ,staffno = ? ,designation = ? ,nationality = ? ,firstname = ? ,lastname = ? ,mobile = ? ,customertype = ? ,documents = ? ,active = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{staffmaster.getStaffcode(),staffmaster.getStaffno(),staffmaster.getDesignation(),staffmaster.getNationality(),staffmaster.getFirstname(),staffmaster.getLastname(),staffmaster.getMobile(),staffmaster.getCustomertype(),staffmaster.getDocuments(),staffmaster.getActive(),staffmaster.getStatus(),staffmaster.getId()});
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
		
		

	 public StaffmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from staffmaster where id = ? ";
			List<StaffmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(StaffmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
