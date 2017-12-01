
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseLpomasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "lpomasterDao")
public class LpomasterDao extends BaseLpomasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public String getAllCustomer() {
		List<LpomasterBean> listLpomasterBean = null;
		ObjectMapper objectMapper=null;
		String sJson=null;
		try {
			
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT l.*, DATE_FORMAT(l.expiryDate, '%d-%M-%Y') as expiryDate1,CASE WHEN l.status IN ('0') THEN 'Deactive' WHEN l.status in ('1') THEN 'Active'  ELSE '-----' END as lpoStatus  from lpomaster l";
			System.out.println("sql:::"+sql);
			listLpomasterBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<LpomasterBean>(LpomasterBean.class));
			if(listLpomasterBean !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(listLpomasterBean);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}
	 public List<LpomasterBean> getByLpoNo(String sMobileNo) {
		 List<LpomasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from lpomaster where lponumber = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{sMobileNo},
			ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	 public List<LpomasterBean> getLPOdetails(LpomasterBean lpomasterBean) {
		 List<LpomasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT l.*,DATE_FORMAT(l.expiryDate, '%d-%M-%Y') as expiryDate1 from lpomaster l where lponumber = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{lpomasterBean.getLponumber()},
			ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	 public List<CylinderTypesBean> populate(String sql ){
		 jdbcTemplate = custom.getJdbcTemplate();
				List<CylinderTypesBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CylinderTypesBean.class));
					return retlist;
		 }
	
}

