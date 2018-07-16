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
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.bean.UsedGasBean;
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
	@RequestMapping(value = "/gasReport")
	public String gasReports(@ModelAttribute("gasReportsForm") Expensetracker expensetracker, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "gasReport";
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
	
	@RequestMapping("searchGasReport")
	public @ResponseBody String searchGasReport(UsedGasBean usedGasBean ) {
		ObjectMapper objectMapper = null;
		JSONObject jsonObject = new JSONObject();
		List<UsedGasBean> listOrderBeans = null;
		String monthnumber = null;
		String year = null;
		try {
			if(StringUtils.isNotBlank(usedGasBean.getMonth())){
				String month1 = usedGasBean.getMonth();
				 String [] arrOfStr = month1.split("/");
				  monthnumber = arrOfStr[0];
				  year = arrOfStr[1];
			}
			if(StringUtils.isNotBlank(usedGasBean.getFromDate())){
				Date date=  KhaibarGasUtil.dateFormate(usedGasBean.getFromDate());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				usedGasBean.setFromDate(String.valueOf(sqlDate));
			}
			if(StringUtils.isNotBlank(usedGasBean.getToDate())){
				Date date=  KhaibarGasUtil.dateFormate(usedGasBean.getToDate());
						java.sql.Date sqlDate = new java.sql.Date(date.getTime());
						usedGasBean.setToDate(String.valueOf(sqlDate));
			}
			if(StringUtils.isNotBlank(usedGasBean.getCustomerType())){
				listOrderBeans = expensetrackerDao.getGasReport(usedGasBean,monthnumber,year);
			}else{
				
				listOrderBeans = expensetrackerDao.getSearchReport(usedGasBean,monthnumber,year);
			}
			
			
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
	@RequestMapping(value = "/gassummary")
	public String gassummary(@ModelAttribute("gasReportsForm") Expensetracker expensetracker, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "gassummary";
	}
	@RequestMapping("searchGassummary")
	public @ResponseBody String searchGassummary(UsedGasBean usedGasBean ) {
		ObjectMapper objectMapper = null;
		JSONObject jsonObject = new JSONObject();
		List<Map<String,Object>> listOrderBeans = null;
		String monthnumber = null;
		String year = null;
		try {
			if(StringUtils.isNotBlank(usedGasBean.getMonth())){
				String month1 = usedGasBean.getMonth();
				 String [] arrOfStr = month1.split("/");
				  monthnumber = arrOfStr[0];
				  year = arrOfStr[1];
			}
			if(StringUtils.isNotBlank(usedGasBean.getFromDate())){
				Date date=  KhaibarGasUtil.dateFormate(usedGasBean.getFromDate());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				usedGasBean.setFromDate(String.valueOf(sqlDate));
			}
			if(StringUtils.isNotBlank(usedGasBean.getToDate())){
				Date date=  KhaibarGasUtil.dateFormate(usedGasBean.getToDate());
						java.sql.Date sqlDate = new java.sql.Date(date.getTime());
						usedGasBean.setToDate(String.valueOf(sqlDate));
			}
//			if(StringUtils.isNotBlank(usedGasBean.getCustomerType())){
//				listOrderBeans = expensetrackerDao.getGascustomersummary(usedGasBean,monthnumber,year);
//			}else{
				
				listOrderBeans = expensetrackerDao.getSearchgassumaryreport(usedGasBean,monthnumber,year);
//			}
			
			
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
	
//	getSearchgassumaryreport
	
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
	
	@ModelAttribute("fillingstation")
	public Map<Integer, String> populateCity() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,stationname from fillingstationmaster where status='1' ";
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
	
	@ModelAttribute("items")
	public Map<Integer, String> populateitems() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,name from items where status='1' and itemType='Cylinder' ";
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
	
	}
