
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
	public String getAllCustomer(String status) {
		List<CustomermasterBean> listCustomermasterBean = null;
		try {
			jdbcTemplate = custom.getJdbcTemplate();
			
			String sql = "SELECT c.*,IFNULL(s.`securityDeposit`,'--') AS securityDeposit,s.`amount` as securityAmount,CASE WHEN c.status IN ('0') THEN 'Deactive' WHEN c.status IN ('1') THEN 'Active'  ELSE '-----' END AS custStatus FROM customermaster c LEFT JOIN `securedeposit` s ON c.id=s.`customerId`  where c.status='"+status+"' GROUP BY c.id order by c.id desc";
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
			StringBuffer buffer = new StringBuffer();
			buffer.append("select cm.*,cym.ownercompany,cc.cylinderId as cylinderId1,i.name  as name,cc.cylinderreturn,cc.invoiceId from customermaster  cm left join  customercylinders cc on cc.customerId = cm.id left join cylindermaster cym on  cc.cylinderId = cym.id left join items i on i.id=cym.size where 1=1  ");
					if(StringUtils.isNotBlank(customermasterBean.getCustomertype())){
						buffer.append(" and cm.customertype= '"+customermasterBean.getCustomertype()+"' ");
						buffer.append( "  group by cm.id ");
					}
					if(StringUtils.isNotBlank(customermasterBean.getCustomerid())){
						buffer.append(" and cm.id= "+customermasterBean.getCustomerid()+" ");
					}
					String sql = buffer.toString();
//					buffer.append( "  group by cm.id ");
//			String sql = "select cm.*,GROUP_CONCAT(cc.cylinderId) as cylinderId1,GROUP_CONCAT(i.name) as name,cym.ownercompany from customermaster cm  ,customercylinders cc ,  cylindermaster cym ,  items i   where cc.customerId = cm.id and cym.id=cc.cylinderId and cym.size=i.id  and cm.customertype=?  group by cm.id";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] {  });
			if (retlist.size() > 0)
				return retlist;
			return null;
		}
	 public boolean updateCylinderPrice(CustomermasterBean customermasterBean) {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			StringBuffer buffer =new StringBuffer();
			buffer.append("update customermaster set  ");
			if(StringUtils.isNotBlank(customermasterBean.getNetAmount())){
				buffer.append("netAmount="+customermasterBean.getNetAmount() );
			}
			if(StringUtils.isNotBlank(customermasterBean.getCylinderId())){
				buffer.append(",cylinderId= '"+customermasterBean.getCylinderId().replace("'", " ")+"' ");
			}
			
			if(StringUtils.isNotBlank(customermasterBean.getDueAmount())){
				buffer.append(", dueAmount="+customermasterBean.getDueAmount());
			}
			if(StringUtils.isNotBlank(customermasterBean.getPayedAmount())){
				buffer.append(" ,paidAmount="+customermasterBean.getPayedAmount());
			}
			if(StringUtils.isNotBlank(customermasterBean.getPreviousDueAmount())){
				buffer.append(" ,previousDueAmount="+customermasterBean.getPreviousDueAmount());
			}
			if(StringUtils.isNotBlank(customermasterBean.getInvoiceId())){
				buffer.append(" ,invoiceId='"+customermasterBean.getInvoiceId()+"' ");
			}
			buffer.append(" where id="+customermasterBean.getId());
			String sql = buffer.toString();
			int i = jdbcTemplate.update(sql, new Object[]{});
			 if(i>0)
				 result = true;
				return result;
		}
	 public boolean updateDueAmount(String dueAmount,String customerId,String invoiceId) {
			boolean result=false;
			jdbcTemplate = custom.getJdbcTemplate();
			StringBuffer buffer =new StringBuffer();
			buffer.append("update customermaster set  ");
			if(StringUtils.isNotBlank(dueAmount)){
				buffer.append(" dueAmount="+dueAmount);
			}
			if(StringUtils.isNotBlank(invoiceId)){
				buffer.append(", invoiceId='"+invoiceId+"' " );
			}
			buffer.append(" where id="+customerId);
			String sql = buffer.toString();
			int i = jdbcTemplate.update(sql, new Object[]{});
			 if(i>0)
				 result = true;
				return result;
		}
	 public List<Map<String, Object>> getCustomersPrintData(String invoiceId) {
			jdbcTemplate = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT p.invoiceId, p.`quantity`, p.`price`,`grossamount`,`dueamount`,p.`invoiceid`,p.`paidamount` ,GROUP_CONCAT(i.name) AS itemName FROM `printdata` p left join `items` i on p.`items`=i.`id`  WHERE   p.invoiceId=?  GROUP BY p.`invoiceid` ORDER BY p.`created_time` ASC ");
					
					String sql = buffer.toString();
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] { invoiceId });
			if (retlist.size() > 0)
				return retlist;
			return null;
		}
	 public List<Map<String, Object>> getCustomersDetails(CustomermasterBean customermasterBean) {
			jdbcTemplate = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			buffer.append("select * from  customermaster cm where   1=1 ");
					if(StringUtils.isNotBlank(customermasterBean.getCustomerid())){
						buffer.append(" and cm.id= "+customermasterBean.getCustomerid()+" ");
					}
					String sql = buffer.toString();
//					buffer.append( "  group by cm.id ");
//			String sql = "select cm.*,GROUP_CONCAT(cc.cylinderId) as cylinderId1,GROUP_CONCAT(i.name) as name,cym.ownercompany from customermaster cm  ,customercylinders cc ,  cylindermaster cym ,  items i   where cc.customerId = cm.id and cym.id=cc.cylinderId and cym.size=i.id  and cm.customertype=?  group by cm.id";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] {  });
			if (retlist.size() > 0)
				return retlist;
			return null;
		}
	 public List<Map<String, Object>> getDueamountInvoiceData(String invoiceId) {
			jdbcTemplate = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT GROUP_CONCAT(i.name) AS itemName, p.grossamount,p.paidAmount,p.invoiceId,cm.customertype,cm.`customername`,cm.`customeraddress`,cm.`mobile`,cm.`landline`,p.`dueAmount` FROM `printdata` p LEFT JOIN `customermaster` cm ON p.`customerid`=cm.id  LEFT JOIN  items i ON p.`items` =i.id   ");
					if(StringUtils.isNotBlank(invoiceId)){
						buffer.append(" where p.`invoiceid`='"+invoiceId+"' ");
					}
					buffer.append(" GROUP BY p.`invoiceid` ");
					String sql = buffer.toString();
					System.out.println(sql);
//					buffer.append( "  group by cm.id ");
//			String sql = "select cm.*,GROUP_CONCAT(cc.cylinderId) as cylinderId1,GROUP_CONCAT(i.name) as name,cym.ownercompany from customermaster cm  ,customercylinders cc ,  cylindermaster cym ,  items i   where cc.customerId = cm.id and cym.id=cc.cylinderId and cym.size=i.id  and cm.customertype=?  group by cm.id";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] {  });
			if (retlist.size() > 0)
				return retlist;
			return null;
		}
	 public List<Map<String, Object>> getcustomeritems(CustomermasterBean objCustomermasterBean) {
			jdbcTemplate = custom.getJdbcTemplate();
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT i.id,i.name FROM items i,`customertariffmaster` ctf WHERE i.id=ctf.`itemId` AND ctf.customerId=?  ");
					
					String sql = buffer.toString();
					System.out.println(sql);
//					buffer.append( "  group by cm.id ");
//			String sql = "select cm.*,GROUP_CONCAT(cc.cylinderId) as cylinderId1,GROUP_CONCAT(i.name) as name,cym.ownercompany from customermaster cm  ,customercylinders cc ,  cylindermaster cym ,  items i   where cc.customerId = cm.id and cym.id=cc.cylinderId and cym.size=i.id  and cm.customertype=?  group by cm.id";
			List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql, new Object[] {objCustomermasterBean.getCustomerid()  });
			if (retlist.size() > 0)
				return retlist;
			return null;
		}
	 
}

