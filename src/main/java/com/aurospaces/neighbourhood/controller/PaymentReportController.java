/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.PrintDataBean;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.aurospaces.neighbourhood.db.dao.PaymentHistoryDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.aurospaces.neighbourhood.util.SendAttachmentInEmail;

/**
 * @author Kotaiah
 *
 */
@Controller
@RequestMapping("admin")
public class PaymentReportController {
	@Autowired PaymentHistoryDao paymentHistoryDao;
	@Autowired ServletContext objContext;
	@Autowired LpomasterDao lpomasterDao;
	@RequestMapping(value = "/paymentreport")
	public String paymentreport(@ModelAttribute("gasReportsForm") Expensetracker expensetracker, ModelMap model,
			HttpServletRequest request, HttpSession session) {
	try {
		

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e);

	}
	return "paymentreport";
	}
	

	@RequestMapping("searchpaymentReport")
	public @ResponseBody String searchExpensesReport(Expensetracker expensetracker ) {
		ObjectMapper objectMapper = null;
		JSONObject jsonObject = new JSONObject();
		List<Map<String,Object>> listOrderBeans = null;
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
			listOrderBeans = paymentHistoryDao.getPaymentReport(expensetracker,monthnumber,year);
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
	
	@RequestMapping("getInvoiceData1")
	public @ResponseBody String getInvoiceData(PrintDataBean printDataBean) {
		ObjectMapper objectMapper = null;
		JSONObject jsonObject = new JSONObject();
		List<Map<String,Object>> listOrderBeans = null;
		String monthnumber = null;
		String year = null;
		try {
			
			listOrderBeans = paymentHistoryDao.getInvoiceData(printDataBean.getInvoiceid());
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				// System.out.println(sJson);
				jsonObject.put("allOrders", listOrderBeans);
			} else {
				objectMapper = new ObjectMapper();
				jsonObject.put("allOrders", listOrderBeans);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(jsonObject);

	}
	

	@RequestMapping(value = "/backupData")
	public String backUpdata(
			HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession)  {
		
	
		try{
			
	        
			String propertiespath = objContext.getRealPath("Resources"+ File.separator + "DataBase.properties");

			FileInputStream input = new FileInputStream(propertiespath);
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			String  usermail = prop.getProperty("usermail");
			String  to = prop.getProperty("to");
			String mailpassword = prop.getProperty("mailpassword");
			String port = prop.getProperty("port");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			String dbname = prop.getProperty("db");
			String dbport = prop.getProperty("dbport");
			LpomasterBean lpobean = 	paymentHistoryDao.getmysqlpath();
			String mysqlpath = null;
			if(lpobean != null){
				mysqlpath =lpobean.getRemarks();
			}
			mysqlpath = mysqlpath.replace("Data", "bin"); 
//			select @@datadir
//			Properties prop = new Properties();
//			   String propertiespath = objContext.getRealPath("Resources"
//						+ File.separator + "DataBase.properties");
//			   FileInputStream input = new FileInputStream(propertiespath);
//				// load a properties file
//				prop.load(input);
//				String couponcode = prop.getProperty("usermail");
			
	        
			 byte[] data = SendAttachmentInEmail.getData("localhost", dbport,
					 userName, password, dbname,mysqlpath ).getBytes();		
					   File filedst = new File("backup1.sql");
					   FileOutputStream dest = new FileOutputStream(filedst);
					   dest.write(data);
			SendAttachmentInEmail.send( to , usermail , mailpassword, port);
		}catch(Exception e){
e.printStackTrace();
	System.out.println(e);
		}
	  return "redirect:dashBoard.htm";


	}
	@ModelAttribute("itemType")
	public Map<Integer, String> populateitems() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,name from items where status='1' and itemType IN( 'Cylinder','Accessories','Equipment') ";
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
