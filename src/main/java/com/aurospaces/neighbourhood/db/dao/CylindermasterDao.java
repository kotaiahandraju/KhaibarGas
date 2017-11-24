
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	

	@Transactional
	public boolean deleteCylinder(int id) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		try{
			String sql = "Update  cylindermaster set status='0' WHERE id=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{id});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}
	
	public List<CylindermasterBean> getByCylinderId(String cyid) {
		 List<CylindermasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from cylindermaster where cylinderid = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{cyid},
			ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	
	
	
	
}

