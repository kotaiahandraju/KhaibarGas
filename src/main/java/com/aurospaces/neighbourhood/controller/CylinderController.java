package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
//@RequestMapping(value = "/admin")
public class CylinderController {
	@Autowired
	CylindermasterDao cylindermasterDao;
	
	@RequestMapping(value = "/CylinderHome")
	public String cylinderHome( @Valid @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
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

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderHome";
	}
	
	
	@RequestMapping(value = "/addcylinder", method = RequestMethod.POST)
	public String addCylinder(@Valid @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,BindingResult bindingresults,Model model)
    {
        try
        {
        	
		if(bindingresults.hasErrors())
		{
		return "cylinderHome";
	    }
		
		
			objCylindermasterBean.setStatus("1");
	      cylindermasterDao.save(objCylindermasterBean);
	      
		model.addAttribute("cylinder",new CylindermasterBean());
		//List<CylindermasterBean> list =cylindermasterDao.getCylinders();
	//	model.addAttribute("cylinders", cylindermasterDao.getCylinders());
		
		
        }catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		
		
		return "redirect:CylinderHome";
	}
	
	
	@RequestMapping(value = "/deleteCylinder")
	public  @ResponseBody String deleteCylinder(CylindermasterBean objCylindermasterBean,BindingResult bindingresults,Model model)
    {
		System.out.println("deleteBodyType page...");
		JSONObject jsonObj = new JSONObject();
		boolean delete = false;

        
		
		return "redirect:CylinderHome";
	}
	
	
	}
	
	
	

