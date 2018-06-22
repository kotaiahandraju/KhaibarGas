
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
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
		 buffer.append( "SELECT DATE_FORMAT(p.created_time,'%d-%b-%Y') AS createdOn,p.invoiceid,cm.customerid, GROUP_CONCAT(ifnull(i.name,'---')) AS itemName,ifnull(p.`totalamount`,0) AS itemamount,IFNULL((IFNULL(p.totalamount,0)*(IFNULL(p.`discount`,0)/100)),0) AS discount," 
						+" IFNULL(p.`netamount`,0) as netamount,IFNULL(p.`vatamount`,0) as vatamount,(IFNULL(p.netamount,0)+IFNULL(p.vatamount,0)) AS totalAmount,IFNULL(p.`previousdueamount`,0) as previousdueamount ,  IFNULL(p.grossamount,0) as grossamount,IFNULL(p.paidAmount,0) as paidAmount ,IFNULL(p.`dueAmount`,0) as dueAmount , "
						 +" p.invoiceId,cm.customertype,cm.`customername`, 	 cm.`customeraddress`,cm.`mobile`,cm.`landline` FROM `printdata` p LEFT JOIN "   
						 +"  `customermaster` cm ON p.`customerid`=cm.id  LEFT JOIN  items i ON p.`items` =i.id WHERE 1=1   " );
		 
		 
		 if(StringUtils.isNotBlank(expensetracker.getMonth())){
				buffer.append(" and MONTH(p.created_time)='"+month+"'  AND YEAR(p.created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(expensetracker.getFromDate()) &&  StringUtils.isNotBlank(expensetracker.getToDate())){
				buffer.append(" and  Date(p.created_time) BETWEEN '"+expensetracker.getFromDate()+"' AND '"+expensetracker.getToDate()+"' " );
			} 
		 if(StringUtils.isNotBlank(expensetracker.getCustomerId())){
			 buffer.append(" and cm.id= "+expensetracker.getCustomerId() );
		 }
		 if(StringUtils.isNotBlank(expensetracker.getCustomerType())){
			 buffer.append(" and cm.customertype= '"+expensetracker.getCustomerType()+"' ");
		 }
		 buffer.append("  GROUP BY p.`invoiceid` order by p.created_time asc,cm.customerid asc");
		 String sql =buffer.toString();
		 System.out.println(sql);
			 retlist = jdbcTemplate.queryForList(sql,	new Object[]{});
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	
	public LpomasterBean getmysqlpath() {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = " SELECT @@basedir AS remarks ";
			List<LpomasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
	}
	public List<Map<String,Object>> getInvoiceData(String invoiceId) {
		 List<Map<String,Object>> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
		 StringBuffer buffer = new StringBuffer();
		 buffer.append( "SELECT DATE_FORMAT(p.created_time,'%d-%b-%Y') AS createdOn,i.`name`,p.* FROM `printdata` p LEFT JOIN    "
				 	    +" `customermaster` cm ON p.`customerid`=cm.id  LEFT JOIN  items i ON p.`items` =i.id WHERE 1=1 AND p.invoiceid='"+invoiceId+"'   " );
		 
		
		 String sql =buffer.toString();
		 System.out.println(sql);
			 retlist = jdbcTemplate.queryForList(sql,	new Object[]{});
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
}

