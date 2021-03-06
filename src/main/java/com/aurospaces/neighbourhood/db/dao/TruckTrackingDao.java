
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.TruckTrackingBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTruckTrackingDao;




@Repository(value = "truckTrackingDao")
public class TruckTrackingDao extends BaseTruckTrackingDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public List<TruckTrackingBean> getTruckTrackingAll(String status,String truckStatus){  
		jdbcTemplate = custom.getJdbcTemplate();
		 
		
		 String sql =  "select tm.trucknumber ,tt.*,CASE WHEN tt.status IN ('0') THEN 'Deactive' WHEN tt.status in ('1') THEN 'Active'  ELSE '-----' END as truckTrackingStatus from truck_tracking tt,trucksmaster tm where tm.id=tt.truckId and tt.status= '"+status+"' and truckStatus='"+truckStatus+"' order by id desc";
		List<TruckTrackingBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(TruckTrackingBean.class));
		
		if (retlist.size() > 0)
			return retlist;
		return null;
		    
		}  
  public TruckTrackingBean getTruckDetails(String truckId){
	  jdbcTemplate = custom.getJdbcTemplate();
	  
	  String sql = "SELECT * FROM `truck_tracking` WHERE truckId=? ORDER BY `created_time` DESC";
	  List<TruckTrackingBean>  retlist = jdbcTemplate.query(sql,new Object[]{truckId},ParameterizedBeanPropertyRowMapper.newInstance(TruckTrackingBean.class));
	  if(retlist.size() > 0)
			return retlist.get(0);
		return null;
	  
  }

}

