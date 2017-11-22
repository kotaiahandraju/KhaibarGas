package com.aurospaces.neighbourhood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/admin")
public class TrucksController {
	@RequestMapping(value = "/truckHome")
	public String LoginHome(@ModelAttribute("truckForm") TrucksmasterBean objTrucksmasterBean, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "truckHome";
	}
}
