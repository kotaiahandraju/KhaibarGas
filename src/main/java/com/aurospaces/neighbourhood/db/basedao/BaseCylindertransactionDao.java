
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

import com.aurospaces.neighbourhood.bean.CylindertransactionBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseCylindertransactionDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO cylindertransaction( created_time, updated_time, cylindetId, cylinderStatus, createdBy,fillingStation ,customerId,truckId,storename,invoiceId) values (?, ?, ?, ?, ?,?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final CylindertransactionBean cylindertransaction) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(cylindertransaction.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(cylindertransaction.getCreatedTime() == null)
					{
					cylindertransaction.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(cylindertransaction.getCreatedTime().getTime()); 
							
					if(cylindertransaction.getUpdatedTime() == null)
					{
					cylindertransaction.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(cylindertransaction.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, cylindertransaction.getCylindetId());
ps.setString(4, cylindertransaction.getCylinderStatus());
ps.setString(5, cylindertransaction.getCreatedBy());
ps.setString(6, cylindertransaction.getFillingStation());
ps.setString(7, cylindertransaction.getCustomerId());
ps.setString(8, cylindertransaction.getTruckId());
ps.setString(9, cylindertransaction.getStorename());
ps.setString(10, cylindertransaction.getInvoiceId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				cylindertransaction.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE cylindertransaction  set cylindetId = ? ,cylinderStatus = ? ,createdBy = ?,TruckId=?,storename=?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{cylindertransaction.getCylindetId(),cylindertransaction.getCylinderStatus(),cylindertransaction.getCreatedBy(),cylindertransaction.getTruckId(),cylindertransaction.getStorename(),cylindertransaction.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM cylindertransaction WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public CylindertransactionBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from cylindertransaction where id = ? ";
			List<CylindertransactionBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(CylindertransactionBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
