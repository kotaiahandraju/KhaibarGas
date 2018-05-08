
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

import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.PaymentHistoryBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BasePaymentHistoryDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO payment_history( created_time, updated_time, customerId,netAmount,vatAmount,paidAmount,dueAmount,grossAmount,invoiceId) values (?, ?, ?, ?, ?, ?, ?,?,?)"; 


	java.sql.Timestamp expiryDate=null;


	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final PaymentHistoryBean paymentHistoryBean) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(paymentHistoryBean.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(paymentHistoryBean.getCreatedTime() == null)
					{
						paymentHistoryBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(paymentHistoryBean.getCreatedTime().getTime()); 
							
					if(paymentHistoryBean.getUpdatedTime() == null)
					{
						paymentHistoryBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(paymentHistoryBean.getUpdatedTime().getTime()); 
					
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, paymentHistoryBean.getCustomerId());
ps.setString(4, paymentHistoryBean.getNetAmount());
ps.setString(5, paymentHistoryBean.getVatAmount());
ps.setString(6, paymentHistoryBean.getPaidAmount());
ps.setString(7, paymentHistoryBean.getDueAmount());
ps.setString(8, paymentHistoryBean.getGrossAmount());
ps.setString(9, paymentHistoryBean.getInvoiceId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				paymentHistoryBean.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE securedeposit  set  customerId=?, amount=?, itemId=?, quantity=?, companyId=?,remarks=?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{});
		}
	}
		
		@Transactional
		public Boolean delete(int id,String status) {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update lpomaster set status='"+status+"' where id = ?";
			jdbcTemplate.update(sql, new Object[]{id});
			 int results=jdbcTemplate.update(sql, new Object[]{id});
				if(results!=0){
					result= true;
				}
				return result;
		}
		
		

	 public LpomasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from lpomaster where id = ? ";
			List<LpomasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
