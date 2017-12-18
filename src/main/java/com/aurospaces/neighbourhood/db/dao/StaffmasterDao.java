
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.StaffmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseStaffmasterDao;




@Repository(value = "staffmasterDao")
public class StaffmasterDao extends BaseStaffmasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	
	public List<StaffmasterBean> getAllStaffmasterDetails() {
		
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT s.*,CASE WHEN s.status IN ('0') THEN 'Deactive' WHEN s.status in ('1') THEN 'Active'  ELSE '-----' END as staffStatus  from staffmaster s order by s.id desc";
		List<StaffmasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(StaffmasterBean.class));
		if (retlist.size() > 0)
			return retlist;
			
		return null;
	}
	
	
	public List<StaffmasterBean> getByName(StaffmasterBean objTariffmasterBean){
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from staffmaster where id = ?";
		List<StaffmasterBean> retlist = jdbcTemplate.query(sql,
				new Object[]{objTariffmasterBean.getId()},
		ParameterizedBeanPropertyRowMapper.newInstance(StaffmasterBean.class));
		if(retlist.size() > 0)
			return retlist;
		return retlist;
	}

}

