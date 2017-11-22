
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCylindermasterDao;




@Repository(value = "cylindermasterDao")
public class CylindermasterDao extends BaseCylindermasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	@SuppressWarnings("unchecked")
	
	public List<CylindermasterBean> getCylinders(){  
		 jdbcTemplate = custom.getJdbcTemplate();
		return jdbcTemplate.query("SELECT  *,date(c.expirydate) as expirydate FROM cylindermaster c", new BeanPropertyRowMapper(CylindermasterBean.class));
			
		    
		}  
	

	public int  updateCylinders(int id){  
		 jdbcTemplate = custom.getJdbcTemplate();
		return jdbcTemplate.update(" update cylindermaster set status='0' where id="+id);
			

	}
}

