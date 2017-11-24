
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTrucksmasterDao;

@Repository(value = "trucksmasterDao")
public class TrucksmasterDao extends BaseTrucksmasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<TrucksmasterBean> getAllTrucks() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT t.*, DATE_FORMAT(t.registrationexpirydate, '%d-%M-%Y') as registrationexpirydate1,  DATE_FORMAT(t.civildefensecardexpirydate, '%d-%M-%Y') as civildefensecardexpirydate1,DATE_FORMAT(t.servicedue, '%d-%M-%Y') as servicedue1 ,CASE WHEN t.status IN ('0') THEN 'Deactive' WHEN t.status in ('1') THEN 'Active'  ELSE '-----' END as truckStatus  from trucksmaster t";
		List<TrucksmasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(TrucksmasterBean.class));
		if (retlist.size() > 0)
			return retlist;
		return null;
	}
	 public TrucksmasterBean getByName(TrucksmasterBean trucksmasterBean) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from trucksmaster where trucknumber = ?";
			List<TrucksmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{trucksmasterBean.getTrucknumber()},
			ParameterizedBeanPropertyRowMapper.newInstance(TrucksmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
}

