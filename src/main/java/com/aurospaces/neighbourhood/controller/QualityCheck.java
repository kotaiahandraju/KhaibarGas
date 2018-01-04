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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CustomermasterBean;
import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.CylindertransactionBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.KhaibarUsersBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindertransactionDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.aurospaces.neighbourhood.db.dao.StoresmasterDao;

@Controller
@RequestMapping("admin")
public class QualityCheck {
	@Autowired CylindermasterDao cylindermasterDao;
	@Autowired FillingstationmasterDao fillingstationmasterDao;
	@Autowired CylindertransactionDao cylindertransactionDao;
	@Autowired StoresmasterDao storesmasterDao;
	@Autowired ItemsDao itemsDao;
	@Autowired LpomasterDao lpomasterDao;
	private Logger logger = Logger.getLogger(QualityCheck.class);
	
	@RequestMapping(value = "/qualityCheckHome")
	public String qualityCheckHome( @ModelAttribute("qualityCheckForm") FillingstationmasterBean fillingstationmasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "qualityCheckHome";
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
	
	
	@RequestMapping("returnCylinderQualityCheck")
	public @ResponseBody String returnCylinderQualityCheck(@RequestParam("cylinderType") String cylinderType,@RequestParam("quantity") String limit){
		ObjectMapper objectMapper=null;
		String sJson=null;
		List<CylindermasterBean> retlist=null;
		try{
			if(cylinderType !="" && limit !=""){
				
				int iLimit=Integer.parseInt(limit);
				System.out.println("----data------"+"---name"+cylinderType+"---quantity---"+limit);
				retlist=cylindermasterDao.searchReturnQualityChecking(cylinderType, iLimit,"7");
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(retlist);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sJson;
		
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
	
	@RequestMapping(value="/updateCylinderStatus8")
	public @ResponseBody String updateCylinderStatus8(CylindertransactionBean cylindertransactionBean1, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
    CylindermasterBean cylindermasterBean =null;
    int intcount = 0;
    JSONObject jsonObject = new JSONObject();
		try {
			System.out.println("----------store----: "+cylindertransactionBean1.getStorename());
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			System.out.println("storename---"+cylenderId);
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindermasterBean= new CylindermasterBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setStorename(cylindertransactionBean1.getStorename());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindermasterBean.setId(Integer.parseInt(cylenderId1[i]));
			cylindermasterBean.setCylinderstatus(cylindertransactionBean1.getCylinderStatus());
			cylindermasterBean.setStore(cylindertransactionBean1.getStorename());
			cylindertransactionDao.save(cylindertransactionBean);
			boolean count = cylindermasterDao.updateCylinderStatus(cylindermasterBean);
			if(count){
				intcount++;
			}
			System.out.println("count---"+intcount);
			}
			jsonObject.put("msg", intcount+" Records Updated ");
		} catch (Exception e) {
			objJson.put("msg", e);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TransactionController class in updateCylinderStatus method");
		}


		return String.valueOf(jsonObject);
	}
	
	/*@RequestMapping(value="/updateCylinderStatus")
	public @ResponseBody String tariffMasterHome(CylindertransactionBean fillingstationmasterBean, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
    CylindermasterBean cylindermasterBean = null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String fillingStation =fillingstationmasterBean.getFillingStation();
			String cylenderId =fillingstationmasterBean.getCylindetId();
			String cylinderStatus =fillingstationmasterBean.getCylinderStatus();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindermasterBean = new CylindermasterBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylinderStatus);
			cylindertransactionBean.setFillingStation(fillingStation);
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindermasterBean.setId(Integer.parseInt(cylenderId1[i]));
			cylindermasterBean.setCylinderstatus(cylinderStatus);
			cylindermasterBean.setFillingstationId(fillingStation);
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylindermasterBean);
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
    CylindermasterBean cylindermasterBean1 = null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
				
				CylindermasterBean cylindermasterBean = cylindermasterDao.getByCylinderName(Integer.parseInt(cylenderId1[i]));
				int cylinderCapacity = 0;
				if(cylindermasterBean != null){
					if(StringUtils.isNotBlank(cylindermasterBean.getName())){
						String numberOnly= cylindermasterBean.getName().replaceAll("[^0-9]", "");
						cylinderCapacity= Integer.parseInt(numberOnly);
						fillingstationmasterDao.updateUsedGas(Integer.parseInt(cylindertransactionBean1.getFillingStation()), cylinderCapacity);
						fillingstationmasterDao.updateClosingGas();
					}
				}
				cylindermasterBean1 = new CylindermasterBean();
			cylindertransactionBean = new CylindertransactionBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setFillingStation(cylindertransactionBean1.getFillingStation());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindermasterBean1.setId(Integer.parseInt(cylenderId1[i]));
			cylindermasterBean1.setCylinderstatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylindermasterBean1);
			
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
    CylindermasterBean cylindermasterBean =null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindermasterBean= new CylindermasterBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setFillingStation(cylindertransactionBean1.getFillingStation());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindermasterBean.setId(Integer.parseInt(cylenderId1[i]));
			cylindermasterBean.setCylinderstatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylindermasterBean);
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
    CylindermasterBean cylindermasterBean = null;
		try {
			KhaibarUsersBean users = (KhaibarUsersBean)session.getAttribute("cacheUserBean");
			String cylenderId =cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindermasterBean= new CylindermasterBean();
			cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
			cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
			cylindertransactionBean.setTruckId(cylindertransactionBean1.getTruckId());
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindermasterBean.setId(Integer.parseInt(cylenderId1[i]));
			cylindermasterBean.setCylinderstatus(cylindertransactionBean1.getCylinderStatus());
			cylindermasterBean.setTruckId(cylindertransactionBean1.getTruckId());
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylindermasterBean);
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
	@RequestMapping(value = "/cylinderDeliver")
	public String lpoHome(@ModelAttribute("lpoForm")CylindertransactionBean cylindertransactionBean,HttpServletRequest request,
			HttpSession session) {
		String sJson = null;
		List<LpomasterBean> lpoList=null;
		try {
			
			sJson=lpomasterDao.getAllCustomer();
			if(sJson !=null){
				
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderDeliver";
	}
	@RequestMapping(value = "/getTruckCylinders")
	public @ResponseBody String deletetruckMaster( CylindertransactionBean objCylindertransactionBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<Map<String, Object>> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			if(StringUtils.isNotBlank(objCylindertransactionBean.getTruckId()))
			{
			listOrderBeans = cylindermasterDao.getInTruckCylinders(objCylindertransactionBean.getTruckId());
			}
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption"+e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
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
	
	@ModelAttribute("items")
	public Map<Integer, String> populateitems() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,name from items where status='1' and itemType in('Accessories','Cylinder') ";
			List<CylinderTypesBean> list = lpomasterDao.populate(sSql);
			for (CylinderTypesBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}*/
}
