
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
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
	
	public List<CylindermasterBean> getCylinders(String status){  
		jdbcTemplate = custom.getJdbcTemplate();
		 
		 //String sql="SELECT *, DATE_FORMAT(expirydate,'%d/%m/%Y') AS expirtdate1  FROM cylindermaster";
		
		 String sql =  "SELECT sm.storename,co.companyname , c. *,cs.name as cylinderstatus,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%b-%Y') AS expirtdate1 , CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM companymaster co,cylindermaster c,items i,cylinderstatus cs,storesmaster sm where  c.size=i.id and cs.id=c.cylinderstatus and co.id=c.ownercompany and sm.id=c.store and c.status='"+status+"' order by c.id desc";
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
			String sql = "SELECT * from cylindermaster where id = ?";
			List<CylindermasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{cylindermasterBean.getId()},
			ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	
	
		public   List<Map<String, Object>>  getCylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT a.name as cylinderstatus, IFNULL(b.statuscount,0) as count	FROM cylinderstatus a	left outer join (SELECT cylinderstatus, IFNULL(count(cylinderstatus), 0) as statuscount FROM cylindermaster,`items` i WHERE i.id=cylindermaster.`size` AND i.`itemType`='Cylinder'  group by cylinderstatus) b	on a.id = b.cylinderstatus; ";
			   
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
		 
			String sql = "  select * from lpoitems lpn,items i ,lpomaster lpm where lpn.itemid=i.id and lpm.lponumber=lpn.lponumber and  i.itemType='Cylinder' and  lpn.itemid=? and lpm.status='1' group by lpn.lponumber";
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
	public boolean updateCylinderStatus(CylindermasterBean cylindermasterBean) {
		jdbcTemplate = custom.getJdbcTemplate();
		boolean update = false;
		
		try{
			StringBuffer buffer = new StringBuffer();
			buffer.append("Update  cylindermaster set ");
			if(StringUtils.isNotBlank(cylindermasterBean.getCylinderstatus())){
				buffer.append(" cylinderstatus= '"+cylindermasterBean.getCylinderstatus()+"'");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getFillingstationId())){
				buffer.append(" ,fillingstationId= '"+cylindermasterBean.getFillingstationId()+"'");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getTruckId())){
				buffer.append(" ,truckId= '"+cylindermasterBean.getTruckId()+"'");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getStore())){
				buffer.append(",store= '"+cylindermasterBean.getStore()+"'");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getCustomerid())){
				buffer.append(",customerid= '"+cylindermasterBean.getCustomerid()+"'");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getOwnercompany())){
				buffer.append(",ownercompany= '"+cylindermasterBean.getOwnercompany()+"'");
			}
			if(cylindermasterBean.getId() !=0){
				buffer.append(" WHERE id= '"+cylindermasterBean.getId()+"'"); 
			}
//			String sql = "Update  cylindermaster set cylinderstatus= ?,fillingstationId=?  WHERE cylinderid=?";
			String sql = buffer.toString();
			System.out.println("sql------------"+sql);
			int intDelete = jdbcTemplate.update(sql, new Object[]{});
			System.out.println("intDelete------------"+intDelete);
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
			String sql = "update cylindermaster c set  c.cylinderid= concat((select SUBSTR(cy.name,1,1) from items cy where cy.id=c.size),LPAD( c.id, 9, '0')) ";
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
			String sql =  "select  cm.id, cm.cylinderid,s.storename ,i.name  from cylindermaster cm,items i,storesmaster s where cm.store=s.id and i.id=cm.size and cm.store=? and cm.size=? and cm.cylinderstatus=?  limit ?";
				retlis = jdbcTemplate.query(sql, new Object[] {sStore,cylinderType,cylinderstatus,limit },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			System.out.println("-----------list----------"+retlis);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}
	/*public List<CylindermasterBean> onChangeStoreAndCylinderType(String sStore,String cylinderType,String cylinderstatus) {
		jdbcTemplate = custom.getJdbcTemplate();
		List<CylindermasterBean> retlis=null;
		try{
			String sql =  "select Count(cm.cylinderstatus)as count1 from cylindermaster cm,items i,storesmaster s where cm.store=s.id and i.id=cm.size and cm.store=? and cm.size=? and cm.cylinderstatus=?";
				retlis = jdbcTemplate.query(sql, new Object[] {sStore,cylinderType,cylinderstatus },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			System.out.println("--------onChangeStoreAndCylinderType---list----------"+retlis.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}*/
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
			buffer.append("select cm.id, cm.cylinderid,f.stationname ,i.name  from cylindermaster cm,items i,fillingstationmaster f where cm.size=i.id and f.id=cm.fillingstationId ");
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
			System.out.println("-----------list----------"+retlis+"sql----"+sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retlis;
	}
	 @SuppressWarnings("deprecation")
		public  int  getTotalcylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql=" SELECT  COUNT(*)    FROM cylindermaster cm,items i WHERE cm.`size`=i.id AND i.`itemType`='Cylinder' ";
			   
			   return jdbcTemplate.queryForInt(sql);
		}
	 public CylindermasterBean getByCylinderName(int  cylinderId) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT i.name  from cylindermaster c,items i where i.id=c.size and c.id = ?";
			List<CylindermasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{cylinderId},
			ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<CylindermasterBean> getInTruckCylinders(String truckId,String size,int limit){
		 jdbcTemplate = custom.getJdbcTemplate();
		 String sql = "select * from cylindermaster where truckId=? and size=? and cylinderstatus='5' limit ? ";
		 List<CylindermasterBean> result = jdbcTemplate.query(sql,new Object[]{truckId,size,limit},ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
		 if(result.size()>0)
			 return result;
		return null;
		 
	 }
	 public List<CylindermasterBean> searchReturnQualityChecking(String cylinderType,int limit,String cylinderstatus) {
			jdbcTemplate = custom.getJdbcTemplate();
			List<CylindermasterBean> retlis=null;
			try{
				String sql =  "select  cm.id, cm.cylinderid,i.name  from cylindermaster cm,items i where  i.id=cm.size and cm.size=? and cm.cylinderstatus=?  limit ?";
					retlis = jdbcTemplate.query(sql, new Object[] {cylinderType,cylinderstatus,limit },
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
				System.out.println("-----------list----------"+retlis);
			}catch(Exception e){
				e.printStackTrace();
			}
			return retlis;
		}
	 public List<CylindermasterBean> onChangeStoreAndCylinderType(CylindermasterBean cylindermasterBean) {
			jdbcTemplate = custom.getJdbcTemplate();
			List<CylindermasterBean> retlis=null;
			try{
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(cylinderstatus) as count1 from cylindermaster where 1=1");
				
				if(StringUtils.isNotBlank(cylindermasterBean.getFillingstationId())){
					buffer.append(" and fillingstationId="+cylindermasterBean.getFillingstationId() );
				}
				if(StringUtils.isNotBlank(cylindermasterBean.getSize())){
					buffer.append(" and size="+cylindermasterBean.getSize() );			
				}
				if(StringUtils.isNotBlank(cylindermasterBean.getStore())){
					buffer.append(" and store= "+cylindermasterBean.getStore());
				}
				if(StringUtils.isNotBlank(cylindermasterBean.getCylinderstatus())){
					buffer.append(" and cylinderstatus= "+cylindermasterBean.getCylinderstatus());
				}
				if(StringUtils.isNotBlank(cylindermasterBean.getTruckId())){
					buffer.append(" and truckId= "+cylindermasterBean.getTruckId());
				}
				String sql = buffer.toString();
				System.out.println("----sql---"+sql);
					retlis = jdbcTemplate.query(sql, new Object[] { },
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
			}catch(Exception e){
				e.printStackTrace();
			}
			return retlis;
		}
	 public String onChangeReports(CylindermasterBean cylindermasterBean) {
			jdbcTemplate = custom.getJdbcTemplate();
			List<CylindermasterBean> retlis=null;
			List<CylindermasterBean> retlis1=null;
			 JSONObject jsonObject= new JSONObject();
			try{
				if(StringUtils.isNotBlank(cylindermasterBean.getOwnercompany())){
					String sql = "select count(ownercompany)as count1 from cylindermaster where ownercompany=?";
					System.out.println("----sql---"+sql);
					retlis = jdbcTemplate.query(sql, new Object[] {cylindermasterBean.getOwnercompany()},
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
					String sql1 = "select lponumber,cylinderid,color from cylindermaster where ownercompany=?";
					System.out.println("----sql1---"+sql1);
					retlis1 =jdbcTemplate.query(sql1, new Object[] {cylindermasterBean.getOwnercompany()},
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
				}else if(StringUtils.isNotBlank(cylindermasterBean.getStorename())){
					String sql = "select count(store)as count1 from cylindermaster where store=?";
					System.out.println("----sql---"+sql);
					retlis = jdbcTemplate.query(sql, new Object[] {cylindermasterBean.getStorename() },
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
					String sql1 = "select lponumber,cylinderid,color from cylindermaster where store=?";
					System.out.println("----sql1---"+sql1);
					retlis1 =  jdbcTemplate.query(sql1, new Object[] {cylindermasterBean.getStorename() },
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
				}else if(StringUtils.isNotBlank(cylindermasterBean.getCylinderstatus())){
					String sql = "select count(cylinderstatus)as count1 from cylindermaster where cylinderstatus=?";
					System.out.println("----sql---"+sql);
					retlis = jdbcTemplate.query(sql, new Object[] {cylindermasterBean.getCylinderstatus()},
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
					String sql1 = "select lponumber,cylinderid,color from cylindermaster where cylinderstatus=?";
					System.out.println("----sql1---"+sql1);
					retlis1 = jdbcTemplate.query(sql1, new Object[] {cylindermasterBean.getCylinderstatus()},
						ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
					
				}
				
				jsonObject.put("count", retlis);
				jsonObject.put("listJson", retlis1);
				
				 // retlis.addAll(retlis1);
				  
				System.out.println("----list---"+jsonObject);
				
					
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(jsonObject);
		}
	 public List<CylindermasterBean> getCylindersReport(CylindermasterBean cylindermasterBean){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT DATE_FORMAT(c.created_time,'%d-%b-%Y') AS createdOn, co.companyname , c. *,cs.name as cylinderstatus,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%b-%Y') AS expirtdate1 , CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM companymaster co,cylindermaster c,items i,cylinderstatus cs where c.size=i.id and cs.id=c.cylinderstatus and co.id=c.ownercompany ");
			if(StringUtils.isNotBlank(cylindermasterBean.getOwnercompany())){
				buffer.append(" and c.ownercompany = "+cylindermasterBean.getOwnercompany());
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getStorename())){
				buffer.append(" and c.store = "+cylindermasterBean.getStorename() +"  and c.cylinderstatus = '1' "  );
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getCylinderstatus())){
				buffer.append(" and c.cylinderstatus = "+cylindermasterBean.getCylinderstatus());
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getLponumber())){
				buffer.append(" and c.lponumber = '"+cylindermasterBean.getLponumber()+"' ");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getSize())){
				buffer.append(" and c.size = '"+cylindermasterBean.getSize()+"' ");
			}
			String sql = buffer.toString();
			System.out.println(sql);
//			 String sql =  "SELECT co.companyname , c. *,cs.name as cylinderstatus,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%b-%Y') AS expirtdate1 , CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM companymaster co,cylindermaster c,items i,cylinderstatus cs where c.size=i.id and cs.id=c.cylinderstatus and co.id=c.ownercompany  order by c.id desc";
			List<CylindermasterBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
					ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			} 
	 public int truckincylinderscount(String truckId,String itemId) {
 			jdbcTemplate = custom.getJdbcTemplate();
			int  retlis=0;
			try{
				String sql =  "SELECT COUNT(*) FROM `cylindermaster` WHERE `size`=? AND `truckId` =? AND `cylinderstatus`='5'";
					retlis = jdbcTemplate.queryForInt(sql, new Object[] {itemId,truckId });
				System.out.println("-----------list----------"+retlis);
			}catch(Exception e){
				e.printStackTrace();
			}
			return retlis;
		}
	 
	 
		public   JSONObject  getCylindersStatusCount(){  
			JSONObject jsonObject = new JSONObject();
			 jdbcTemplate = custom.getJdbcTemplate();
			 String sql = null;
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='1' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			   
			 List<CylindermasterBean> emptylist =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("emptylist", emptylist);
			 
			 
			  sql="SELECT  i.id, i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='2' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> fillingStation =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("fillingStation", fillingStation);
			 
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='3' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> filled =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("filled", filled);
			 
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='4' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> qualitycheck =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("qualitycheck", qualitycheck);
			 
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='5' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> truck =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("truck", truck);
			 
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='6' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> delivered =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("delivered", delivered);
			 
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='7' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> returned =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("returned", returned);
			 
			 
			  sql="SELECT   i.id,i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm   WHERE cm.cylinderstatus='8' AND cm.size=i.id AND cm.`status`='1') count FROM `items` i  WHERE `itemType`='Cylinder' AND i.status='1'; ";
			 List<CylindermasterBean> missedCylinder =  jdbcTemplate.query(sql, new Object[] {  },	ParameterizedBeanPropertyRowMapper.newInstance(CylindermasterBean.class));
			 jsonObject.put("missedCylinder", missedCylinder);
			 
			 
			 
			 
			 
			 
				return jsonObject;
		}
		
		
		public   List<Map<String, Object>>  getGassummary(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT 'Private Cylinders' AS gastype, SUM((pc.`quantity`)*(CAST(i.name AS UNSIGNED))) AS sum_of_gas    FROM `privatecylinderfilledprice` pc,items i  WHERE pc.`items` =i.id    AND i.`itemType`='Cylinder'     GROUP BY i.`itemType`='Cylinder'      UNION ALL      SELECT 'Khaibar Cylinders' AS gastype,SUM((1)*(CAST(i.name AS UNSIGNED))) AS sum_of_gas  FROM `cylindertransaction` ct,`cylindermaster` cm ,items i  WHERE ct.`cylinderStatus`='3'  AND cm.id=ct.`cylindetId` AND i.id=cm.size AND i.`itemType`='Cylinder'    GROUP BY i.`itemType`='Cylinder '  UNION ALL SELECT 'Remaining Gas' AS gastype, SUM(closingBalanceGas) AS sum_of_gas FROM fillingstationmaster WHERE status=1   ";
			   
			 List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
				return retlist;
		}
		public   List<Map<String, Object>>  getExpensessummary(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT `accountHead`,SUM(amount) AS amount FROM   `expensetracker` GROUP BY accountHead ";
			   
			 List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
				return retlist;
		}
		public   List<Map<String, Object>>  totalusagegasreport(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT 'Filling station available gas' AS gastype, SUM(`closingBalanceGas`) AS sum_of_gas FROM `fillingstationmaster` UNION ALL  " 

						+" SELECT 'Private Cylinders filled gas ' AS gastype, SUM((pc.`quantity`)*(CAST(i.name AS UNSIGNED))) AS sum_of_gas "  
						 +" FROM `privatecylinderfilledprice` pc,items i  WHERE pc.`items` =i.id    AND i.`itemType`='Cylinder' "   
						  
						  +"  GROUP BY i.`itemType`='Cylinder'      UNION ALL "      
						  +"  SELECT 'Khaibar Cylinders delivered gas' AS gastype, " 
						  +"  SUM((1)*(CAST(i.name AS UNSIGNED))) AS sum_of_gas  FROM `cylindertransaction` ct,`cylindermaster` cm , " 
						  +"  items i  WHERE ct.`cylinderStatus`='6'  AND cm.id=ct.`cylindetId` AND i.id=cm.size AND i.`itemType`='Cylinder'  " 
						    +"  GROUP BY i.`itemType`='Cylinder '  " 
						   +"   UNION ALL      " 
						      
						   +" SELECT 'Khaibar Cylinders filled gas ' AS gastype, " 
						 +"   SUM((1)*(CAST(i.name AS UNSIGNED))) AS sum_of_gas  FROM `cylindermaster` cm , "  
						 +"   items i  WHERE cm.`cylinderStatus` IN (3,4,5)   AND i.id=cm.size AND i.`itemType`='Cylinder'  " 
						  +"    GROUP BY i.`itemType`='Cylinder '   ";
			   
			 List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
				return retlist;
		}
		public   List<Map<String, Object>>  getTotalCylindersCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT a.name AS label, IFNULL(b.statuscount,0) AS Y	FROM cylinderstatus a	LEFT OUTER JOIN (SELECT cylinderstatus, IFNULL(COUNT(cylinderstatus), 0) AS statuscount FROM cylindermaster,`items` i WHERE i.id=cylindermaster.`size` AND i.`itemType`='Cylinder'  GROUP BY cylinderstatus) b	ON a.id = b.cylinderstatus;  ";
			   
			 List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
				return retlist;
		}
		public   List<Map<String, Object>>  getFillingStationcount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT SUM(`gasavailability`) AS gasavailability,SUM(`usedGas`) AS usedGas,SUM(`closingBalanceGas`) AS closingBalanceGas FROM `fillingstationmaster` ";
			   
			 List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
				return retlist;
		}
		public List<Map<String,Object>> getCylindersStatuscount(CylindermasterBean cylindermasterBean){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT cs.`name`,COUNT(*) AS COUNT FROM `cylindermaster` c,`cylinderstatus` cs WHERE cs.id=c.`cylinderstatus` ");
			if(StringUtils.isNotBlank(cylindermasterBean.getOwnercompany())){
				buffer.append(" and c.ownercompany = "+cylindermasterBean.getOwnercompany());
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getStorename())){
				buffer.append(" and c.store = "+cylindermasterBean.getStorename() +"  and c.cylinderstatus = '1' "  );
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getCylinderstatus())){
				buffer.append(" and c.cylinderstatus = "+cylindermasterBean.getCylinderstatus());
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getLponumber())){
				buffer.append(" and c.lponumber = '"+cylindermasterBean.getLponumber()+"' ");
			}
			if(StringUtils.isNotBlank(cylindermasterBean.getSize())){
				buffer.append(" and c.size = '"+cylindermasterBean.getSize()+"' ");
			}
			buffer.append("  GROUP BY `cylinderstatus` ");
			
			String sql = buffer.toString();
			System.out.println(sql);
//			 String sql =  "SELECT co.companyname , c. *,cs.name as cylinderstatus,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%b-%Y') AS expirtdate1 , CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM companymaster co,cylindermaster c,items i,cylinderstatus cs where c.size=i.id and cs.id=c.cylinderstatus and co.id=c.ownercompany  order by c.id desc";
			List<Map<String,Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] {  });
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			}
		
//	 SELECT  i.`name`,  (SELECT  COUNT(*)   FROM cylindermaster cm	   WHERE cm.cylinderstatus='1' AND cm.size=i.id ) counts	FROM `items` i  WHERE `itemType`='Cylinder'
}

