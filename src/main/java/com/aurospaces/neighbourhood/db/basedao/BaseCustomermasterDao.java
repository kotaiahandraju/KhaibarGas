
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
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseCustomermasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO customermaster( created_time, updated_time, customerid, customername, customeraddress, mobile, landline, authorizedperson, contactperson, customertype, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final CustomermasterBean customermaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(customermaster.getId() == 0)	{
		
		
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(customermaster.getCreatedTime() == null)
					{
					customermaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(customermaster.getCreatedTime().getTime()); 
							
					if(customermaster.getUpdatedTime() == null)
					{
					customermaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(customermaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, customermaster.getCustomerid());
ps.setString(4, customermaster.getCustomername());
ps.setString(5, customermaster.getCustomeraddress());
ps.setString(6, customermaster.getMobile());
ps.setString(7, customermaster.getLandline());
ps.setString(8, customermaster.getAuthorizedperson());
ps.setString(9, customermaster.getContactperson());
ps.setString(10, customermaster.getCustomertype());
ps.setString(11, customermaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				customermaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE customermaster  set customerid = ? ,customername = ? ,customeraddress = ? ,mobile = ? ,landline = ? ,authorizedperson = ? ,contactperson = ? ,customertype = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{customermaster.getCustomerid(),customermaster.getCustomername(),customermaster.getCustomeraddress(),customermaster.getMobile(),customermaster.getLandline(),customermaster.getAuthorizedperson(),customermaster.getContactperson(),customermaster.getCustomertype(),customermaster.getStatus(),customermaster.getId()});
		}
	}
		
		@Transactional
		public Boolean delete(int id,String status) {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update customermaster set status='"+status+"' where id = ?";
			jdbcTemplate.update(sql, new Object[]{id});
			 int results=jdbcTemplate.update(sql, new Object[]{id});
				if(results!=0){
					result= true;
				}
				return result;
		}
		

	 public CustomermasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from customermaster where id = ? ";
			List<CustomermasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(CustomermasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<CustomermasterBean> getByMobileNo(String sMobileNo) {
		 List<CustomermasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from customermaster where mobile = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{sMobileNo},
			ParameterizedBeanPropertyRowMapper.newInstance(CustomermasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}

	

}
