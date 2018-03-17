/**
 * 
 */
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.SecuredepositBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSecuredepositDao;

/**
 * @author Kotaiah
 *
 */
@Repository(value = "SecuredepositDao")
public class SecuredepositDao extends BaseSecuredepositDao {
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<SecuredepositBean> getAllData(){  
		jdbcTemplate = custom.getJdbcTemplate();
		 
		 //String sql="SELECT *, DATE_FORMAT(expirydate,'%d/%m/%Y') AS expirtdate1  FROM cylindermaster";
		
		 String sql =  "select * from securedeposit order by id desc";
		List<SecuredepositBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(SecuredepositBean.class));
		
		if (retlist.size() > 0)
			return retlist;
		return null;
		    
		} 
}
