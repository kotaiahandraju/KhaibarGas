
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

import com.aurospaces.neighbourhood.bean.CompanymasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseCompanymasterDao{
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;




 
	public final String INSERT_SQL = "INSERT INTO companymaster( created_time, updated_time, companycode, companyname, contactpersonname, contactpersonmobile, emailid, remarks, typeofcompany, customertype, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final CompanymasterBean companymaster) 
	{
		
		jdbcTemplate = custom.getJdbcTemplate();
	if(companymaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(companymaster.getCreatedTime() == null)
					{
					companymaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(companymaster.getCreatedTime().getTime()); 
							
					if(companymaster.getUpdatedTime() == null)
					{
					companymaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(companymaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, companymaster.getCompanycode());
ps.setString(4, companymaster.getCompanyname());
ps.setString(5, companymaster.getContactpersonname());
ps.setString(6, companymaster.getContactpersonmobile());
ps.setString(7, companymaster.getEmailid());
ps.setString(8, companymaster.getRemarks());
ps.setString(9, companymaster.getTypeofcompany());
ps.setString(10, companymaster.getCustomertype());
ps.setString(11, companymaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				companymaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE companymaster  set companycode = ? ,companyname = ? ,contactpersonname = ? ,contactpersonmobile = ? ,emailid = ? ,remarks = ? ,typeofcompany = ? ,customertype = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{companymaster.getCompanycode(),companymaster.getCompanyname(),companymaster.getContactpersonname(),companymaster.getContactpersonmobile(),companymaster.getEmailid(),companymaster.getRemarks(),companymaster.getTypeofcompany(),companymaster.getCustomertype(),companymaster.getStatus(),companymaster.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM companymaster WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public CompanymasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from companymaster where id = ? ";
			List<CompanymasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(CompanymasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
