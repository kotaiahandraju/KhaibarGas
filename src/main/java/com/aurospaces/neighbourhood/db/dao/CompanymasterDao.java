
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CompanymasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCompanymasterDao;




@Repository(value = "companymasterDao")
public class CompanymasterDao extends BaseCompanymasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public List<CompanymasterBean> getAllCompanyMasterDetails() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from companymaster";
		List<CompanymasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(CompanymasterBean.class));
		if (retlist.size() > 0)
			return retlist;
			
		return null;
	}
	public List<CompanymasterBean> getByName(CompanymasterBean objcompanyMasterBean){
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from companymaster where companyname = ?";
		List<CompanymasterBean> retlist = jdbcTemplate.query(sql,
				new Object[]{objcompanyMasterBean.getCompanyname()},
		ParameterizedBeanPropertyRowMapper.newInstance(CompanymasterBean.class));
		if(retlist.size() > 0)
			return retlist;
		return retlist;
	}
	
}

