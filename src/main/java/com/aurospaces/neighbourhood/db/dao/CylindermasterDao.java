
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.LpoitemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCylindermasterDao;




@Repository(value = "cylindermasterDao")
public class CylindermasterDao extends BaseCylindermasterDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	@SuppressWarnings("unchecked")
	
	public List<CylindermasterBean> getCylinders(){  
		jdbcTemplate = custom.getJdbcTemplate();
		 
		 //String sql="SELECT *, DATE_FORMAT(expirydate,'%d/%m/%Y') AS expirtdate1  FROM cylindermaster";
		
		 String sql =  "SELECT c. *,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%M-%Y') AS expirtdate1 ,  CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM cylindermaster c,items i where c.size=i.id";
		List<CylindermasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
				ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
		
		if (retlist.size() > 0)
			return retlist;
		return null;
		    
		}  
	

	@Transactional
	public boolean deleteCylinder(int id,String status) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		try{
			String sql = "Update  cylindermaster set status='"+status+"' WHERE id=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{id});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}
	
	public CylindermasterBean getByCylinderId(CylindermasterBean cylindermasterBean) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from cylindermaster where cylinderid = ?";
			List<CylindermasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{cylindermasterBean.getCylinderid()},
			ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	
	
	 @SuppressWarnings("deprecation")
		public  int  getCylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT  count(*)    FROM cylindermaster";
			   
			   return jdbcTemplate.queryForInt(sql);
		}
	 
	
	public List<CylinderTypesBean> getCylinderstypes() {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select name,id from items where id in (1,2,3)";
			@SuppressWarnings("rawtypes")
			List list=jdbcTemplate.queryForList(sql);
			List<CylinderTypesBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CylinderTypesBean.class));
			return retlist;

		}

	 
	 /**
	 * Retreives cylinder object by input capacity 
	 */
	public int getCylinderIdByCapacity(String capacity) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select id from cylindertypes where capacity=?";
			int value =jdbcTemplate.queryForObject(sql, new Object[] { capacity }, Integer.class);
			return value;

		}

	
	public List<LpoitemsBean> getCylinderCapacityByID(String itemid) {
		 jdbcTemplate = custom.getJdbcTemplate();
		 //String retlist=null;
		 List<LpoitemsBean> retlist=null;
			String sql = "select lponumber,itemid from lpoitems where itemid=? group by lponumber,itemid";
			retlist= jdbcTemplate.query(sql, new Object[] { itemid },ParameterizedBeanPropertyRowMapper.newInstance(LpoitemsBean.class));
			 
			 return retlist;
		}
	
	public List<CylindermasterBean> getEmptyCylinders( String cylinderstatus){  
		jdbcTemplate = custom.getJdbcTemplate();
		 String sql =  "SELECT  c.*,it.name,s.storename FROM cylindermaster c,items it,storesmaster s  where c.size=it.id and s.id=c.store  and c.cylinderstatus=?";
		List<CylindermasterBean> retlist = jdbcTemplate.query(sql, new Object[] {cylinderstatus },
				ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
		
		if (retlist.size() > 0)
			return retlist;
		return null;
		    
		} 
	public boolean updateCylinderStatus(String cylinderId,String cylinderStatus,String fillingStation) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean update = false;
		try{
			String sql = "Update  cylindermaster set cylinderstatus= ?,fillingstationId=?  WHERE cylinderid=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{cylinderStatus,fillingStation,cylinderId});
			if(intDelete != 0){
				update = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
	}



	public boolean updateCylinderIds() {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		try{
			String sql = "update cylindermaster c set  c.cylinderid= concat((select SUBSTR(cy.name,1,1) from cylindertypes cy where cy.id=c.size),LPAD( c.id, 9, '0')) ";
			int intDelete = jdbcTemplate.update(sql, new Object[]{});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}
		
	public List<LpomasterBean> populate(String sql ){
		 jdbcTemplate = custom.getJdbcTemplate();
				List<LpomasterBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
					return retlist;
		 }
	
	public List<CylindermasterBean> searchCylinderMoveToFilling(String sStore,String name,int limit) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		List<CylindermasterBean> retlis=null;
		try{
			String sql =  "select c.cylinderid,it.name,c.size,st.storename from cylindermaster c,items it,storesmaster st where c.store=? and c.size=? and it.id=c.size and c.store=st.id limit ?";
				retlis = jdbcTemplate.query(sql, new Object[] {sStore,name,limit },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			System.out.println("-----------list----------"+retlis);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}
	public List<CylindermasterBean> searchFillingStationInQualitycheck(String stationname,String name) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean delete = false;
		List<CylindermasterBean> retlis=null;
		try{
			String sql =  "select c.*,ct.name,s.storename from cylindermaster c,storesmaster s,cylindertypes ct where c.fillingstationId=? and ct.id=? and s.id=store";
				retlis = jdbcTemplate.query(sql, new Object[] {stationname,name },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			System.out.println("-----------list----------"+retlis.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}
	
	public List<LpoitemsBean> getMadeByAndExparidate(String itemid,String lponumber) {
		 jdbcTemplate = custom.getJdbcTemplate();
		 //String retlist=null;
		 List<LpoitemsBean> retlist=null;
			String sql = "select l.suppliername,li.expiryDate from lpomaster l ,lpoitems li where l.lponumber =li.lponumber and li.itemid=? and li.lponumber =? ";
			retlist= jdbcTemplate.query(sql, new Object[] { itemid,lponumber },ParameterizedBeanPropertyRowMapper.newInstance(LpoitemsBean.class));
			 System.out.println("---------retList"+retlist.size());
			 return retlist;
		}

	
}

