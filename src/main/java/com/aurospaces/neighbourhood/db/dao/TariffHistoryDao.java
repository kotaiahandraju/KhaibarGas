
package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTariffHistoryDao;




@Repository(value = "tariffHistoryDao")
public class TariffHistoryDao extends BaseTariffHistoryDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	/*public List<TariffmasterBean> getAllTariffmasterDetails(String status) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT t.*,i.name as itemName,CASE WHEN t.status IN ('0') THEN 'Deactive' WHEN t.status in ('1') THEN 'Active'  ELSE '-----' END as tariffStatus  from tariffmaster t,items i where t.itemId=i.id and t.status='"+status+"' ";
		List<TariffmasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
		if (retlist.size() > 0)
			return retlist;
			
		return null;
	}
	
	
	public List<TariffmasterBean> getByName(TariffmasterBean objTariffmasterBean){
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from tariffmaster where itemId = ?";
		List<TariffmasterBean> retlist = jdbcTemplate.query(sql,
				new Object[]{objTariffmasterBean.getItemId()},
		ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
		if(retlist.size() > 0)
			return retlist;
		return retlist;
	}
	public TariffmasterBean getgetTariffPrice(String itemId){
		List<TariffmasterBean> retlist =null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select * from tariffmaster where itemId=?";
			retlist = jdbcTemplate.query(sql,new Object[]{itemId},ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
			if(retlist.size()>0)
				return retlist.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}*/



}

