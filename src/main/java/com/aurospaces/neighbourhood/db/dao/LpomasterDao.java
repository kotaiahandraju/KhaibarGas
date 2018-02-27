
package com.aurospaces.neighbourhood.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.LpoitemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseLpomasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Repository(value = "lpomasterDao")
public class LpomasterDao extends BaseLpomasterDao
{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public String getAllCustomer(String status) {
		List<LpomasterBean> listLpomasterBean = null;
		ObjectMapper objectMapper=null;
		String sJson=null;
		try {
			
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT l.*, DATE_FORMAT(l.expiryDate, '%d-%b-%Y') as expiryDate1,CASE WHEN l.status IN ('0') THEN 'Deactive' WHEN l.status in ('1') THEN 'Active'  ELSE '-----' END as lpoStatus  from lpomaster l where status='"+status+"' order by id desc";
			System.out.println("sql:::"+sql);
			listLpomasterBean = jdbcTemplate.query(sql, new BeanPropertyRowMapper<LpomasterBean>(LpomasterBean.class));
			if(listLpomasterBean !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(listLpomasterBean);
			}
		} catch (Exception e) {
			//logger.error("Exception in getAllDamage in PopulateDaoImpl"+ e);
			e.printStackTrace();
		}
		return sJson;
	}
	 public LpomasterBean getByLpoNo(String sMobileNo) {
		 List<LpomasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from lpomaster where lponumber = ? ";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{sMobileNo},
			ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
	 public List<LpomasterBean> getLPOdetails(LpomasterBean lpomasterBean) {
		 List<LpomasterBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "select l.suppliername,li.expirydate as expirydate from lpomaster l ,lpoitems li where l.lponumber =li.lponumber and li.itemid=? and li.lponumber =?";
			 retlist = jdbcTemplate.query(sql,
			new Object[]{lpomasterBean.getItem(),lpomasterBean.getLponumber()},
			ParameterizedBeanPropertyRowMapper.newInstance(LpomasterBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	 public List<LpoitemsBean> viewLPOdetails(LpomasterBean lpomasterBean) {
		 List<LpoitemsBean> retlist =null;
		 jdbcTemplate = custom.getJdbcTemplate();
//			String sql = "select li.lponumber,li.quantity,li.price,li.totalprice,li.discount,li.grandtotal,i.name as itemid from lpoitems li ,items i where i.id=li.itemid and li.lponumber=? ";
		 String sql = "select li.*,i.name,lm.amount,lm.`suppliername`, lm.`supplieraddress`,DATE_FORMAT(Date(lm.`created_time`), '%d-%b-%Y') as lpoDate from lpoitems li,lpomaster lm,items i where i.id=li.itemid and li.lponumber=lm.lponumber and li.lponumber = ?";
			 retlist = jdbcTemplate.query(sql,	new Object[]{lpomasterBean.getLponumber()},ParameterizedBeanPropertyRowMapper.newInstance(LpoitemsBean.class));
			if(retlist.size() > 0)
				return retlist;
			return retlist;
		}
	 public List<CylinderTypesBean> populate(String sql ){
		 jdbcTemplate = custom.getJdbcTemplate();
				List<CylinderTypesBean> retlist = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CylinderTypesBean.class));
					return retlist;
		 }
	 public void updateLPONumber() {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "update lpomaster  set  lponumber= concat('LPO','1' ,LPAD( id, 6, '0'))";
			jdbcTemplate.update(sql, new Object[]{});
		}
	 @SuppressWarnings("deprecation")
	public Map getCountFromLPOItemsAndCylindermaster(LpomasterBean lpomasterBean) {
		int retlist = 0;
		boolean count = false;
		Map  listMap=new HashMap();
		jdbcTemplate = custom.getJdbcTemplate();
		String sql1 = "select ifnull((SELECT count(*) from cylindermaster where lponumber = ? AND size=? group by lponumber,size),0) AS count1 ";
		String sql = "select sum(quantity) from lpoitems where lponumber = ? AND itemId=? group by lponumber,itemId";
		if (StringUtils.isNotBlank(lpomasterBean.getLponumber()) && StringUtils.isNotBlank(lpomasterBean.getItem())) {

			System.out.println("---lpomasterBean.getLponumber(),----" + lpomasterBean.getLponumber()
					+ "-----lpomasterBean.getItem()---" + lpomasterBean.getItem());
			int resultList = jdbcTemplate.queryForInt(sql1,
					new Object[] { lpomasterBean.getLponumber(), lpomasterBean.getItem() });
			retlist = jdbcTemplate.queryForInt(sql,
					new Object[] { lpomasterBean.getLponumber(), lpomasterBean.getItem() });

			int result = retlist - resultList;
			System.out.println("---result----" + result);
			if (result > 0) {
				count = true;
				
			}
			listMap.put("count", count);
			listMap.put("result", result);

		}
		return listMap;
	}
}

