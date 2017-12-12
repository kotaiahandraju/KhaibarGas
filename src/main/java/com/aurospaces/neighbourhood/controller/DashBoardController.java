package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value="admin")
public class DashBoardController {
	@Autowired CylindermasterDao cylindermasterDao;
	@Autowired CustomermasterDao objCustomerDao;
	private Logger logger = Logger.getLogger(DashBoardController.class);
	@RequestMapping(value = "/dashboard")
	public String fillingStationHome( ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		try {
			

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
			/* if(cylindersCount != null){
				 for(CylindermasterBean list:cylindersCount){
					if( list.getCylinderstatus().equals("Empty")){
						session.setAttribute("EmptyCount", list.getCount());
					}
					
					if( list.getCylinderstatus().equals("FillingStation")){
						session.setAttribute("FillingStation", list.getCount());
					}
					if( list.getCylinderstatus().equals("QualityCheck")){
						session.setAttribute("QualityCheck", list.getCount());
					}
					if( list.getCylinderstatus().equals("Truck")){
						session.setAttribute("Truck", list.getCount());
					}
					if( list.getCylinderstatus().equals("DeliveryBoy")){
						session.setAttribute("DeliveryBoy", list.getCount());
					}
					if( list.getCylendersstatus() =="Empty"){
						session.setAttribute("EmptyCount", list.getCount());
					}else{
						session.setAttribute("EmptyCount", "---");
					}
					 
				 }
			 }*/
			 totalCylinderCount = cylindermasterDao.getTotalcylindersCount();
			 customerCount = objCustomerDao.getCustomerCount();
			session.setAttribute("totalCylinderCount", totalCylinderCount);
			objJson.put("totalCylinderCount", totalCylinderCount);
			objJson.put("customerCount", customerCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return String.valueOf(objJson);
	}
	
	
}
