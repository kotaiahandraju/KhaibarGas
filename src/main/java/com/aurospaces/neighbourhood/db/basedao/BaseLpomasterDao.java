
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
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseLpomasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO lpomaster( created_time, updated_time, lponumber, item, remarks, suppliername, supplieraddress, suppliercontactno, supplieremail, amount, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final LpomasterBean lpomaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(lpomaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(lpomaster.getCreatedTime() == null)
					{
					lpomaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(lpomaster.getCreatedTime().getTime()); 
							
					if(lpomaster.getUpdatedTime() == null)
					{
					lpomaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(lpomaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, lpomaster.getLponumber());
ps.setString(4, lpomaster.getItem());
ps.setString(5, lpomaster.getRemarks());
ps.setString(6, lpomaster.getSuppliername());
ps.setString(7, lpomaster.getSupplieraddress());
ps.setString(8, lpomaster.getSuppliercontactno());
ps.setString(9, lpomaster.getSupplieremail());
ps.setString(10, lpomaster.getAmount());
ps.setString(11, lpomaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				lpomaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE lpomaster  set lponumber = ? ,item = ? ,remarks = ? ,suppliername = ? ,supplieraddress = ? ,suppliercontactno = ? ,supplieremail = ? ,amount = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{lpomaster.getLponumber(),lpomaster.getItem(),lpomaster.getRemarks(),lpomaster.getSuppliername(),lpomaster.getSupplieraddress(),lpomaster.getSuppliercontactno(),lpomaster.getSupplieremail(),lpomaster.getAmount(),lpomaster.getStatus(),lpomaster.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM lpomaster WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
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
