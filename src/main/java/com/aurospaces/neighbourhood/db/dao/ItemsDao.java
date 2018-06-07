
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseItemsDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "itemsDao")
public class ItemsDao extends BaseItemsDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public String getAllItems(String status) {
		List<ItemsBean> listItemsmasterBean = null;
		ObjectMapper objectMapper=null;
		String sJson=null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT s.*,DATE_FORMAT(s.createdtime,'%d-%b-%Y') AS createddate,CASE WHEN s.status IN ('0') THEN 'Deactive' WHEN s.status in ('1') THEN 'Active'  ELSE '-----' END as itemstatus  from items s where status='"+status+"' order by s.id desc";
			System.out.println("sql:::"+sql);
			listItemsmasterBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ItemsBean>(ItemsBean.class));
			if(listItemsmasterBean !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(listItemsmasterBean);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}
	
	 public ItemsBean getByItemName(String sName) {
		 List<ItemsBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from items where name = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{sName},
			ParameterizedBeanPropertyRowMapper.newInstance(ItemsBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	 public List<ItemsBean> getItems() {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select id,trucknumber as name from trucksmaster where status='1' ";
			List<ItemsBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ItemsBean.class));
			return retlist;

		}

}

