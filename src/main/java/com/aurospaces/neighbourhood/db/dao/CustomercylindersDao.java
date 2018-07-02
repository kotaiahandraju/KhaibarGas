
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
		String sql = "SELECT DATE_FORMAT(pd.created_time,'%d-%b-%Y') as created_time,i.`name`,cm.trnNumber,cm.`customername`,cm.`mobile`,cm.`customeraddress`,cm.`landline`,cm.`customertype`,cm.`customerid`, "		
						+"	pd.`items`,sum(pd.`quantity`) as quantity,pd.`price`,pd.`totalamount`,pd.`discount`,pd.`netamount`,pd.`totalnetamount`,"
						+" pd.`vatamount`,pd.`paidamount`,pd.`dueamount`,pd.`grossamount`,pd.`previousdueamount`,pd.`invoiceid` "	
						+" FROM `printdata` pd,`customermaster` cm,`items` i " 
						+"  WHERE cm.`id`=pd.`customerid` AND pd.items=i.id   AND pd.`invoiceid`=?  GROUP BY NAME,items,quantity,price,invoiceid";
		 result =jdbcTemplate.queryForList(sql, new Object[]{invoiceId});
		 System.out.println(sql);
		return result;
	}
	
	public List<Map<String, Object>>  getInvoiceDataReturnCylinder(String invoiceId) {
		List<Map<String, Object>> result=null;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT DATE_FORMAT(pd.created_time,'%d-%b-%Y') as created_time,t.`trucknumber`,pd.`cylinderId`,i.`name`,cm.trnNumber,cm.`customername`,cm.`mobile`,cm.`customeraddress`,cm.`landline`,cm.`customertype`,cm.`customerid`, 	"
						+" pd.`totalnetamount`,	 pd.`vatamount`,pd.`paidamount`,pd.`dueamount`,pd.`grossamount`,pd.`previousdueamount`,pd.`invoiceid` " 
					 +" FROM `returncylinder` pd,`customermaster` cm ,`cylindermaster` cm1,`items` i,`trucksmaster` t  "
					  +" WHERE cm.`id`=pd.`customerId` AND pd.`cylinderId`=cm1.`id` AND t.id=pd.`cylinderReturnTruck` AND cm1.size=i.id  AND pd.`invoiceid`= ? ";
		 result =jdbcTemplate.queryForList(sql, new Object[]{invoiceId});
		 System.out.println(sql);
		return result;
	}


}

