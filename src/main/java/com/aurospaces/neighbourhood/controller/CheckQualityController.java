package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;

public class CheckQualityController {
	
	CylindermasterDao cylindermasterDao;
	private Logger logger = Logger.getLogger(TransactionController.class);
	
	@RequestMapping(value = "/checkQuality")
	public String checkQualityHome(  CylindermasterBean objCylindermasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {
	ObjectMapper objectMapper = null;
	String sJson = null;
	List<CylindermasterBean> listOrderBeans = null;
	try {
		listOrderBeans = cylindermasterDao.getEmptyCylinders();
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
	return "checkQualityHome";
}


}
