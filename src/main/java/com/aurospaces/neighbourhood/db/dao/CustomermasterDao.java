
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CustomermasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCustomermasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "customermasterDao")
public class CustomermasterDao extends BaseCustomermasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	ObjectMapper objectMapper=null;
	String sJson=null;
	public String getAllCustomer() {
		List<CustomermasterBean> listCustomermasterBean = null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT c.*,CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as custStatus  from customermaster c";
			System.out.println("sql:::"+sql);
			listCustomermasterBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CustomermasterBean>(CustomermasterBean.class));
			if(listCustomermasterBean !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(listCustomermasterBean);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}
	 @SuppressWarnings("deprecation")
		public  int  getCustomerCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT  count(*)    FROM customermaster";
			   
			   return jdbcTemplate.queryForInt(sql);
		}
	 public boolean updateCustomerIds() {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update customermaster  set  customerid= concat(SUBSTR(customertype,1,3),'1' ,LPAD( id, 4, '0'))";
			int i = jdbcTemplate.update(sql, new Object[]{});
			 if(i>0)
				 result = true;
				return result;
		}
}

