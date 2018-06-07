
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseAddGasDao;




@Repository(value = "AddGasDao")
public class AddGasDao extends BaseAddGasDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getdata() {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = " SELECT i.`name`,COUNT(*) AS counts ,CONCAT(MONTHNAME(ct.created_time),'- ', YEAR(ct.created_time)) AS label FROM cylindertransaction ct, `cylindermaster` cm ,items i WHERE i.id=cm.size AND ct.`cylindetId`=cm.id AND i.`itemType`='Cylinder' and ct.cylinderStatus='3' GROUP BY DATE_FORMAT(ct.created_time,'%Y-%m'),i.id ORDER BY MONTH(ct.created_time) ASC  ";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
			return retlist;

		}

}

