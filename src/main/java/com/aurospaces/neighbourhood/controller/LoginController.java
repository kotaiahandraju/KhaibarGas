package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;

@Controller
public class LoginController {
	
	@Autowired
	CylindermasterDao cylindermasterDao;


	
	
	
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
//		List<CylindermasterBean> list =cylindermasterDao.getCylinders();
//		model.addAttribute("cylinders", cylindermasterDao.getCylinders());
		
		
		
		
		return "redirect:LoginHome";
	}
	}
	

}
