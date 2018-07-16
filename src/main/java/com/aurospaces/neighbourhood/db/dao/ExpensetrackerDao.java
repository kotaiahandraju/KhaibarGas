
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.bean.UsedGasBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseExpensetrackerDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "ExpensetrackerDao")
public class ExpensetrackerDao extends BaseExpensetrackerDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public String getAllExpenseTracker(String status) {
		List<Expensetracker> expensetracker = null;
		ObjectMapper objectMapper=null;
		String sJson=null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT e.*,CASE WHEN e.status IN ('0') THEN 'Deactive' WHEN e.status in ('1') THEN 'Active'  ELSE '-----' END as trackrstatus  from expensetracker e where e.status='"+status+"'  order by e.id desc";
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

	 public List<Expensetracker> getExpensesReport(Expensetracker expensetracker,String month,String year){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT e.*, STR_TO_DATE(`dateOfExpense`, '%d-%b-%Y') AS date1 FROM    `expensetracker` e  where 1=1 ");
			
			if(StringUtils.isNotBlank(expensetracker.getAccountHead()) ){
				buffer.append(" and accountHead='"+expensetracker.getAccountHead()+"' " );
			}
			if(StringUtils.isNotBlank(expensetracker.getMonth())){
				buffer.append(" AND MONTH(STR_TO_DATE(`dateOfExpense`, '%d-%b-%Y'))='"+month+"'  AND YEAR(STR_TO_DATE(`dateOfExpense`, '%d-%b-%Y')) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(expensetracker.getFromDate()) &&  StringUtils.isNotBlank(expensetracker.getToDate())){
				buffer.append(" HAVING  date1 BETWEEN '"+expensetracker.getFromDate()+"' AND '"+expensetracker.getToDate()+"' " );
			}
			
			String sql = buffer.toString();
			System.out.println(sql);
//			 String sql =  "SELECT co.companyname , c. *,cs.name as cylinderstatus,i.name As sizeName,DATE_FORMAT(c.expirydate,'%d-%b-%Y') AS expirtdate1 , CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as cylendersstatus   FROM companymaster co,cylindermaster c,items i,cylinderstatus cs where c.size=i.id and cs.id=c.cylinderstatus and co.id=c.ownercompany  order by c.id desc";
			List<Expensetracker> retlist = jdbcTemplate.query(sql, new Object[] {  },
					ParameterizedBeanPropertyRowMapper.newInstance(Expensetracker.class));
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			} 
	 
	 public List<UsedGasBean> getSearchReport(UsedGasBean usedGasBean,String month,String year){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select * from (SELECT `created_time`,`fillingStationId`,`fillingstationname`,`gasInKgs`,`closedgas`,DATE_FORMAT(created_time,'%d-%b-%Y') AS expirtdate1,CONCAT('Added Gas', '(', IFNULL(addgas.lponumber,'--'), ')') AS AddedGas FROM addgas WHERE 1=1 ");
			if(StringUtils.isNotBlank(month)){
				 
				buffer.append("  AND  MONTH(created_time) ='"+month+"' AND YEAR(created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(created_time) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
				buffer.append(" and fillingStationId='"+usedGasBean.getFillingStationId()+"'  ");
			}
			buffer.append(" UNION ALL SELECT `created_time`,`fillingStationId`,`fillingstationname`,`gasInKgs`,`closedgas`,DATE_FORMAT(created_time,'%d-%b-%Y') AS expirtdate1,'Used Gas' AS UsedGas FROM usedgas where 1=1 ");
			if(StringUtils.isNotBlank(month)){
				 
				buffer.append("  AND  MONTH(created_time) ='"+month+"' AND YEAR(created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(created_time) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
				buffer.append(" and fillingStationId='"+usedGasBean.getFillingStationId()+"'  ");
			}
			buffer.append(" ) foo where 1=1 ");
			if(StringUtils.isNotBlank(usedGasBean.getGasType())){
				buffer.append(" and  foo.AddedGas LIKE '%"+usedGasBean.getGasType()+"%' ");
			}
			buffer.append(" ORDER BY created_time ");
			String sql = buffer.toString();
			System.out.println(sql);
			List<UsedGasBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
					ParameterizedBeanPropertyRowMapper.newInstance(UsedGasBean.class));
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			} 
	 
	
	 
	 public List<UsedGasBean> getGasReport(UsedGasBean usedGasBean,String month,String year){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append("  SELECT `created_time`,`fillingStationId`,`fillingstationname`,`gasInKgs`,`closedgas`,DATE_FORMAT(created_time,'%d-%b-%Y') AS expirtdate1,'Used Gas' AS AddedGas FROM usedgas where 1=1 ");
			if(StringUtils.isNotBlank(month)){
				 
				buffer.append("  AND  MONTH(created_time) ='"+month+"' AND YEAR(created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(created_time) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			if(StringUtils.isNotBlank(usedGasBean.getCustomerType())){
				buffer.append(" and customerType='"+usedGasBean.getCustomerType()+"'  ");
			}
			if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
				buffer.append(" and fillingStationId='"+usedGasBean.getFillingStationId()+"'  ");
			}
			buffer.append(" ORDER BY created_time ");
			String sql = buffer.toString();
			System.out.println(sql);
			List<UsedGasBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
					ParameterizedBeanPropertyRowMapper.newInstance(UsedGasBean.class));
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			} 
	 public List<UsedGasBean> getGascustomersummary(UsedGasBean usedGasBean,String month,String year){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append("  SELECT `created_time`,`fillingStationId`,GROUP_CONCAT( DISTINCT `fillingstationname`) AS fillingstationname,sum(`gasInKgs`) as gasInKgs,`closedgas`,DATE_FORMAT(created_time,'%d-%b-%Y') AS expirtdate1,'Used Gas' AS AddedGas FROM usedgas where 1=1 ");
			if(StringUtils.isNotBlank(month)){
				 
				buffer.append("  AND  MONTH(created_time) ='"+month+"' AND YEAR(created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(created_time) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			if(StringUtils.isNotBlank(usedGasBean.getCustomerType())){
				buffer.append(" and customerType='"+usedGasBean.getCustomerType()+"'  ");
			}
			if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
				buffer.append(" and fillingStationId='"+usedGasBean.getFillingStationId()+"'  ");
			}
			buffer.append("  GROUP BY DATE(created_time) ORDER BY  created_time ");
			String sql = buffer.toString();
			System.out.println(sql);
			List<UsedGasBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
					ParameterizedBeanPropertyRowMapper.newInstance(UsedGasBean.class));
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			} 
	/* public List<UsedGasBean> getSearchgassumaryreport(UsedGasBean usedGasBean,String month,String year){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append(" SELECT *FROM (SELECT `created_time`, `fillingStationId`,GROUP_CONCAT(DISTINCT  `fillingstationname`) AS fillingstationname,SUM(`gasInKgs`) as gasInKgs,`closedgas`, DATE_FORMAT(created_time,'%d-%b-%Y') AS expirtdate1,CONCAT('Added Gas', '(', IFNULL(addgas.lponumber,'--'), ')') AS AddedGas FROM addgas WHERE 1=1 ");
			if(StringUtils.isNotBlank(month)){
				 
				buffer.append("  AND  MONTH(created_time) ='"+month+"' AND YEAR(created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(created_time) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			
			if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
				buffer.append(" and fillingStationId='"+usedGasBean.getFillingStationId()+"'  ");
			}
			buffer.append(" GROUP BY DATE(created_time) ");
			buffer.append(" UNION ALL SELECT `created_time`,`fillingStationId`,GROUP_CONCAT( DISTINCT `fillingstationname`) AS fillingstationname,SUM(`gasInKgs`) as gasInKgs,`closedgas`,DATE_FORMAT(created_time,'%d-%b-%Y') AS expirtdate1,'Used Gas' AS UsedGas FROM usedgas WHERE 1=1");
			if(StringUtils.isNotBlank(month)){
				 
				buffer.append("  AND  MONTH(created_time) ='"+month+"' AND YEAR(created_time) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(created_time) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
				buffer.append(" and fillingStationId='"+usedGasBean.getFillingStationId()+"'  ");
			}
			buffer.append(" GROUP BY DATE(created_time) ");
			buffer.append(" ) foo where 1=1 ");
			if(StringUtils.isNotBlank(usedGasBean.getGasType())){
				buffer.append(" and  foo.AddedGas LIKE '%"+usedGasBean.getGasType()+"%' ");
			}
			buffer.append(" GROUP BY  DATE(created_time),foo.AddedGas ORDER BY created_time ");
			String sql = buffer.toString();
			System.out.println(sql);
			List<UsedGasBean> retlist = jdbcTemplate.query(sql, new Object[] {  },
					ParameterizedBeanPropertyRowMapper.newInstance(UsedGasBean.class));
			
			if (retlist.size() > 0)
				return retlist;
			return null;
			    
			}*/
	 public List<Map<String,Object>> getSearchgassumaryreport(UsedGasBean usedGasBean,String month,String year){  
			jdbcTemplate = custom.getJdbcTemplate();
			 
			StringBuffer buffer = new StringBuffer();
			buffer.append("  SELECT foo.gasType,DATE_FORMAT(foo.cdate,'%d-%M-%Y') AS cdate,SUM(foo.sum_of_gas) AS totalgas,foo.stationname,foo.id FROM ( " 
    
				   +" SELECT 'Used Gas' AS gasType, 'public' AS customer, DATE(ct.created_time) AS cdate ,(COUNT(*))*(CAST(i.name AS UNSIGNED)) AS sum_of_gas ,GROUP_CONCAT(DISTINCT f.`stationname`) AS stationname,GROUP_CONCAT(DISTINCT f.id) AS id "
				    +" FROM `cylindertransaction` ct,`cylindermaster` cm ,items i ,`fillingstationmaster` f WHERE ct.`cylinderStatus`='3' "
				   +" AND cm.id=ct.`cylindetId` AND i.id=cm.size AND f.id=ct.`fillingStation` AND i.`itemType`='Cylinder'   ");
					if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
						buffer.append(" and f.id ="+usedGasBean.getFillingStationId() );
					}
					if(StringUtils.isNotBlank(usedGasBean.getItems())){
						buffer.append(" and i.id ="+usedGasBean.getItems() );
					}
				   buffer.append(" GROUP BY DATE(ct.created_time),i.name ");
				   buffer.append("  UNION ALL " 
				    
					+" SELECT  'Used Gas' AS gasType,'private' AS customer ,STR_TO_DATE(pc.`cylinderFilledDate`,'%d-%M-%Y') AS cdate,(SUM(pc.`quantity`))*(CAST(i.name AS UNSIGNED)) AS sum_of_gas,GROUP_CONCAT(DISTINCT f.`stationname`) AS stationname,GROUP_CONCAT(DISTINCT f.id) AS id " 
					+" FROM `privatecylinderfilledprice` pc,items i, fillingstationmaster f WHERE pc.`items` =i.id  "
				   +" AND f.id=pc.`fillingstationId` AND i.name AND i.`itemType`='Cylinder'   ");
				   if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
						buffer.append(" and f.id ="+usedGasBean.getFillingStationId() );
					}
				   if(StringUtils.isNotBlank(usedGasBean.getItems())){
						buffer.append(" and i.id ="+usedGasBean.getItems() );
					}
				   buffer.append("  GROUP BY STR_TO_DATE(pc.`cylinderFilledDate`,'%d-%M-%Y'),i.name ");
				   buffer.append("  UNION ALL  "
				      
				    +" SELECT 'Added gas' AS gasType ,'XXX' AS customer,DATE(a.created_time) AS cdate,SUM(a.`gasInKgs`) AS sum_of_gas,GROUP_CONCAT(DISTINCT f.`stationname`) AS stationname,GROUP_CONCAT(DISTINCT f.id) AS id " 
				    +" FROM addgas a,fillingstationmaster f WHERE 1=1 AND f.id=a.`fillingStationId`  ");
				   if(StringUtils.isNotBlank(usedGasBean.getFillingStationId())){
						buffer.append(" and f.id ="+usedGasBean.getFillingStationId() );
					}
				    buffer.append("  GROUP BY DATE(a.created_time) "
				+" ) foo WHERE 1=1   ");
			if(StringUtils.isNotBlank(month)){
				buffer.append("  AND  MONTH(foo.cdate) ='"+month+"' AND YEAR(foo.cdate) ='"+year+"' " );
			}else if(StringUtils.isNotBlank(usedGasBean.getFromDate()) &&  StringUtils.isNotBlank(usedGasBean.getToDate())){
				buffer.append(" and  date(foo.cdate) BETWEEN '"+usedGasBean.getFromDate()+"' AND '"+usedGasBean.getToDate()+"' " );
			}
			
			
			if(StringUtils.isNotBlank(usedGasBean.getGasType())){
				buffer.append(" and foo.gasType='"+usedGasBean.getGasType()+"'  ");
			}
			if(StringUtils.isNotBlank(usedGasBean.getCustomerType())){
				buffer.append(" and foo.customer='"+usedGasBean.getCustomerType()+"'  ");
			}
			
			buffer.append(" GROUP BY foo.cdate ");
			String sql = buffer.toString();
			System.out.println(sql);
			List<Map<String,Object>> retlist = jdbcTemplate.queryForList(sql);
			
				return retlist;
			    
			} 
}

