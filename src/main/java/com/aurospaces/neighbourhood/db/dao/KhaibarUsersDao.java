
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.KhaibarUsersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseKhaibarUsersDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "khaibarUsersDao")
public class KhaibarUsersDao extends BaseKhaibarUsersDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	 public KhaibarUsersBean loginChecking(KhaibarUsersBean khaibarUsersBean) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from khaibar_users where userName = ? and password =? and status='1'  ";
			List<KhaibarUsersBean> retlist = jdbcTemplate.query(sql,
			new Object[]{khaibarUsersBean.getUserName(),khaibarUsersBean.getPassword()},
			ParameterizedBeanPropertyRowMapper.newInstance(KhaibarUsersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 
	public String getAllUsers(String status) {
		
		ObjectMapper objectMapper=null;
		String sJson=null;
			List<KhaibarUsersBean> khaibarUsersBean = null;
			try {
				jdbcTemplate = custom.getJdbcTemplate();
				
				String sql = "select ku.id,ku.created_time,ku.updated_time,ku.userName,ku.password,ku.roleId,ku.status,ku.edit,ku.delete1,ku.mobileapp,CASE WHEN ku.status IN ('0') THEN 'Deactive' WHEN ku.status IN ('1') THEN 'active' ELSE '-----' END AS userstatus  FROM khaibar_users as ku  where ku.status='"+status+"' and ku.roleId in('2') ";
				System.out.println("sql:::"+sql);
				khaibarUsersBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(KhaibarUsersBean.class));
				if(khaibarUsersBean !=null){
					objectMapper =new ObjectMapper();
					 sJson=objectMapper.writeValueAsString(khaibarUsersBean);
				}
			} catch (Exception e) {
				//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
				e.printStackTrace();
			}
			return sJson;
		}
	
	public KhaibarUsersBean getByUserName(String sName) {
		 List<KhaibarUsersBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from khaibar_users where userName = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{sName},
			ParameterizedBeanPropertyRowMapper.newInstance(KhaibarUsersBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}



}

