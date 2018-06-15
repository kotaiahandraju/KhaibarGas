
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

import com.aurospaces.neighbourhood.bean.TariffmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseCustomerTariffmasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO customertariffmaster( created_time, updated_time, assetcode, assetdescription, rate, alloweddiscount, remarks, status,itemId,customerId,vatallow) values (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TariffmasterBean tariffmaster,boolean status) 
	{
		if(tariffmaster.getCreatedTime() == null)
		{
		tariffmaster.setCreatedTime( new Date());
		}
		final java.sql.Timestamp createdTime = 		new java.sql.Timestamp(tariffmaster.getCreatedTime().getTime()); 
				
		if(tariffmaster.getUpdatedTime() == null)
		{
		tariffmaster.setUpdatedTime( new Date());
		}
		final java.sql.Timestamp updatedTime = 	new java.sql.Timestamp(tariffmaster.getUpdatedTime().getTime()); 
		jdbcTemplate = custom.getJdbcTemplate();
	if(tariffmaster.getId() == 0)	{
		
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, tariffmaster.getAssetcode());
ps.setString(4, tariffmaster.getAssetdescription());
ps.setString(5, tariffmaster.getRate());
ps.setString(6, tariffmaster.getAlloweddiscount());
ps.setString(7, tariffmaster.getRemarks());
ps.setString(8, tariffmaster.getStatus());
ps.setString(9, tariffmaster.getItemId());
ps.setString(10, tariffmaster.getCustomerId());
ps.setString(11, tariffmaster.getVatallow());



							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				tariffmaster.setId(unId.intValue());
				

		}
		else
		{
			java.sql.Timestamp updatedTime1 =null;
			if(status){
				 updatedTime1 = updatedTime;
			}else{
				 updatedTime1 = new java.sql.Timestamp(tariffmaster.getUpdatedTime().getTime()); 
			}

			String sql = "UPDATE customertariffmaster  set updated_time =?, assetcode = ? ,assetdescription = ? ,rate = ? ,alloweddiscount = ? ,remarks = ? ,itemId=?,customerId=?,vatallow=?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{updatedTime1,tariffmaster.getAssetcode(),tariffmaster.getAssetdescription(),tariffmaster.getRate(),tariffmaster.getAlloweddiscount(),tariffmaster.getRemarks(),tariffmaster.getItemId(),tariffmaster.getCustomerId(),tariffmaster.getVatallow(),tariffmaster.getId()});
		}
	}
		
		@Transactional
		public boolean delete(int id,String status) {
			jdbcTemplate = custom.getJdbcTemplate();
			boolean delete = false;
			try{
			String sql = "update customertariffmaster set status='"+status+"'  WHERE id=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{id});
			jdbcTemplate.update(sql, new Object[]{id});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
			return delete;
		}
		

	 public TariffmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from customertariffmaster where id = ? ";
			List<TariffmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
