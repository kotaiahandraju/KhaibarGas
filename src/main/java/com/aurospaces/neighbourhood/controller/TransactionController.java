package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.CylindertransactionBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.KhaibarUsersBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindertransactionDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.aurospaces.neighbourhood.db.dao.StoresmasterDao;
import com.aurospaces.neighbourhood.db.dao.TrucksmasterDao;

@Controller
@RequestMapping("admin")
public class TransactionController {
	@Autowired CylindermasterDao cylindermasterDao;
	@Autowired FillingstationmasterDao fillingstationmasterDao;
	@Autowired CylindertransactionDao cylindertransactionDao;
	@Autowired StoresmasterDao storesmasterDao;
	@Autowired ItemsDao itemsDao;
	private Logger logger = Logger.getLogger(TransactionController.class);
	
	@RequestMapping(value = "/cylinderMovetofillingStation")
	public String cylinderMovetofillingStation( @ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus="1";
			listOrderBeans = cylindermasterDao.getEmptyCylinders(cylinderstatus);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "movetoFillingStation";
	}
	
	@RequestMapping(value="/updateCylinderStatus")
	public @ResponseBody String tariffMasterHome(CylindertransactionBean fillingstationmasterBean, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String fillingStation =fillingstationmasterBean.getFillingStation();
			String cylenderId =fillingstationmasterBean.getCylindetId();
			String cylinderStatus =fillingstationmasterBean.getCylinderStatus();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylinderStatus);
			cylindertransactionBean.setFillingStation(fillingStation);
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylenderId1[i], cylinderStatus,fillingStation);
			}
			
		} catch (Exception e) {
			objJson.put("msg", e);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TransactionController class in updateCylinderStatus method");
		}


		return "tariffMasterHome";
	}
	
	
	@RequestMapping("searchCylinderMoveToFilling")
	public @ResponseBody String searchCylinderMoveToFilling(@RequestParam("store") String sStore,@RequestParam("cylinderType") String cylinderType,@RequestParam("quantity") String limit){
		ObjectMapper objectMapper=null;
		String sJson=null;
		List<CylindermasterBean> retlist=null;
		try{
			if(sStore !="" && cylinderType !="" && limit !=""){
				
				int iLimit=Integer.parseInt(limit);
				System.out.println("----data------"+sStore+"---name"+cylinderType+"---quantity---"+limit);
				retlist=cylindermasterDao.searchCylinderMoveToFilling(sStore, cylinderType, iLimit,"1");
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(retlist);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sJson;
		
	}
	
	@RequestMapping(value = "/cylinderFilledStatus")
	public String cylinderFiledStatus( @ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus="2";
			listOrderBeans = cylindermasterDao.getEmptyCylinders(cylinderstatus);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderFiledStatus";
	}
	@RequestMapping("/searchQualityCheck1")
	public @ResponseBody String searchQualityCheck1(HttpServletRequest request){
		JSONObject objJson= new JSONObject();
		List<CylindermasterBean> retlist=null;
		String stationname = null;
		String quantity = null;
		String cylinderType= null; 
		int limit = 100;
		try{
			stationname = request.getParameter("stationname");
			quantity = request.getParameter("quantity");
			if(StringUtils.isNotBlank(quantity)){
				limit  = Integer.parseInt(quantity);
			}
			cylinderType = request.getParameter("cylinderType");
			if(stationname !="" && quantity !="" && cylinderType !=""){
				
				retlist=cylindermasterDao.searchQualityCheck(stationname, cylinderType, limit,"2");
				if(retlist != null){
					objJson.put("allOrders1", retlist);
				}else{
					objJson.put("allOrders1", "");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJson);
		
	}
	@RequestMapping(value="/updateCylinderStatus2")
	public @ResponseBody String updateCylinderStatus2(CylindertransactionBean cylindertransactionBean1, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setFillingStation(cylindertransactionBean1.getFillingStation());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylenderId1[i], cylindertransactionBean1.getCylinderStatus(),null);
			}
			
		} catch (Exception e) {
			objJson.put("msg", e);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TransactionController class in updateCylinderStatus method");
		}


		return "tariffMasterHome";
	}
	@RequestMapping(value = "/cylinderQualityCheck")
	public String cylinderQualityCheck( @ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus="3";
			listOrderBeans = cylindermasterDao.getEmptyCylinders(cylinderstatus);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderQualityCheck";
	}
	
	@RequestMapping("/searchQualityCheck")
	public @ResponseBody String searchQualityCheck(HttpServletRequest request){
		JSONObject objJson= new JSONObject();
		List<CylindermasterBean> retlist=null;
		String stationname = null;
		String quantity = null;
		String cylinderType= null; 
		int limit = 100;
		try{
			stationname = request.getParameter("stationname");
			quantity = request.getParameter("quantity");
			if(StringUtils.isNotBlank(quantity)){
				limit  = Integer.parseInt(quantity);
			}
			cylinderType = request.getParameter("cylinderType");
			if(stationname !="" && quantity !="" && cylinderType !=""){
				
				retlist=cylindermasterDao.searchQualityCheck(stationname, cylinderType, limit,"3");
				if(retlist != null){
					objJson.put("allOrders1", retlist);
				}else{
					objJson.put("allOrders1", "");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJson);
		
	}
	@RequestMapping(value="/updateCylinderStatus1")
	public @ResponseBody String updateCylinderStatus1(CylindertransactionBean cylindertransactionBean1, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setFillingStation(cylindertransactionBean1.getFillingStation());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylenderId1[i], cylindertransactionBean1.getCylinderStatus(),null);
			}
			
		} catch (Exception e) {
			objJson.put("msg", e);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TransactionController class in updateCylinderStatus method");
		}


		return "tariffMasterHome";
	}
	
	
	@RequestMapping(value = "/cylinderMovetoTruck")
	public String cylinderMovetoTruck( @ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus="4";
			listOrderBeans = cylindermasterDao.getEmptyCylinders(cylinderstatus);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderMovetoTruck";
	}
	@RequestMapping(value="/updateCylinderStatus3")
	public @ResponseBody String updateCylinderStatus3(CylindertransactionBean cylindertransactionBean1, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setTruckId(cylindertransactionBean1.getTruckId());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylenderId1[i], cylindertransactionBean1.getCylinderStatus(),cylindertransactionBean1.getFillingStation());
			}
			
		} catch (Exception e) {
			objJson.put("msg", e);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TransactionController class in updateCylinderStatus method");
		}


		return "tariffMasterHome";
	}
	@RequestMapping("/searchTruckStatus")
	public @ResponseBody String searchTruckStatus(HttpServletRequest request){
		JSONObject objJson= new JSONObject();
		List<CylindermasterBean> retlist=null;
		String stationname = null;
		String quantity = null;
		String cylinderType= null; 
		int limit = 100;
		try{
			stationname = request.getParameter("stationname");
			quantity = request.getParameter("quantity");
			if(StringUtils.isNotBlank(quantity)){
				limit  = Integer.parseInt(quantity);
			}
			cylinderType = request.getParameter("cylinderType");
			if(stationname !="" && quantity !="" && cylinderType !=""){
				
				retlist=cylindermasterDao.searchQualityCheck(stationname, cylinderType, limit,"4");
				if(retlist != null){
					objJson.put("allOrders1", retlist);
				}else{
					objJson.put("allOrders1", "");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJson);
		
	}
	@ModelAttribute("fillingstation")
	public Map<Integer, String> populateCity() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,stationname from fillingstationmaster";
			List<FillingstationmasterBean> list = fillingstationmasterDao.populate(sSql);
			for (FillingstationmasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getStationname());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("stores")
	public Map<Integer, String> populatestores() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id ,storename from storesmaster where status='1'";
			List<StoresmasterBean> list = storesmasterDao.populate(sSql);
			for (StoresmasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getStorename());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("cylinderTypes")
	public Map<Integer, String> populateUsers() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			List<CylinderTypesBean> list= cylindermasterDao.getCylinderstypes();
			for(CylinderTypesBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("trucks")
	public Map<Integer, String> populateTrucks() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			List<ItemsBean> list= itemsDao.getItems();
			for(ItemsBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	
	
}
