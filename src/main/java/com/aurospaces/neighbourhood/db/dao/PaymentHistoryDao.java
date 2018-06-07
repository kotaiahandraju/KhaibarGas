
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
		 buffer.append( "SELECT p.invoiceid,DATE_FORMAT(p.created_time,'%d-%b-%Y') AS created_time,IFNULL(i.name,'---') AS itemname, IFNULL(p.price,0) AS price,IFNULL((IFNULL(p.price,0)*(IFNULL(p.`discount`,0)/100)),0) AS discount,IFNULL(p.`netamount`,0) AS netamount,IFNULL(p.`vatamount`,0) AS vatamount, "
						+" IFNULL(p.`previousdueamount`,0) AS previousdueamount,IFNULL(p.grossamount,0) AS totalamount, "
						+" IFNULL(p.`paidamount`,0) AS paidamount,p.`dueAmount`,  cm.customerid, cm.customertype,cm.`customername`, "  
						 +" cm.`customeraddress`,cm.`mobile`,cm.`landline` FROM `printdata` p LEFT JOIN  `customermaster` cm "
						 +" ON p.`customerid`=cm.id  LEFT JOIN  items i ON p.`items` =i.id WHERE 1=1 " );
		 
		 if(StringUtils.isNotBlank(expensetracker.getMonth())){
				buffer.append(" and MONTH(p.created_time)='"+month+"'  AND YEAR(p.created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(expensetracker.getFromDate()) &&  StringUtils.isNotBlank(expensetracker.getToDate())){
				buffer.append(" and  Date(p.created_time) BETWEEN '"+expensetracker.getFromDate()+"' AND '"+expensetracker.getToDate()+"' " );
			} 
		 if(StringUtils.isNotBlank(expensetracker.getCustomerId())){
			 buffer.append(" and cm.id= "+expensetracker.getCustomerId() );
		 }
		 buffer.append("  order by p.created_time asc,cm.customerid asc");
		 String sql =buffer.toString();
		 System.out.println(sql);
			 retlist = jdbcTemplate.queryForList(sql,	new Object[]{});
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	

}

