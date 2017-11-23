
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AccessoriesmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseAccessoriesmasterDao;




@Repository(value = "accessoriesmasterDao")
public class AccessoriesmasterDao extends BaseAccessoriesmasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	 public List<AccessoriesmasterBean> getAllAccessories() {
			List<AccessoriesmasterBean> lstDamage = null;
			try {
				jdbcTemplate = custom.getJdbcTemplate();
				
				String sql = "SELECT *from accessoriesmaster";
				System.out.println("sql:::"+sql);
				lstDamage = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AccessoriesmasterBean>(AccessoriesmasterBean.class));
				//System.out.println("lstDamage:::"+lstDamage);
			} catch (Exception e) {
				//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
				e.printStackTrace();
			}
			return lstDamage;
		}


}

