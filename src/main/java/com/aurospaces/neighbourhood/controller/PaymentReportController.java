/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.db.dao.PaymentHistoryDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;

/**
 * @author Kotaiah
 *
 */
@Controller
@RequestMapping("admin")
public class PaymentReportController {
	@Autowired PaymentHistoryDao paymentHistoryDao;
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
//	getPaymentReport
}
