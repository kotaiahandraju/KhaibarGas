package com.aurospaces.neighbourhood.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.db.dao.CompanymasterDao;
import com.aurospaces.neighbourhood.db.dao.CustomercylindersDao;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindertransactionDao;
import com.aurospaces.neighbourhood.db.dao.ExpensetrackerDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.aurospaces.neighbourhood.db.dao.StoresmasterDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;

@Controller
@RequestMapping("admin")

public class ReportsController {
	@Autowired
	CylindermasterDao cylindermasterDao;
	@Autowired
	FillingstationmasterDao fillingstationmasterDao;
	@Autowired
	CylindertransactionDao cylindertransactionDao;
	@Autowired
	StoresmasterDao storesmasterDao;
	@Autowired
	ItemsDao itemsDao;
	@Autowired
	LpomasterDao lpomasterDao;
	@Autowired
	ServletContext objContext;
	@Autowired
	CustomercylindersDao customercylindersDao;
	@Autowired
	CustomermasterDao customermasterDao;
	@Autowired
	CompanymasterDao companymasterDao;
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired ExpensetrackerDao expensetrackerDao;
	private Logger logger = Logger.getLogger(ReportsController.class);

	@RequestMapping(value = "/reportsHome")
	public String reportsHome(@ModelAttribute("reportsForm") CylindermasterBean cylindermasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "reportsHome";
	}
	@RequestMapping("onChangeReports")
	public @ResponseBody String onChangeReports(CylindermasterBean cylinderBean ) {
		ObjectMapper objectMapper = null;
		JSONObject jsonObject = new JSONObject();
		List<CylindermasterBean> listOrderBeans = null;
		try {
			listOrderBeans = cylindermasterDao.getCylindersReport(cylinderBean);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				// System.out.println(sJson);
				jsonObject.put("allOrders1", listOrderBeans);
			} else {
				objectMapper = new ObjectMapper();
				jsonObject.put("allOrders1", listOrderBeans);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(jsonObject);

	}
	@RequestMapping(value = "/expensesReport")
	public String gasReport(@ModelAttribute("reportsForm") Expensetracker expensetracker, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "expenseReport";
	}
	
	@RequestMapping("searchExpensesReport")
	public @ResponseBody String searchExpensesReport(Expensetracker expensetracker ) {
		ObjectMapper objectMapper = null;
		JSONObject jsonObject = new JSONObject();
		List<Expensetracker> listOrderBeans = null;
		String monthnumber = null;
		String year = null;
		try {
			if(StringUtils.isNotBlank(expensetracker.getMonth())){
				String month1 = expensetracker.getMonth();
				 String [] arrOfStr = month1.split("/");
				  monthnumber = arrOfStr[0];
				  year = arrOfStr[1];
			}
			if(StringUtils.isNotBlank(expensetracker.getFromDate())){
				Date date=  KhaibarGasUtil.dateFormate(expensetracker.getFromDate());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				expensetracker.setFromDate(String.valueOf(sqlDate));
			}
			if(StringUtils.isNotBlank(expensetracker.getToDate())){
				Date date=  KhaibarGasUtil.dateFormate(expensetracker.getToDate());
						java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				expensetracker.setToDate(String.valueOf(sqlDate));
			}
			listOrderBeans = expensetrackerDao.getExpensesReport(expensetracker,monthnumber,year);
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				// System.out.println(sJson);
				jsonObject.put("allOrders1", listOrderBeans);
			} else {
				objectMapper = new ObjectMapper();
				jsonObject.put("allOrders1", listOrderBeans);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(jsonObject);

	}
	@ModelAttribute("companys")
	public Map<Integer, String> populatecompanys() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,companyname as name from companymaster where status='1' ";
			List<CylinderTypesBean> list = lpomasterDao.populate(sSql);
			for (CylinderTypesBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
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

	@ModelAttribute("Cylinderstatus")
	public Map<Integer, String> populateCylinderstatus() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id ,name from cylinderstatus where status='1'";
			List<ItemsBean> list = storesmasterDao.populateCylenderstatus(sSql);
			for (ItemsBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	
	@ModelAttribute("LPONumbers")
	public Map<String, String> populateLPONumbers() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "SELECT lponumber,`lponumber` FROM `lpoitems`   GROUP BY `lponumber` ";
			List<LpomasterBean> list = cylindermasterDao.populate(sSql);
			for (LpomasterBean bean : list) {
				statesMap.put(bean.getLponumber(), bean.getLponumber());
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
	
	
	
	
	
	/*@RequestMapping(value = "/updateCylinderStatus")
	public @ResponseBody String tariffMasterHome(CylindertransactionBean fillingstationmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		JSONObject objJson = new JSONObject();
		CylindertransactionBean cylindertransactionBean = null;
		CylindermasterBean cylindermasterBean = null;
		int intcount=0;
		JSONObject jsonObject=new JSONObject();
		try {
			KhaibarUsersBean users = (KhaibarUsersBean) session.getAttribute("cacheUserBean");
			String fillingStation = fillingstationmasterBean.getFillingStation();
			String cylenderId = fillingstationmasterBean.getCylindetId();
			String cylinderStatus = fillingstationmasterBean.getCylinderStatus();
			String[] cylenderId1 = cylenderId.split(",");
			for (int i = 0; i < cylenderId1.length; i++) {
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
				boolean count=cylindermasterDao.updateCylinderStatus(cylindermasterBean);
				 
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

	@RequestMapping("searchCylinderMoveToFilling")
	public @ResponseBody String searchCylinderMoveToFilling(@RequestParam("store") String sStore,
			@RequestParam("cylinderType") String cylinderType, @RequestParam("quantity") String limit) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> retlist = null;
		try {
			if (sStore != "" && cylinderType != "" && limit != "") {

				int iLimit = Integer.parseInt(limit);
				System.out.println("----data------" + sStore + "---name" + cylinderType + "---quantity---" + limit);
				retlist = cylindermasterDao.searchCylinderMoveToFilling(sStore, cylinderType, iLimit, "1");
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(retlist);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sJson;

	}
	@RequestMapping("onChangeStoreAndCylinderType")
	public @ResponseBody String onChangeStoreAndCylinderType(CylindermasterBean cylinderBean) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		String count1=null;
		List<CylindermasterBean> retlist = null;
		try {

				cylinderBean.setCylinderstatus("1");
				System.out.println("----cylindertransactionBean---" + cylinderBean.getStore()+"-------size-------"+cylinderBean.getSize()+"-------STATUS-------"+cylinderBean.getCylinderstatus());
				retlist = cylindermasterDao.onChangeStoreAndCylinderType(cylinderBean);
				for (CylindermasterBean cylindermasterBean : retlist) {
					 count1 =cylindermasterBean.getCount1();
				}
				//objectMapper = new ObjectMapper();
				//sJson = objectMapper.writeValueAsString(count1);
				sJson=count1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sJson;

	}
	@RequestMapping("onChangeCylinderFilledStatusData")
	public @ResponseBody String onChangeCylinderFilledStatusData(CylindermasterBean cylinderBean) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		String count1=null;
		List<CylindermasterBean> retlist = null;
		try {

				cylinderBean.setCylinderstatus("2");
				System.out.println("----onChangeCylinderFilledStatusData---" + cylinderBean.getStationname()+"-------size-------"+cylinderBean.getSize()+"-------STATUS-------"+cylinderBean.getCylinderstatus());
				retlist = cylindermasterDao.onChangeStoreAndCylinderType(cylinderBean);
				for (CylindermasterBean cylindermasterBean : retlist) {
					 count1 =cylindermasterBean.getCount1();
				}
				
				sJson=count1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sJson;

	}
	@RequestMapping("onChangeCylinderQualityCheck")
	public @ResponseBody String onChangeCylinderQualityCheck(CylindermasterBean cylinderBean) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		String count1=null;
		List<CylindermasterBean> retlist = null;
		try {

				cylinderBean.setCylinderstatus("3");
				System.out.println("----onChangeCylinderQualityCheck---" + cylinderBean.getStationname()+"-------size-------"+cylinderBean.getSize()+"-------STATUS-------"+cylinderBean.getCylinderstatus());
				retlist = cylindermasterDao.onChangeStoreAndCylinderType(cylinderBean);
				for (CylindermasterBean cylindermasterBean : retlist) {
					 count1 =cylindermasterBean.getCount1();
				}
				
				sJson=count1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sJson;

	}
	@RequestMapping("onChangeCylinderMovetoTruck")
	public @ResponseBody String onChangeCylinderMovetoTruck(CylindermasterBean cylinderBean) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		String count1=null;
		List<CylindermasterBean> retlist = null;
		try {

				cylinderBean.setCylinderstatus("4");
				System.out.println("----onChangeCylinderQualityCheck---" + cylinderBean.getStationname()+"-------size-------"+cylinderBean.getSize()+"-------STATUS-------"+cylinderBean.getCylinderstatus());
				retlist = cylindermasterDao.onChangeStoreAndCylinderType(cylinderBean);
				for (CylindermasterBean cylindermasterBean : retlist) {
					 count1 =cylindermasterBean.getCount1();
				}
				
				sJson=count1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sJson;

	}
	@RequestMapping("onChangeQualityCheckHome")
	public @ResponseBody String onChangeQualityCheckHome(CylindermasterBean cylinderBean) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		String count1=null;
		List<CylindermasterBean> retlist = null;
		try {

				cylinderBean.setCylinderstatus("7");
				System.out.println("----onChangeQualityCheckHome---" + cylinderBean.getStationname()+"-------size-------"+cylinderBean.getSize()+"-------STATUS-------"+cylinderBean.getCylinderstatus());
				retlist = cylindermasterDao.onChangeStoreAndCylinderType(cylinderBean);
				for (CylindermasterBean cylindermasterBean : retlist) {
					 count1 =cylindermasterBean.getCount1();
				}
				
				sJson=count1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sJson;

	}

	@RequestMapping(value = "/cylinderFilledStatus")
	public String cylinderFiledStatus(
			@ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus = "2";
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
	public @ResponseBody String searchQualityCheck1(HttpServletRequest request) {
		JSONObject objJson = new JSONObject();
		List<CylindermasterBean> retlist = null;
		String stationname = null;
		String quantity = null;
		String cylinderType = null;
		int limit = 100;
		try {
			stationname = request.getParameter("stationname");
			quantity = request.getParameter("quantity");
			if (StringUtils.isNotBlank(quantity)) {
				limit = Integer.parseInt(quantity);
			}
			cylinderType = request.getParameter("cylinderType");
			if (stationname != "" && quantity != "" && cylinderType != "") {

				retlist = cylindermasterDao.searchQualityCheck(stationname, cylinderType, limit, "2");
				if (retlist != null) {
					objJson.put("allOrders1", retlist);
				} else {
					objJson.put("allOrders1", "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(objJson);

	}

	@RequestMapping(value = "/updateCylinderStatus2")
	public @ResponseBody String updateCylinderStatus2(CylindertransactionBean cylindertransactionBean1, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		JSONObject objJson = new JSONObject();
		CylindertransactionBean cylindertransactionBean = null;
		CylindermasterBean cylindermasterBean1 = null;
		int intcount=0;
		JSONObject jsonObject=new JSONObject();
		try {
			KhaibarUsersBean users = (KhaibarUsersBean) session.getAttribute("cacheUserBean");
			String cylenderId = cylindertransactionBean1.getCylindetId();

			String[] cylenderId1 = cylenderId.split(",");
			for (int i = 0; i < cylenderId1.length; i++) {

				CylindermasterBean cylindermasterBean = cylindermasterDao
						.getByCylinderName(Integer.parseInt(cylenderId1[i]));
				int cylinderCapacity = 0;
				if (cylindermasterBean != null) {
					if (StringUtils.isNotBlank(cylindermasterBean.getName())) {
						String numberOnly = cylindermasterBean.getName().replaceAll("[^0-9]", "");
						cylinderCapacity = Integer.parseInt(numberOnly);
						fillingstationmasterDao.updateUsedGas(
								Integer.parseInt(cylindertransactionBean1.getFillingStation()), cylinderCapacity);
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
				boolean count=cylindermasterDao.updateCylinderStatus(cylindermasterBean1);
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

	@RequestMapping(value = "/cylinderQualityCheck")
	public String cylinderQualityCheck(
			@ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus = "3";
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
	public @ResponseBody String searchQualityCheck(HttpServletRequest request) {
		JSONObject objJson = new JSONObject();
		List<CylindermasterBean> retlist = null;
		String stationname = null;
		String quantity = null;
		String cylinderType = null;
		int limit = 100;
		try {
			stationname = request.getParameter("stationname");
			quantity = request.getParameter("quantity");
			if (StringUtils.isNotBlank(quantity)) {
				limit = Integer.parseInt(quantity);
			}
			cylinderType = request.getParameter("cylinderType");
			if (stationname != "" && quantity != "" && cylinderType != "") {

				retlist = cylindermasterDao.searchQualityCheck(stationname, cylinderType, limit, "3");
				if (retlist != null) {
					objJson.put("allOrders1", retlist);
				} else {
					objJson.put("allOrders1", "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(objJson);

	}

	@RequestMapping(value = "/updateCylinderStatus1")
	public @ResponseBody String updateCylinderStatus1(CylindertransactionBean cylindertransactionBean1, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		JSONObject objJson = new JSONObject();
		CylindertransactionBean cylindertransactionBean = null;
		CylindermasterBean cylindermasterBean = null;
		int intcount=0;
		JSONObject jsonObject=new JSONObject();
		try {
			KhaibarUsersBean users = (KhaibarUsersBean) session.getAttribute("cacheUserBean");
			String cylenderId = cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 = cylenderId.split(",");
			for (int i = 0; i < cylenderId1.length; i++) {
				cylindertransactionBean = new CylindertransactionBean();
				cylindermasterBean = new CylindermasterBean();
				cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
				cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
				cylindertransactionBean.setFillingStation(cylindertransactionBean1.getFillingStation());
				cylindertransactionBean.setCylindetId(cylenderId1[i]);
				cylindermasterBean.setId(Integer.parseInt(cylenderId1[i]));
				cylindermasterBean.setCylinderstatus(cylindertransactionBean1.getCylinderStatus());
				cylindertransactionDao.save(cylindertransactionBean);
				boolean count=cylindermasterDao.updateCylinderStatus(cylindermasterBean);
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

	@RequestMapping(value = "/cylinderMovetoTruck")
	public String cylinderMovetoTruck(
			@ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			String cylinderstatus = "4";
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

	@RequestMapping(value = "/updateCylinderStatus3")
	public @ResponseBody String updateCylinderStatus3(CylindertransactionBean cylindertransactionBean1, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		JSONObject objJson = new JSONObject();
		CylindertransactionBean cylindertransactionBean = null;
		CylindermasterBean cylindermasterBean = null;
		int intcount=0;
		JSONObject jsonObject=new JSONObject();
		try {
			KhaibarUsersBean users = (KhaibarUsersBean) session.getAttribute("cacheUserBean");
			String cylenderId = cylindertransactionBean1.getCylindetId();
			String[] cylenderId1 = cylenderId.split(",");
			for (int i = 0; i < cylenderId1.length; i++) {
				cylindertransactionBean = new CylindertransactionBean();
				cylindermasterBean = new CylindermasterBean();
				cylindertransactionBean.setCreatedBy(String.valueOf(users.getId()));
				cylindertransactionBean.setCylinderStatus(cylindertransactionBean1.getCylinderStatus());
				cylindertransactionBean.setTruckId(cylindertransactionBean1.getTruckId());
				cylindertransactionBean.setCylindetId(cylenderId1[i]);
				cylindermasterBean.setId(Integer.parseInt(cylenderId1[i]));
				cylindermasterBean.setCylinderstatus(cylindertransactionBean1.getCylinderStatus());
				cylindermasterBean.setTruckId(cylindertransactionBean1.getTruckId());
				cylindertransactionDao.save(cylindertransactionBean);
				boolean count=cylindermasterDao.updateCylinderStatus(cylindermasterBean);
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

	@RequestMapping("/searchTruckStatus")
	public @ResponseBody String searchTruckStatus(HttpServletRequest request) {
		JSONObject objJson = new JSONObject();
		List<CylindermasterBean> retlist = null;
		String stationname = null;
		String quantity = null;
		String cylinderType = null;
		int limit = 100;
		try {
			stationname = request.getParameter("stationname");
			quantity = request.getParameter("quantity");
			if (StringUtils.isNotBlank(quantity)) {
				limit = Integer.parseInt(quantity);
			}
			cylinderType = request.getParameter("cylinderType");
			if (stationname != "" && quantity != "" && cylinderType != "") {

				retlist = cylindermasterDao.searchQualityCheck(stationname, cylinderType, limit, "4");
				if (retlist != null) {
					objJson.put("allOrders1", retlist);
				} else {
					objJson.put("allOrders1", "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(objJson);

	}

	@RequestMapping(value = "/cylinderDeliver")
	public String cylinderDeliver(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,
			HttpServletRequest request, HttpSession session) {
		String sJson = null;
		List<LpomasterBean> lpoList = null;
		try {
			String sPropFilePath = objContext.getRealPath("Resources" + File.separator + "DataBase.properties");
			if (StringUtils.isNotBlank(sPropFilePath)) {
				Properties objProperties = new Properties();
				InputStream objStream = new FileInputStream(sPropFilePath);
				objProperties.load(objStream);
				String vat = objProperties.getProperty("vat");
				System.out.println(vat);
				request.setAttribute("vat", vat);
			}
			
			 * sJson=lpomasterDao.getAllCustomer(); if(sJson !=null){
			 * 
			 * request.setAttribute("allObjects", sJson); }else{
			 * 
			 * request.setAttribute("allObjects", "''"); }
			 

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderDeliver";
	}

	@RequestMapping(value = "/cylinderDeliverSave")
	public String cylinderDeliverSave(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,
			@RequestParam("unit") String[] unit, @RequestParam("rate") String[] rate,
			@RequestParam("totalvalue") String[] totalvalue, @RequestParam("discount") String[] discount,
			@RequestParam("item1") String[] item, @RequestParam("taxable") String[] taxable,@RequestParam(value = "cylinderId", required=false) String[] cylinderId,
			
			@RequestParam("cylinderDeliverTruck") String cylinderDeliverTruck,
			@RequestParam("cylinderReturnTruck") String cylinderReturnTruck,
			@RequestParam("customerId") String customerId, @RequestParam("payedAmount") String payedAmount,
			@RequestParam("dueAmount") String dueAmount,@RequestParam("netAmount") String netAmount, @RequestParam("vat") String vat,
			@RequestParam(value = "company", required=false) String[] company,HttpServletRequest request,@RequestParam(value = "previousDueAmount", required=false) String previousDueAmount,
			HttpSession session) {
		String sJson = null;
		List<LpomasterBean> lpoList = null;
		CustomercylindersBean customercylindersBean = null;
		TransactionStatus objTransStatus = null;
		TransactionDefinition objTransDef = null;
//		String company = null;
		try {
//			cylinderId = request.getParameter("cylinderId");
//			company = request.getParameter("company");
			objTransDef = new DefaultTransactionDefinition();
			objTransStatus = transactionManager.getTransaction(objTransDef);
			System.out.println(customerId);
			for (int i = 0; i < item.length; i++) {
					
				ItemsBean itemsBean = itemsDao.getById(Integer.parseInt(item[i]));
				if(itemsBean.getItemType().equals("Accessories")){
					customercylindersBean = new CustomercylindersBean();
					customercylindersBean.setQuantity("1");
					customercylindersBean.setPrice(rate[i]);
					customercylindersBean.setTotalPrice(totalvalue[i]);
					customercylindersBean.setDiscount(discount[i]);
					customercylindersBean.setVat(vat);
					customercylindersBean.setAccessoriesId(item[i]);
					customercylindersBean.setCustomerId(customerId);
					customercylindersBean.setCylinderDeliverTruck(cylinderDeliverTruck);
					customercylindersDao.save(customercylindersBean);
				}
					List<CylindermasterBean> listOrderBeans = cylindermasterDao.getInTruckCylinders(cylinderDeliverTruck, item[i],Integer.parseInt(unit[i]));
					if (listOrderBeans != null) {
						for (CylindermasterBean cylindermasterbean : listOrderBeans) {
							CylindermasterBean cylinderMasterBean1 = new CylindermasterBean();
							customercylindersBean = new CustomercylindersBean();
							customercylindersBean.setQuantity("1");
							customercylindersBean.setPrice(rate[i]);
							customercylindersBean.setTotalPrice(totalvalue[i]);
							customercylindersBean.setDiscount(discount[i]);
							customercylindersBean.setVat(vat);
							customercylindersBean.setCustomerId(customerId);
							customercylindersBean.setCylinderDeliverTruck(cylinderDeliverTruck);
							customercylindersBean.setCylinderReturnTruck(cylinderReturnTruck);
							customercylindersBean.setCylinderId(String.valueOf(cylindermasterbean.getId()));
							customercylindersBean.setCylinderreturn("0");
							customercylindersDao.save(customercylindersBean);
							cylinderMasterBean1.setId(cylindermasterbean.getId());
							cylinderMasterBean1.setCylinderstatus("6");
							cylinderMasterBean1.setCustomerid(customerId);
							cylindermasterDao.updateCylinderStatus(cylinderMasterBean1);
						}
						// quantity,price,discount,grandTotal,vat,cylinderDeliverTruck,cylinderReturnTruck
				}
			}
			if(cylinderId !=null){
				for(int i=0;i<cylinderId.length;i++){
					CylindermasterBean cylinderMasterBean2 = new CylindermasterBean();
					customercylindersDao.updateCustomerCylinderStatus(cylinderId[i]);
					cylinderMasterBean2.setId(Integer.parseInt(cylinderId[i]));
					cylinderMasterBean2.setCylinderstatus("7");
					cylinderMasterBean2.setOwnercompany(company[i]);
					CompanymasterBean companymasterBean= companymasterDao.getById(Integer.parseInt(company[i]));
					if(!companymasterBean.getTypeofcompany().equals("Owner")){
						cylinderMasterBean2.setCylinderstatus("8");
						cylinderMasterBean2.setStore("100");
					}
					cylindermasterDao.updateCylinderStatus(cylinderMasterBean2);
			}
			}
			CustomermasterBean dummy = customermasterDao.getById(Integer.parseInt(customerId));
				CustomermasterBean customermasterBean=new CustomermasterBean();
				if(StringUtils.isNotBlank(payedAmount)){
					customermasterBean.setPayedAmount(payedAmount);
				}
				if(StringUtils.isNotBlank(dueAmount)){
					customermasterBean.setDueAmount(dueAmount);			
				}
				if(StringUtils.isNotBlank(netAmount)){
					customermasterBean.setNetAmount(netAmount);
				}else{
					customermasterBean.setNetAmount(dummy.getNetAmount());
				}
				if(StringUtils.isNotBlank(previousDueAmount)){
					customermasterBean.setPreviousDueAmount(previousDueAmount);
				}
				if(cylinderId != null){
					String cid =StringUtils.join(cylinderId,"','");
					System.out.println(Arrays.toString(cylinderId));
					customermasterBean.setCylinderId(cid);
				}
				customermasterBean.setId(Integer.parseInt(customerId));
				customermasterDao.updateCylinderPrice(customermasterBean);
			transactionManager.commit(objTransStatus);
		} catch (Exception e) {
			transactionManager.rollback(objTransStatus);
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:cylinderDeliver";
	}

	@RequestMapping(value = "/getTruckCylinders")
	public @ResponseBody String deletetruckMaster(CylindertransactionBean objCylindertransactionBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		List<CylindermasterBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			if (StringUtils.isNotBlank(objCylindertransactionBean.getTruckId())) {
				listOrderBeans = cylindermasterDao.getInTruckCylinders(objCylindertransactionBean.getTruckId(), null,0);
			}
			objectMapper = new ObjectMapper();
			if (listOrderBeans != null) {

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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption" + e);
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
			List<CylinderTypesBean> list = cylindermasterDao.getCylinderstypes();
			for (CylinderTypesBean bean : list) {
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
			List<ItemsBean> list = itemsDao.getItems();
			for (ItemsBean bean : list) {
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
	}

	@ModelAttribute("companys")
	public Map<Integer, String> populatecompanys() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,companyname as name from companymaster where status='1' ";
			List<CylinderTypesBean> list = lpomasterDao.populate(sSql);
			for (CylinderTypesBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
*/
}
