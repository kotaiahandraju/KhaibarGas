
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.TariffmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCustomerTariffmasterDao;




@Repository(value = "CustomerTariffmasterDao")
public class CustomerTariffmasterDao extends BaseCustomerTariffmasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public List<TariffmasterBean> getAllTariffmasterDetails(String status) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT t.*,i.name as itemName,CASE WHEN t.status IN ('0') THEN 'Deactive' WHEN t.status in ('1') THEN 'Active'  ELSE '-----' END as tariffStatus,t.customerId,c.`customername`  from customertariffmaster t,items i,`customermaster` c WHERE t.itemId=i.id AND c.id=t.customerId and t.status='"+status+"' ";
		List<TariffmasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
		if (retlist.size() > 0)
			return retlist;
			
		return null;
	}
	
	
	public List<TariffmasterBean> getByName(TariffmasterBean objTariffmasterBean){
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from customertariffmaster where customerId = ? and itemId = ?";
		List<TariffmasterBean> retlist = jdbcTemplate.query(sql,new Object[]{objTariffmasterBean.getCustomerId(),objTariffmasterBean.getItemId()},
		ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
		if(retlist.size() > 0)
			return retlist;
		return retlist;
	}
	public TariffmasterBean getgetTariffPrice(String itemId,String customerId){
		List<TariffmasterBean> retlist =null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select * from customertariffmaster where itemId=? and customerId=? ";
			retlist = jdbcTemplate.query(sql,new Object[]{itemId,customerId},ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
			if(retlist.size()>0)
				return retlist.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}



}

