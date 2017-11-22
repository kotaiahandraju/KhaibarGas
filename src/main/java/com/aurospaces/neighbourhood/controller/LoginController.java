package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController {
	
	@Autowired
	CylindermasterDao cylindermasterDao;


	
	@RequestMapping(value = "/LoginHome")
	public String LoginHome(Map<String, Object> model1, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		
		List<CylindermasterBean> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			
			model.addAttribute("cylinderForm",new CylindermasterBean());
			//model.addAttribute("cylinders", cylindermasterDao.getCylinders());
			listOrderBeans =cylindermasterDao.getCylinders();
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
			System.out.print("firstblock1");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "addCylinder";
	}
	
	
	@RequestMapping(value = "/addCylinder", method = RequestMethod.POST)
	String khaibarGasPost(@Valid @ModelAttribute("cylinderForm") CylindermasterBean cylinder,BindingResult bindingresults,Model model) {
    {
    	System.out.print("firstblock2");
		if(bindingresults.hasErrors())
		{
		return "cylinderMaster";
	    }
		
		cylindermasterDao.save(cylinder);
		model.addAttribute("cylinder",new CylindermasterBean());
		List<CylindermasterBean> list =cylindermasterDao.getCylinders();
		model.addAttribute("cylinders", cylindermasterDao.getCylinders());
		
		
		
		
		return "redirect:LoginHome";
	}
	}
	

}
