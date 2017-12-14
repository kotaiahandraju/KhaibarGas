
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
		
		 String sql =  "SELECT c. *,cs.name as cylinderstatus,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%M-%Y') AS expirtdate1 ,  CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM cylindermaster c,items i,cylinderstatus cs where c.size=i.id and cs.id=c.cylinderstatus";
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
	
	
		public   List<Map<String, Object>>  getCylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="select IFNULL(count(c.cylinderstatus), 0) as count,cs.name as  cylinderstatus from cylindermaster c,cylinderstatus cs where cs.id= c.cylinderstatus   group by c.cylinderstatus ";
			   
			 List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
				return retlist;
		}
	 
	
	public List<CylinderTypesBean> getCylinderstypes() {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select * from items where itemType='Cylinder' and status='1' ";
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
			String sql = " select * from lpoitems lpn,items i where lpn.itemid=i.id and i.itemType='Cylinder' and  i.id=? group by lponumber";
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
			StringBuffer buffer = new StringBuffer();
			buffer.append("Update  cylindermaster set ");
			if(StringUtils.isNotBlank(cylinderStatus)){
				buffer.append(" cylinderstatus= '"+cylinderStatus+"'");
			}
			if(StringUtils.isNotBlank(fillingStation)){
				buffer.append(" ,fillingstationId= '"+fillingStation+"'");
			}
			if(StringUtils.isNotBlank(cylinderId)){
				buffer.append(" WHERE cylinderid= '"+cylinderId+"'"); 
			}
//			String sql = "Update  cylindermaster set cylinderstatus= ?,fillingstationId=?  WHERE cylinderid=?";
			String sql = buffer.toString();
			int intDelete = jdbcTemplate.update(sql, new Object[]{});
			if(intDelete != 0){
				update = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
	}

	public boolean updateCylinderStatus1(String cylinderId,String cylinderStatus,String truckId) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean update = false;
		try{
			String sql = "Update  cylindermaster set cylinderstatus= ?,truckId=?  WHERE cylinderid=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{cylinderStatus,truckId,cylinderId});
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
	
	public List<CylindermasterBean> searchCylinderMoveToFilling(String sStore,String cylinderType,int limit,String cylinderstatus) {
		jdbcTemplate = custom.getJdbcTemplate();
		List<CylindermasterBean> retlis=null;
		try{
			String sql =  "select cm.cylinderid,s.storename ,i.name  from cylindermaster cm,items i,storesmaster s where cm.store=s.id and i.id=cm.size and cm.store=? and cm.size=? and cm.cylinderstatus=?  limit ?";
				retlis = jdbcTemplate.query(sql, new Object[] {sStore,cylinderType,cylinderstatus,limit },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			System.out.println("-----------list----------"+retlis);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}
	public List<CylindermasterBean> searchFillingStationInQualitycheck(String stationname,String name) {
		jdbcTemplate = custom.getJdbcTemplate();
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
	public List<CylindermasterBean> searchQualityCheck(String fillingStationId,String cylinderType,int limit,String cylinderstatus) {
		jdbcTemplate = custom.getJdbcTemplate();
		List<CylindermasterBean> retlis=null;
		try{
			StringBuffer buffer = new StringBuffer();
			buffer.append("select cm.cylinderid,f.stationname ,i.name  from cylindermaster cm,items i,fillingstationmaster f where cm.size=i.id and f.id=cm.fillingstationId ");
			if(StringUtils.isNotBlank(fillingStationId)){
				buffer.append(" and cm.fillingstationId="+fillingStationId );
			}
			if(StringUtils.isNotBlank(cylinderType)){
				buffer.append(" and cm.size="+cylinderType );			
			}
			if(StringUtils.isNotBlank(cylinderstatus)){
				buffer.append(" and cm.cylinderstatus= "+cylinderstatus);
			}
			if(StringUtils.isNotBlank(fillingStationId)){
				buffer.append(" limit "+limit);
			}
//			String sql =  "select cm.cylinderid,f.stationname ,i.name  from cylindermaster cm,items i,fillingstationmaster f where cm.size=i.id and f.id=cm.fillingstationId and cm.size=? and cm.fillingstationId= ? and cm.cylinderstatus= ?   limit ?";
			String sql = buffer.toString();
				retlis = jdbcTemplate.query(sql, new Object[] { },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			System.out.println("-----------list----------"+retlis);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}
	 @SuppressWarnings("deprecation")
		public  int  getTotalcylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT  count(*)    FROM cylindermaster";
			   
			   return jdbcTemplate.queryForInt(sql);
		}
}

