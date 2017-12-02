
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.LpoitemsBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseLpoitemsDao{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO lpoitems( createdtime, updated_time, itemid, lponumber, quantity, price, totalprice, discount, grandtotal) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final LpoitemsBean lpoitems) 
	{
	if(lpoitems.getId() == 0)	{
		jdbcTemplate = custom.getJdbcTemplate();

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(lpoitems.getCreatedtime() == null)
					{
					lpoitems.setCreatedtime( new Date());
					}
					java.sql.Timestamp createdtime = 
						new java.sql.Timestamp(lpoitems.getCreatedtime().getTime()); 
							
					if(lpoitems.getUpdatedTime() == null)
					{
					lpoitems.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(lpoitems.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdtime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, lpoitems.getItemid());
ps.setString(4, lpoitems.getLponumber());
ps.setString(5, lpoitems.getQuantity());
ps.setString(6, lpoitems.getPrice());
ps.setString(7, lpoitems.getTotalprice());
ps.setString(8, lpoitems.getDiscount());
ps.setString(9, lpoitems.getGrandtotal());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				lpoitems.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE lpoitems  set createdtime = ? ,itemid = ? ,lponumber = ? ,quantity = ? ,price = ? ,totalprice = ? ,discount = ? ,grandtotal = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{lpoitems.getCreatedtime(),lpoitems.getItemid(),lpoitems.getLponumber(),lpoitems.getQuantity(),lpoitems.getPrice(),lpoitems.getTotalprice(),lpoitems.getDiscount(),lpoitems.getGrandtotal(),lpoitems.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM lpoitems WHERE id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public LpoitemsBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from lpoitems where id = ? ";
			List<LpoitemsBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(LpoitemsBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
