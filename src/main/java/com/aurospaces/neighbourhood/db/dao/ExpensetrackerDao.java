
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
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
			}
			if(StringUtils.isNotBlank(expensetracker.getFromDate()) &&  StringUtils.isNotBlank(expensetracker.getToDate())){
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
}

