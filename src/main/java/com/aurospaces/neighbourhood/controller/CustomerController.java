package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	private Logger logger = Logger.getLogger(CustomerController.class);
	@Autowired
	CustomermasterDao customermasterDao;
	
	
	@RequestMapping(value = "/customerHome")
	public String customerHome(@ModelAttribute("customerForm")CustomermasterBean customermasterBean,HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CustomermasterBean> customerList=null;
		try {
			
			sJson=customermasterDao.getAllCustomer("1");
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
			customermasterDao.updateCustomerIds();
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
			  sJson=customermasterDao.getAllCustomer("1");
			  System.out.println("deleted cusmer data--"+sJson);
				
			}
		
		
		return sJson;
	}
	@RequestMapping(value = "/getCustomerIds")
	public @ResponseBody String getCustomerIds( CustomermasterBean objCustomermasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<Map<String, Object>> listOrderBeans  = null;
		List<Map<String, Object>> listcustomeritems  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			

			listOrderBeans = customermasterDao.getCustomers(objCustomermasterBean);
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null ) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
			
			listcustomeritems = customermasterDao.getcustomeritems(objCustomermasterBean);
			
			if (listcustomeritems != null ) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listcustomeritems);
				jsonObj.put("items", listcustomeritems);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listcustomeritems);
				jsonObj.put("items", listcustomeritems);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption"+e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	@RequestMapping(value = "/getCustomerInvoiceData")
	public @ResponseBody String getCustomerInvoiceData( CustomermasterBean objCustomermasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<Map<String, Object>> listOrderBeans  = null;
		List<Map<String, Object>> listOrderBeans1  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		try{
			
			listOrderBeans = customermasterDao.getCustomersDetails(objCustomermasterBean);
			String invoiceId = null;
			if(listOrderBeans !=null){
			for (Map<String, Object> map : listOrderBeans) {
				
				 invoiceId = String.valueOf(map.get("invoiceId"));
			   
			}
			if(StringUtils.isNotBlank(invoiceId)){
				listOrderBeans1 = customermasterDao.getCustomersPrintData(invoiceId);
			}
			}
			 objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
			if (listOrderBeans1 != null && listOrderBeans1.size() > 0) {
				
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans1);
				request.setAttribute("allOrders2", sJson);
				jsonObj.put("allOrders2", listOrderBeans1);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans1);
				request.setAttribute("allOrders2", "''");
				jsonObj.put("allOrders2", listOrderBeans1);
			}
		}catch(Exception e){
			e.printStackTrace();
	System.out.println(e);
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption"+e);
			return String.valueOf(jsonObj);
			
		}
		return String.valueOf(jsonObj);
	}
	
	@RequestMapping(value = "/inActiveCustomer")
	public @ResponseBody String inActiveCustomer( @RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		 try{
			  sJson=customermasterDao.getAllCustomer(status);
			  System.out.println("deleted cusmer data--"+sJson);
		
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return sJson;
	}
	
	
}
