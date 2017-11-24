package com.aurospaces.neighbourhood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aurospaces.neighbourhood.bean.LoginBean;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	String khaibarGasPost( @ModelAttribute("loginForm") LoginBean cylinder,BindingResult bindingresults,Model model) {
    {
    	System.out.print("firstblock2");
		
		
		
		
		
		
		return "loginPage1";
	}
	}
	

}
