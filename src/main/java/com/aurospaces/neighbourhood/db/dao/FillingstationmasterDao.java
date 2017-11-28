
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseFillingstationmasterDao;




@Repository(value = "fillingstationmasterDao")
public class FillingstationmasterDao extends BaseFillingstationmasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<FillingstationmasterBean> getFillingStationAllData(){  
		 jdbcTemplate = custom.getJdbcTemplate();
		return jdbcTemplate.query("SELECT f.*,CASE WHEN f.status IN ('0') THEN 'Deactive' WHEN f.status in ('1') THEN 'Active'  ELSE '-----' END as fillingStatus  from fillingstationmaster f", new BeanPropertyRowMapper(FillingstationmasterBean.class));
			
		    
		}  
	
	@Transactional
	public boolean deleteFillingStationData(int id) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		try{
			String sql = "Update  fillingstationmaster set status='0' WHERE id=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{id});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}
	
	
	public FillingstationmasterBean getByFillingstationById(FillingstationmasterBean fillingstationmasterBean) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from fillingstationmaster where unitpoint = ?";
			List<FillingstationmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{fillingstationmasterBean.getUnitpoint()},
			ParameterizedBeanPropertyRowMapper.newInstance(FillingstationmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	


}

