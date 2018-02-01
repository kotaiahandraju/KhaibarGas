
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

import com.aurospaces.neighbourhood.bean.AccessoriesmasterBean;
import com.aurospaces.neighbourhood.bean.CustomermasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseInvoiceDataDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO invoicedata( created_time, updated_time, payedAmount,dueAmount,netAmount,previousDueAmount,cid,invoiceId) values (?,?, ?, ?, ?,?,?,?)"; 


	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final CustomermasterBean customermasterBean) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(customermasterBean.getId() == 0)	{
		
		
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(customermasterBean.getCreatedTime() == null)
					{
						customermasterBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(customermasterBean.getCreatedTime().getTime()); 
							
					if(customermasterBean.getUpdatedTime() == null)
					{
						customermasterBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(customermasterBean.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, customermasterBean.getPayedAmount());
ps.setString(4, customermasterBean.getDueAmount());
ps.setString(5, customermasterBean.getNetAmount());
ps.setString(6, customermasterBean.getPreviousDueAmount());
ps.setString(7, customermasterBean.getCylinderId());
ps.setString(8, customermasterBean.getInvoiceId());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				customermasterBean.setId(unId.intValue());
				
		}
		else
		{
			
			String sql = "UPDATE addgas  set fillingStationId = ? ,gasInKgs = ?   where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{});
		}
	}
		
		@Transactional
		public Boolean delete(int id,String status) {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update accessoriesmaster set status='"+status+"' where id = ?";
		 int results=jdbcTemplate.update(sql, new Object[]{id});
			if(results!=0){
				result= true;
			}
			return result;
		}
		

	 public AccessoriesmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from accessoriesmaster where id = ? ";
			List<AccessoriesmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(AccessoriesmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	

	

}
