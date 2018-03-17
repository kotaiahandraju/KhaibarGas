
package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BasePrintDataDao;




@Repository(value = "PrintDataDao")
public class PrintDataDao extends BasePrintDataDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public boolean updatePrintdataCylinderStatus(String cylinderID,String invoiceId,String customerId) {
		boolean result=false;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "update  printdata  set returnCylinderInvoiceId='"+invoiceId+"' where cylinderId=? order by created_time desc limit 1";
		 int results=jdbcTemplate.update(sql, new Object[]{cylinderID});
			if(results!=0){
				result= true;
			}
			return result;
	}


}

