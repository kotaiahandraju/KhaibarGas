package com.aurospaces.neighbourhood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CylinderController {
	@RequestMapping(value = "/CylinderHome")
	public String LoginHome( CylindermasterBean objCylindermasterBean, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
		
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderHome";
	}
	
}
