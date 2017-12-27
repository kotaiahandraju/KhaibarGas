
package com.aurospaces.neighbourhood.db.dao;

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
	public boolean updateCustomerCylinderStatus(String cylinderID) {
		boolean result=false;
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "update  customercylinders  set cylinderreturn='1' where cylinderId=? order by created_time desc limit 1";
		 int results=jdbcTemplate.update(sql, new Object[]{cylinderID});
			if(results!=0){
				result= true;
			}
			return result;
	}


}

