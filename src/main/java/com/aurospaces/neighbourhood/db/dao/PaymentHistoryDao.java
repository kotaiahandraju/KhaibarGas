
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BasePaymentHistoryDao;




@Repository(value = "PaymentHistoryDao")
public class PaymentHistoryDao extends BasePaymentHistoryDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String,Object>> getPaymentReport(Expensetracker expensetracker,String month,String year) {
		 List<Map<String,Object>> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
		 StringBuffer buffer = new StringBuffer();
		 buffer.append( "SELECT cm.customerid, GROUP_CONCAT(i.name) AS itemName, p.grossamount,p.paidAmount,p.invoiceId,cm.customertype,cm.`customername`, "
						+" cm.`customeraddress`,cm.`mobile`,cm.`landline`,p.`dueAmount` FROM `printdata` p LEFT JOIN " 
						+" `customermaster` cm ON p.`customerid`=cm.id  LEFT JOIN  items i ON p.`items` =i.id where 1=1 " );
		 
		 
		 if(StringUtils.isNotBlank(expensetracker.getMonth())){
				buffer.append(" and MONTH(p.created_time)='"+month+"'  AND YEAR(p.created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(expensetracker.getFromDate()) &&  StringUtils.isNotBlank(expensetracker.getToDate())){
				buffer.append(" and  Date(p.created_time) BETWEEN '"+expensetracker.getFromDate()+"' AND '"+expensetracker.getToDate()+"' " );
			} 
		 if(StringUtils.isNotBlank(expensetracker.getCustomerId())){
			 buffer.append(" and cm.id= "+expensetracker.getCustomerId() );
		 }
		 buffer.append("  GROUP BY p.`invoiceid` order by p.created_time asc,cm.customerid asc");
		 String sql =buffer.toString();
		 System.out.println(sql);
			 retlist = jdbcTemplate.queryForList(sql,	new Object[]{});
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	
	

}

