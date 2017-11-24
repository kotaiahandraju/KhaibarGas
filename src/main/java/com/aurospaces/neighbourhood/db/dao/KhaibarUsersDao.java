
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.KhaibarUsersBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseKhaibarUsersDao;




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


}

