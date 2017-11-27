
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.TariffmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTariffmasterDao;




@Repository(value = "tariffmasterDao")
public class TariffmasterDao extends BaseTariffmasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public List<TariffmasterBean> getAllTariffmasterDetails() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from tariffmaster";
		List<TariffmasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
		if (retlist.size() > 0)
			return retlist;
			
		return null;
	}
	
	
	public List<TariffmasterBean> getByName(TariffmasterBean objTariffmasterBean){
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from tariffmaster where assetcode = ?";
		List<TariffmasterBean> retlist = jdbcTemplate.query(sql,
				new Object[]{objTariffmasterBean.getAssetcode()},
		ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
		if(retlist.size() > 0)
			return retlist;
		return retlist;
	}	



}

