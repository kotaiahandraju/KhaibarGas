
package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseLpoitemsDao;




@Repository(value = "lpoitemsDao")
public class LpoitemsDao extends BaseLpoitemsDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public void deleteLPONumber(String  lpoNumber) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "DELETE FROM lpoitems WHERE lponumber=?";
		jdbcTemplate.update(sql, new Object[]{lpoNumber});
	}
	


}

