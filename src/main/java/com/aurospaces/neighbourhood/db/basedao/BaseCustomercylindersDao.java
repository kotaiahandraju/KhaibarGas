
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

import com.aurospaces.neighbourhood.bean.CustomercylindersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseCustomercylindersDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO customercylinders( created_time, updated_time, customerId, cylinderId, quantity, price, discount, grandTotal, vat, cylinderreturn,cylinderReturnTruck,cylinderDeliverTruck,accessoriesId,invoiceId) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final CustomercylindersBean customercylinders) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(customercylinders.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(customercylinders.getCreatedTime() == null)
					{
					customercylinders.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(customercylinders.getCreatedTime().getTime()); 
							
					if(customercylinders.getUpdatedTime() == null)
					{
					customercylinders.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(customercylinders.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, customercylinders.getCustomerId());
ps.setString(4, customercylinders.getCylinderId());
ps.setString(5, customercylinders.getQuantity());
ps.setString(6, customercylinders.getPrice());
ps.setString(7, customercylinders.getDiscount());
ps.setString(8, customercylinders.getTotalPrice());
ps.setString(9, customercylinders.getVat());
ps.setString(10, customercylinders.getCylinderreturn());
ps.setString(11, customercylinders.getCylinderReturnTruck());
ps.setString(12, customercylinders.getCylinderDeliverTruck());
ps.setString(13, customercylinders.getAccessoriesId());
ps.setString(14, customercylinders.getInvoiceId());
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				customercylinders.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE customercylinders  set customerId = ? ,cylinderId = ? ,quantity = ? ,price = ? ,discount = ? ,grandTotal = ? ,vat = ? ,cylinderreturn = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{customercylinders.getCustomerId(),customercylinders.getCylinderId(),customercylinders.getQuantity(),customercylinders.getPrice(),customercylinders.getDiscount(),customercylinders.getTotalPrice(),customercylinders.getVat(),customercylinders.getCylinderreturn(),customercylinders.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM customercylinders WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public CustomercylindersBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from customercylinders where id = ? ";
			List<CustomercylindersBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(CustomercylindersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
