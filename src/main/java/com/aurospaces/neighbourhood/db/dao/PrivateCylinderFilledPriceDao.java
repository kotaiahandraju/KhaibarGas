/**
 * 
 */
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BasePrivateCylinderFilledPriceDao;

/**
 * @author Kotaiah
 *
 */
@Repository(value = "PrivateCylinderFilledPriceDao")
public class PrivateCylinderFilledPriceDao extends BasePrivateCylinderFilledPriceDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public List<Map<String, Object>>  getInvoiceData(String invoiceId) {
		List<Map<String, Object>> result=null;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT f.`stationname`,cylinderFilledDate as created_time,i.`name`,cm.`customername`,cm.`mobile`,cm.`customeraddress`,cm.`landline`,cm.`customertype`,cm.`customerid`, " 		
					+"	pd.`items`,pd.`quantity`,pd.`price`,pd.`totalamount`,pd.`discount`,pd.`netamount`,pd.`totalnetamount`, "
					 +" pd.`vatamount`,pd.`paidamount`,pd.`dueamount`,pd.`grossamount`,pd.`previousdueamount`,pd.`invoiceid` " 	
					 +" FROM `privatecylinderfilledprice` pd,`customermaster` cm,`fillingstationmaster` f,`items` i "  
					  +" WHERE cm.`id`=pd.`customerid` AND f.id=pd.`fillingstationId` AND pd.`items`=i.id AND pd.`invoiceid`=?  GROUP BY NAME,items,quantity,price,invoiceid";
		 result =jdbcTemplate.queryForList(sql, new Object[]{invoiceId});
		 System.out.println(sql);
		return result;
	}
}
