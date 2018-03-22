/**
 * 
 */
package com.aurospaces.neighbourhood.db.basedao;

/**
 * @author Kotaiah
 *
 */


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
import com.aurospaces.neighbourhood.bean.PrintDataBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BasePrivateCylinderFilledPriceDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO privatecylinderfilledprice( `created_time` ,  `updated_time` ,  customerId ,  `items` ,`quantity` ,`price` ,`totalamount` , `discount` ,`netamount` ,`totalnetamount` ,`vatamount` ,`paidamount` ,`dueamount` ,`grossamount` ,  `previousdueamount`,invoiceId,fillingstationId  ) values (?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final PrintDataBean printDataBean) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(printDataBean.getId() == 0)	{
		
		
		
	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(printDataBean.getCreatedTime() == null)
					{
					printDataBean.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(printDataBean.getCreatedTime().getTime()); 
							
					if(printDataBean.getUpdatedTime() == null)
					{
					printDataBean.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(printDataBean.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
					
//					 userId ,  `items` ,`quantity` ,`price` ,`totalamount` , `discount` ,`netamount` ,
//					 `totalnetamount` ,`vatamount` ,`paidamount` ,`dueamount` ,`grossamount` ,  `previousdueamount` ,
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, printDataBean.getCustomerId());
ps.setString(4, printDataBean.getItems());
ps.setString(5, printDataBean.getQuantity());
ps.setString(6, printDataBean.getPrice());
ps.setString(7, printDataBean.getTotalamount());
ps.setString(8, printDataBean.getDiscount());
ps.setString(9, printDataBean.getNetamount());
ps.setString(10, printDataBean.getTotalnetamount());
ps.setString(11, printDataBean.getVatamount());
ps.setString(12, printDataBean.getPaidamount());
ps.setString(13, printDataBean.getDueamount());
ps.setString(14, printDataBean.getGrossamount());
ps.setString(15, printDataBean.getPreviousdueamount());
ps.setString(16, printDataBean.getInvoiceid());
ps.setString(17, printDataBean.getFillingstationId());



							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				printDataBean.setId(unId.intValue());
				
		}
		else
		{
			
			String sql = "UPDATE accessoriesmaster  set typeofaccessory = ? ,suppliername = ? ,madein = ? ,lponumber = ? ,remarks = ? ,status = ?  where id = ? ";
	
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
