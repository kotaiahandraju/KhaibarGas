
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCustomercylindersDao;




@Repository(value = "customercylindersDao")
public class CustomercylindersDao extends BaseCustomercylindersDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public boolean updateCustomerCylinderStatus(String cylinderID,String invoiceId,String customerId) {
		boolean result=false;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "update  customercylinders  set cylinderreturn='1',returnCylinderInvoiceId='"+invoiceId+"' where cylinderId=? order by created_time desc limit 1";
		 int results=jdbcTemplate.update(sql, new Object[]{cylinderID});
			if(results!=0){
				result= true;
			}
			return result;
	}
	public List<Map<String, Object>>  getInvoiceData(String invoiceId) {
		List<Map<String, Object>> result=null;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT cm.`customername`,cm.`mobile`,cm.`customeraddress`,cm.`landline`,cm.`customertype`,cm.`customerid`, "		
						+"	pd.`items`,pd.`quantity`,pd.`price`,pd.`totalamount`,pd.`discount`,pd.`netamount`,pd.`totalnetamount`,"
						+" pd.`vatamount`,pd.`paidamount`,pd.`dueamount`,pd.`grossamount`,pd.`previousdueamount`,pd.`invoiceid` "	
						+" FROM `printdata` pd,`customermaster` cm " 
						+"  WHERE cm.`id`=pd.`customerid`  AND pd.`invoiceid`=? ";
		 result =jdbcTemplate.queryForList(sql, new Object[]{invoiceId});
		return result;
	}
	
	public List<Map<String, Object>>  getInvoiceDataReturnCylinder(String invoiceId) {
		List<Map<String, Object>> result=null;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT cm.`customername`,cm.`mobile`,cm.`customeraddress`,cm.`landline`,cm.`customertype`,cm.`customerid`, "		
						+"	pd.`items`,pd.`quantity`,pd.`price`,pd.`totalamount`,pd.`discount`,pd.`netamount`,pd.`totalnetamount`,"
						+" pd.`vatamount`,pd.`paidamount`,pd.`dueamount`,pd.`grossamount`,pd.`previousdueamount`,pd.`invoiceid` "	
						+" FROM `printdata` pd,`customermaster` cm " 
						+"  WHERE cm.`id`=pd.`customerid`  AND pd.`invoiceid`=? ";
		 result =jdbcTemplate.queryForList(sql, new Object[]{invoiceId});
		return result;
	}


}

