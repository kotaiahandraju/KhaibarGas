
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseStoresmasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "storesmasterDao")
public class StoresmasterDao extends BaseStoresmasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public String getAllStore(String status) {
		List<StoresmasterBean> listStoresmasterBean = null;
		ObjectMapper objectMapper=null;
		String sJson=null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT s.*,CASE WHEN s.status IN ('0') THEN 'Deactive' WHEN s.status in ('1') THEN 'Active'  ELSE '-----' END as storeStatus  from storesmaster s where status='"+status+"' order by s.id desc";
			System.out.println("sql:::"+sql);
			listStoresmasterBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<StoresmasterBean>(StoresmasterBean.class));
			if(listStoresmasterBean !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(listStoresmasterBean);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}
	
	 public List<StoresmasterBean> getByStoreName(String sName) {
		 List<StoresmasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from storesmaster where storename = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{sName},
			ParameterizedBeanPropertyRowMapper.newInstance(StoresmasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	 public List<StoresmasterBean> getStoreDetails(StoresmasterBean storesmasterBean) {
		 List<StoresmasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select * from storesmaster where status='1' and id=? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{storesmasterBean.getId()},
			ParameterizedBeanPropertyRowMapper.newInstance(StoresmasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	 public List<StoresmasterBean> populate(String sql ){
		 jdbcTemplate = custom.getJdbcTemplate();
				List<StoresmasterBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(StoresmasterBean.class));
					return retlist;
		 }
	 public List<ItemsBean> populateCylenderstatus(String sql ){
		 jdbcTemplate = custom.getJdbcTemplate();
				List<ItemsBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ItemsBean.class));
					return retlist;
		 }
	 
	 public boolean updateStoreIds() {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update storesmaster set storeid =concat('Store', LPAD(id ,6,0))";
			int i = jdbcTemplate.update(sql, new Object[]{});
			 if(i>0)
				 result = true;
				return result;
		}
		
}




