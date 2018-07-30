
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
			String sql = " SELECT foo.name,SUM(foo.counts) AS counts,CONCAT(MONTHNAME(foo.label),'- ', YEAR(foo.label)) AS label FROM( "
						+" SELECT i.name,COUNT(*) AS counts ,STR_TO_DATE(pc.`cylinderFilledDate`,'%d-%M-%Y') AS label  FROM "
						+" `privatecylinderfilledprice` pc,items i WHERE i.id =pc.`items` GROUP BY STR_TO_DATE(pc.`cylinderFilledDate`,'%d-%M-%Y'), i.id "
						+"	UNION ALL "
						+"	SELECT i.`name`,COUNT(*) AS counts ,DATE(ct.created_time) AS label  "
						+"	FROM cylindertransaction ct, `cylindermaster` cm ,items i WHERE i.id=cm.size AND ct.`cylindetId`=cm.id  "
						+"	AND i.`itemType`='Cylinder' AND ct.cylinderStatus='3' GROUP BY date(ct.created_time),i.id  "
						+"	 ) foo GROUP BY foo.name,CONCAT(MONTHNAME(foo.label),'- ', YEAR(foo.label))    ";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
			return retlist;

		}

}

