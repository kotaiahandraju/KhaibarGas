package com.aurospaces.neighbourhood.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.AddGasBean;
import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.NewClassBean;
import com.aurospaces.neighbourhood.bean.ddd;
import com.aurospaces.neighbourhood.db.dao.AddGasDao;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value="admin")
public class DashBoardController {
	@Autowired CylindermasterDao cylindermasterDao;
	@Autowired CustomermasterDao objCustomerDao;
	@Autowired FillingstationmasterDao fillingstationmasterDao;
	@Autowired AddGasDao addGasDao;
	private Logger logger = Logger.getLogger(DashBoardController.class);
	@RequestMapping(value = "/dashboard")
	public String fillingStationHome( ModelMap model, HttpServletRequest request,
			HttpSession session) {
		List<ddd> listOrderBeans  = null;
		ObjectMapper objectMapper = null;
		JSONObject jsonObj = new JSONObject();
		String sJson=null;
		boolean delete = false;
		List<Map<String, Object>> cylinderstatuscount = null;
		List<Map<String, Object>> gassummary = null;
		List<Map<String, Object>> expensessummary = null;
		List<Map<String, Object>> totalusagegasreport = null;
		List<Map<String, Object>> totalCylindersCount = null;
		List<Map<String, Object>> fillingStationcount = null;
		
		try {
			
			/*listOrderBeans = 
					//objectMapper = new ObjectMapper();
				if (listOrderBeans != null && listOrderBeans.size() > 0) {
					
					objectMapper = new ObjectMapper();
					sJson = objectMapper.writeValueAsString(listOrderBeans);
					request.setAttribute("allOrders1", sJson);
					jsonObj.put("dps", listOrderBeans);
					// System.out.println(sJson);
				} else {
					objectMapper = new ObjectMapper();
					sJson = objectMapper.writeValueAsString(listOrderBeans);
					request.setAttribute("allOrders1", "''");
					jsonObj.put("dps", "''");
				}*/
			///////////////////////
			List<Map<String, Object>> branch_prod_list = null;
				Map<String,Map<String,Object>> prod_map = new HashMap<String,Map<String,Object>>();
				Map<String,String> branches_map = new HashMap<String,String>();
						branch_prod_list = addGasDao.getdata();
					
					for(Map<String, Object> row:branch_prod_list){
						if(!branches_map.containsKey((String)row.get("label"))){
							branches_map.put((String)row.get("label"), (String)row.get("label"));
						}
						String product_id = (String)row.get("name");
						if(prod_map.containsKey(product_id)){
							Map<String,Object> branch = (Map<String,Object>)prod_map.get(product_id);
							branch.put((String)row.get("label"), row.get("counts"));
							//total_delivered += ((Double)row.get("delivered")).longValue();
							//total_nullified += ((Double)row.get("nullified")).longValue();
						}else{
							Map<String,Object> branch = new HashMap<String,Object>();
							branch.put((String)row.get("label"), row.get("counts"));
							prod_map.put(product_id, branch);
							//total_delivered += ((Double)row.get("delivered")).longValue();
							//total_nullified += ((Double)row.get("nullified")).longValue();
						}
						
					}
					
					/*List<Map<String, Object>> ordered_list = null ;
						ordered_list = listDao.getProductsOrderedQtyBranchWise();
					for(Map<String, Object> row:ordered_list){
						if(!branches_map.containsKey((String)row.get("branch_name"))){
							branches_map.put((String)row.get("branch_name"), (String)row.get("branch_name"));
						}
						String prod_name = (String)row.get("category")+"##"+(String)row.get("sub_category")+"##"+(String)row.get("item_code");
						if(prod_map.containsKey(prod_name)){
							Map<String,Object> branch_map = (Map<String,Object>)prod_map.get(prod_name);
							if(branch_map.containsKey(row.get("branch_name"))){
								String values = (String)branch_map.get((String)row.get("branch_name"));
								branch_map.put((String)row.get("branch_name"), ((Double)row.get("ordered")).intValue()+","+values);
								//total_orders += ((Double)row.get("ordered")).longValue();
							}else{
								branch_map.put((String)row.get("branch_name"), ((Double)row.get("ordered")).intValue()+",0,0");
								prod_map.put(prod_name, branch_map);
								//total_orders += ((Double)row.get("ordered")).longValue();
							}
						}else{
							Map<String,Object> branch = new HashMap<String,Object>();
							branch.put((String)row.get("branch_name"), ((Double)row.get("ordered")).intValue()+",0,0");
							prod_map.put(prod_name, branch);
							//total_orders += ((Double)row.get("ordered")).longValue();
						}
					}*/
					Collection<Map<String,Object>> branch_maps = prod_map.values();
					Iterator<Map<String,Object>> iter = branch_maps.iterator();
					while(iter.hasNext()){
						Map<String, Object> branch_map = iter.next();
						Set br_keys = branches_map.keySet();
						Iterator iter2 = br_keys.iterator();
						while(iter2.hasNext()){
							String branch_name = (String)iter2.next();
							if(!branch_map.containsKey(branch_name)){
								branch_map.put(branch_name, "0");
							}
						}
						
					}
					
					// sort size values on month-year
					Iterator prod_keys = prod_map.keySet().iterator();
					while(prod_keys.hasNext()){
						String key = (String)prod_keys.next();
						Map<String,Object> value =  prod_map.get(key);
						Map<String, Object> sortedValue = new TreeMap<String, Object>(new Comparator<String>() {
							 
					        public int compare(String o1, String o2) {
					    
					          SimpleDateFormat s = new SimpleDateFormat("MMM- yyyy");
					          Date s1 = null;
					          Date s2 = null;
					          try {
					            s1 = s.parse(o1);
					            s2 = s.parse(o2);
					          } catch (ParseException e) {
					              e.printStackTrace();
					          }
					          return s1.compareTo(s2);
					        }
					     });
						sortedValue.putAll(value);
						prod_map.put(key, sortedValue);
					}
					
					// sort on month-year 
					Map<String, Object> sortedBranchesMap = new TreeMap<String, Object>(new Comparator<String>() {
						 
				        public int compare(String o1, String o2) {
				    
				          SimpleDateFormat s = new SimpleDateFormat("MMM- yyyy");
				          Date s1 = null;
				          Date s2 = null;
				          try {
				            s1 = s.parse(o1);
				            s2 = s.parse(o2);
				          } catch (ParseException e) {
				              e.printStackTrace();
				          }
				          return s1.compareTo(s2);
				        }
				     });
					sortedBranchesMap.putAll(branches_map);
					objectMapper = new ObjectMapper();
					sJson = objectMapper.writeValueAsString(sortedBranchesMap);
					request.setAttribute("branches_map", sJson);
					
					// sort on size
					Map<String, Object> sortedProdMap = new TreeMap<String, Object>(prod_map);
					objectMapper = new ObjectMapper();
					sJson = objectMapper.writeValueAsString(sortedProdMap);
					request.setAttribute("delivered_qty_list", sJson);
					
					/*objectMapper = new ObjectMapper();
					if (branch_prod_list != null && branch_prod_list.size() > 0) {
		
						objectMapper = new ObjectMapper();
						sJson = objectMapper.writeValueAsString(branch_prod_list);
						request.setAttribute("delivered_qty_list", sJson);
					} else {
						request.setAttribute("delivered_qty_list", "''");
					}*/
					
					cylinderstatuscount = 	cylindermasterDao.getCylindersCount();
					if(cylinderstatuscount != null){
						
						request.setAttribute("cylinderstatuscount", objectMapper.writeValueAsString(cylinderstatuscount));
					}else{
						request.setAttribute("cylinderstatuscount", "''");
					}
					
					gassummary = 	cylindermasterDao.getGassummary();
					if(gassummary != null){
						
						request.setAttribute("gassummary", objectMapper.writeValueAsString(gassummary));
					}else{
						request.setAttribute("gassummary", "''");
					}
					
					expensessummary = 	cylindermasterDao.getExpensessummary();
					if(expensessummary != null){
						
						request.setAttribute("expensessummary", objectMapper.writeValueAsString(expensessummary));
					}else{
						request.setAttribute("expensessummary", "''");
					}
					
					totalusagegasreport = 	cylindermasterDao.totalusagegasreport();
					if(totalusagegasreport != null){
						
						request.setAttribute("totalusagegasreport", objectMapper.writeValueAsString(totalusagegasreport));
					}else{
						request.setAttribute("totalusagegasreport", "''");
					}
					
					totalCylindersCount = 	cylindermasterDao.getTotalCylindersCount();
					if(totalCylindersCount != null){
						
						request.setAttribute("totalCylindersCount", objectMapper.writeValueAsString(totalCylindersCount));
					}else{
						request.setAttribute("totalCylindersCount", "''");
					}
					
					fillingStationcount = 	cylindermasterDao.getFillingStationcount();
					if(fillingStationcount != null){
						
						request.setAttribute("fillingStationcount", objectMapper.writeValueAsString(fillingStationcount));
					}else{
						request.setAttribute("fillingStationcount", "''");
					}

					
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "dashboardHome";
	}
	
	@RequestMapping(value = "/getCount")
	public @ResponseBody String getCount( ModelMap model, HttpServletRequest request,
			HttpSession session) {
		JSONObject objJson =new JSONObject();
		List<Map<String, Object>> cylindersCount =null;
		int totalCylinderCount = 0;
		int totalGas = 0;
		int customerCount = 0;
		try {
			
			 cylindersCount=cylindermasterDao.getCylindersCount();
			 for (int i = 0 ; i < cylindersCount.size() ; i++) {
		            Map<String, Object> myMap = cylindersCount.get(i);
//		            System.out.println("Data For Map" + i);
		            String status= String.valueOf( myMap.get("cylinderstatus"));
		            session.setAttribute(String.valueOf( myMap.get("cylinderstatus")),myMap.get("count"));
//		            System.out.println(myMap.get("count"));
//		            System.out.println( myMap.get("cylinderstatus"));
		            /*for (Entry<String, Object> entrySet : myMap.entrySet()) {
		                System.out.println("Key = " + entrySet.getKey() + " , Value = " + entrySet.getValue());
		            }*/
		        }
			 totalGas =  fillingstationmasterDao.totalGas();
			 totalCylinderCount = cylindermasterDao.getTotalcylindersCount();
			 customerCount = objCustomerDao.getCustomerCount();
			session.setAttribute("totalCylinderCount", totalCylinderCount);
			session.setAttribute("totalGas", totalGas);
			objJson.put("totalCylinderCount", totalCylinderCount);
			objJson.put("customerCount", customerCount);
			objJson.put("totalGas", totalGas);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return String.valueOf(objJson);
	}
	
	@RequestMapping(value = "/NewFile")
	public String fillingStationHome1( ModelMap model, HttpServletRequest request,
			HttpSession session) {
		List<ddd> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "NewFile";
	}
	@RequestMapping(value = "/NewFile1")
	public String fillingStationHome11( ModelMap model, HttpServletRequest request,	HttpSession session) {
		List<ddd> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try {
//			System.out.println("bean--------:"+bean.getName()+"----a--- "+bean.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "printcustomer";
	}
	@RequestMapping(value = "/getCylindersStatusCount")
	public  @ResponseBody String getCylindersStatusCount( ModelMap model, HttpServletRequest request,	HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj=	cylindermasterDao.getCylindersStatusCount();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return String.valueOf(jsonObj);
	}
	
	
}
