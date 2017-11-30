package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.AccessoriesmasterBean;
import com.aurospaces.neighbourhood.bean.CustomermasterBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class CustomerController {
	
	@Autowired
	CustomermasterDao customermasterDao;
	
	
	@RequestMapping(value = "/customerHome")
	public String customerHome(@ModelAttribute("customerForm")CustomermasterBean customermasterBean,HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CustomermasterBean> customerList=null;
		try {
			
			sJson=customermasterDao.getAllCustomer();
			if(sJson !=null){
				
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "customerHome";
	}
	
	@RequestMapping(value = "/customerSave")
	public  String customerSave(@ModelAttribute("customerForm")CustomermasterBean customermasterBean, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes reAttributes) {
		boolean isInsert = false;
		String sJson = "";
		List<CustomermasterBean> customermaster=null;
		 String sProductId ="";
		 Integer existId =null;
		try {
			System.out.println("--------customerSave----------");
			
			String sMobileNo=customermasterBean.getMobile();
			customermaster=customermasterDao.getByMobileNo(sMobileNo);
			System.out.println("customerSave"+customermaster);
			if(customermaster.size() ==0 || customermaster ==null){
				customermasterBean.setStatus("1");
				customermasterDao.save(customermasterBean);
				reAttributes.addFlashAttribute("msg", "Add record Sucessfull");
				 reAttributes.addFlashAttribute("cssMsg", "success");
			}else{
				for (CustomermasterBean customermasterBean2 : customermaster) {
					
					 existId=customermasterBean2.getId();
					 if(existId==customermasterBean.getId()){
						 customermasterDao.save(customermasterBean);
						 reAttributes.addFlashAttribute("msg", "Record Updated Successfully");
						 reAttributes.addFlashAttribute("cssMsg", "warning");

					 }else{
						 reAttributes.addFlashAttribute("msg", "Mobile Number already exist.");
						 reAttributes.addFlashAttribute("cssMsg", "danger");

						}
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Exception in Product Controller in productSave()");
			e.printStackTrace();
		}
		return "redirect:customerHome";
	}
	
	@RequestMapping(value = "/customerDelete")
	public @ResponseBody String customerDelete( @RequestParam("id") String id,@RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		Boolean accessoriesmasterBean=null;
		List<AccessoriesmasterBean> accessories=null;
		ObjectMapper objectMapper = null;
		  int dId=Integer.parseInt(id);
		  isDelete = customermasterDao.delete(dId,status);
		 
		  if(isDelete){
			  sJson=customermasterDao.getAllCustomer();
			  System.out.println("deleted cusmer data--"+sJson);
				
			}
		
		
		return sJson;
	}
	

	
}
