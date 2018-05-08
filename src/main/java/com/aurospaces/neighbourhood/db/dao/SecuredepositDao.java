/**
 * 
 */
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.SecuredepositBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSecuredepositDao;

/**
 * @author Kotaiah
 *
 */
@Repository(value = "SecuredepositDao")
public class SecuredepositDao extends BaseSecuredepositDao {
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<SecuredepositBean> getAllData(){  
		jdbcTemplate = custom.getJdbcTemplate();
		 
		 //String sql="SELECT *, DATE_FORMAT(expirydate,'%d/%m/%Y') AS expirtdate1  FROM cylindermaster";
		
		 String sql =  "SELECT com.companyname as companyName,sd.`customerId`,`securityDeposit`,`amount`,`itemId`,IFNULL(`quantity`,'--') as quantity,`companyId`,sd.`remarks`,IFNULL(i.name,'--') AS itemName,cm.`customername`,cm.customerid as userId  FROM  `securedeposit` sd LEFT JOIN `items` i ON sd.`itemId`=i.id LEFT JOIN `customermaster` cm ON cm.id=sd.`customerId` left join companymaster com on com.id=sd.companyId order by sd.id desc";
		List<SecuredepositBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(SecuredepositBean.class));
		
		if (retlist.size() > 0)
			return retlist;
		return null;
		    
		} 
}
