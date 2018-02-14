
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
import com.aurospaces.neighbourhood.bean.PrintDataBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseReturnCylinderDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO returncylinder( `created_time`, `updated_time` ,`cylinderId`,`totalnetamount`,`vatamount`,`paidamount`,`dueamount`,`grossamount`,`previousdueamount`,`cylinderReturnTruck`,`invoiceId`,customerId ) values (?,?,?,?,?,?,?,?,?,?,?,?)"; 





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
//					`created_time`, `updated_time` ,`cylinderId`,`totalnetamount`,
//					`vatamount`,`paidamount`,`dueamount`,
//					`grossamount`,`previousdueamount`,`cylinderReturnTruck`,`invoiceId`
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, printDataBean.getCylinderId());
ps.setString(4, printDataBean.getTotalnetamount());
ps.setString(5, printDataBean.getVatamount());
ps.setString(6, printDataBean.getPaidamount());
ps.setString(7, printDataBean.getDueamount());
ps.setString(8, printDataBean.getGrossamount());
ps.setString(9, printDataBean.getPreviousdueamount());
ps.setString(10, printDataBean.getCylinderReturnTruck());
ps.setString(11, printDataBean.getInvoiceid());
ps.setString(12, printDataBean.getCustomerId());



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
