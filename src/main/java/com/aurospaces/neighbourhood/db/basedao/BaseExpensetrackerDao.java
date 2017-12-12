
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

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;



public class BaseExpensetrackerDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO expensetracker( accountHead, dateOfExpense, itemDescription, paymentType, paymentRemarks, created_time, updated_time) values (?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final Expensetracker expensetracker) 
	{
	if(expensetracker.getId() == 0)	{
		jdbcTemplate = custom.getJdbcTemplate();
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(expensetracker.getCreatedTime() == null)
					{
					expensetracker.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(expensetracker.getCreatedTime().getTime()); 
							
					if(expensetracker.getUpdatedTime() == null)
					{
					expensetracker.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(expensetracker.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setString(1, expensetracker.getAccountHead());
ps.setString(2, expensetracker.getDateOfExpense());
ps.setString(3, expensetracker.getItemDescription());
ps.setString(4, expensetracker.getPaymentType());
ps.setString(5, expensetracker.getPaymentRemarks());
ps.setTimestamp(6, createdTime);
ps.setTimestamp(7, updatedTime);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				expensetracker.setId(unId.intValue());
				

		}
		else
		{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "UPDATE expensetracker  set accountHead = ? ,dateOfExpense = ? ,itemDescription = ? ,paymentType = ? ,paymentRemarks = ? ,updated_time = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{expensetracker.getAccountHead(),expensetracker.getDateOfExpense(),expensetracker.getItemDescription(),expensetracker.getPaymentType(),expensetracker.getPaymentRemarks(),expensetracker.getUpdatedTime(),expensetracker.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			String sql = "DELETE FROM expensetracker WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public Expensetracker getById(int id) {
			String sql = "SELECT * from expensetracker where id = ? ";
			List<Expensetracker> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(Expensetracker.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
