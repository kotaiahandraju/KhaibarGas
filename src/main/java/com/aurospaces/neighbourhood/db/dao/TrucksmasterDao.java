
package com.aurospaces.neighbourhood.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTrucksmasterDao;




@Repository(value = "trucksmasterDao")
public class TrucksmasterDao extends BaseTrucksmasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 

}

