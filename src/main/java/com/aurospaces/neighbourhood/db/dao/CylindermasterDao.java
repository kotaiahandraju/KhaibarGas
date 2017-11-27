
package com.aurospaces.neighbourhood.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
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
		 
		 //String sql="SELECT *, DATE_FORMAT(expirydate,'%d/%m/%Y') AS expirtdate1  FROM cylindermaster";
		
		 String sql =  "SELECT c. *,DATE_FORMAT(c.expirydate,'%d-%M-%Y') AS expirtdate1 ,  CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as status   FROM cylindermaster c";
		List<CylindermasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
		
		if (retlist.size() > 0)
			return retlist;
		return null;
		    
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
	
	public CylindermasterBean getByCylinderId(CylindermasterBean cylindermasterBean) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from cylindermaster where cylinderid = ?";
			List<CylindermasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{cylindermasterBean.getCylinderid()},
			ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	
	
	 @SuppressWarnings("deprecation")
		public  int  getCylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT  count(*)    FROM cylindermaster";
			   
			   return jdbcTemplate.queryForInt(sql);
		}
	 
	 public List<CylinderTypesBean> getCylinderstypes() {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT name,id from cylindertypes";
		
			@SuppressWarnings("rawtypes")
			List list=jdbcTemplate.queryForList(sql);
			
			List<CylinderTypesBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CylinderTypesBean.class));
			return retlist;

		}

	
	
	
	
}

