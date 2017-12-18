
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CompanymasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
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
		String sql = "SELECT c.*,CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as companyStatus  from companymaster c order by c.id desc";
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
	
	public List<CompanymasterBean> populate(String sql ){
		 jdbcTemplate = custom.getJdbcTemplate();
				List<CompanymasterBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CompanymasterBean.class));
					return retlist;
		 }
}

