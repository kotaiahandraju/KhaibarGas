
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CustomermasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCustomermasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "customermasterDao")
public class CustomermasterDao extends BaseCustomermasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	ObjectMapper objectMapper=null;
	String sJson=null;
	public String getAllCustomer() {
		List<CustomermasterBean> listCustomermasterBean = null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT c.*,CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status in ('1') THEN 'Active'  ELSE '-----' END as custStatus  from customermaster c order by c.id desc";
			System.out.println("sql:::"+sql);
			listCustomermasterBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CustomermasterBean>(CustomermasterBean.class));
			if(listCustomermasterBean !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(listCustomermasterBean);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}
	 @SuppressWarnings("deprecation")
		public  int  getCustomerCount(){  
			 jdbcTemplate = custom.getJdbcTemplate();
			 
			 String sql="SELECT  count(*)    FROM customermaster";
			   
			   return jdbcTemplate.queryForInt(sql);
		}
	 public boolean updateCustomerIds() {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update customermaster  set  customerid= concat(SUBSTR(customertype,1,3),'1' ,LPAD( id, 4, '0'))";
			int i = jdbcTemplate.update(sql, new Object[]{});
			 if(i>0)
				 result = true;
				return result;
		}
	 public List<Map<String, Object>> getCustomers(CustomermasterBean customermasterBean) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select cm.*,GROUP_CONCAT(cc.cylinderId) as cylinderId1,GROUP_CONCAT(i.name) as name,cym.ownercompany from customermaster  cm left join  customercylinders cc on cc.customerId = cm.id left join cylindermaster cym on  cc.cylinderId = cym.id left join items i on i.id=cym.size where cm.customertype= ? group by cm.id ";
//			String sql = "select cm.*,GROUP_CONCAT(cc.cylinderId) as cylinderId1,GROUP_CONCAT(i.name) as name,cym.ownercompany from customermaster cm  ,customercylinders cc ,  cylindermaster cym ,  items i   where cc.customerId = cm.id and cym.id=cc.cylinderId and cym.size=i.id  and cm.customertype=?  group by cm.id";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] { customermasterBean.getCustomertype() });
			if (retlist.size() > 0)
				return retlist;
			return null;
		}
}

