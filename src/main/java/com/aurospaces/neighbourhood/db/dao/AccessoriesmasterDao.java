
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AccessoriesmasterBean;
import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseAccessoriesmasterDao;




@Repository(value = "accessoriesmasterDao")
public class AccessoriesmasterDao extends BaseAccessoriesmasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	 public List<AccessoriesmasterBean> getAllAccessories() {
			List<AccessoriesmasterBean> retlist = null;
			try {
				jdbcTemplate = custom.getJdbcTemplate();
				
				String sql = "SELECT a.*,CASE WHEN a.status IN ('0') THEN 'Deactive' WHEN a.status in ('1') THEN 'Active'  ELSE '-----' END as accessoriesStatus  from accessoriesmaster a";
				System.out.println("sql:::"+sql);
				//lstDamage = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AccessoriesmasterBean>(AccessoriesmasterBean.class));
				 retlist = jdbcTemplate.query(sql, new Object[] {  },ParameterizedBeanPropertyRowMapper.newInstance(AccessoriesmasterBean.class));
				//System.out.println("lstDamage:::"+lstDamage);
			} catch (Exception e) {
				//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
				e.printStackTrace();
			}
			return retlist;
		}


}

