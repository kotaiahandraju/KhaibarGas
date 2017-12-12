
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseExpensetrackerDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "ExpensetrackerDao")
public class ExpensetrackerDao extends BaseExpensetrackerDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public String getAllExpenseTracker() {
		List<Expensetracker> expensetracker = null;
		ObjectMapper objectMapper=null;
		String sJson=null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT  *from expensetracker ";
			System.out.println("sql:::"+sql);
			expensetracker = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Expensetracker>(Expensetracker.class));
			if(expensetracker !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(expensetracker);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}


}

