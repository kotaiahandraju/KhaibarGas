package com.aurospaces.neighbourhood.controller;

import java.util.List;

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

import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		int cylindersCount = 0;
		int customerCount = 0;
		try {
			
			 cylindersCount=cylindermasterDao.getCylindersCount();
			 customerCount = objCustomerDao.getCustomerCount();
			session.setAttribute("cylinderCount", cylindersCount);
			objJson.put("cylinderCount", cylindersCount);
			objJson.put("customerCount", customerCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return String.valueOf(objJson);
	}
	
	
}
