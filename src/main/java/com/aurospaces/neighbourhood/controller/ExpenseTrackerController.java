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

import com.aurospaces.neighbourhood.bean.Expensetracker;
import com.aurospaces.neighbourhood.db.dao.ExpensetrackerDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class ExpenseTrackerController {
	@Autowired ExpensetrackerDao expensetrackerDao;
	
	
	@RequestMapping(value = "/expenseTrackerHome")
	public String expenseTrackerHome(@ModelAttribute("expensiveTrackerForm")Expensetracker expensetracker,HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<Expensetracker> ExpensetrackerList=null;
		try {
			
			System.out.println("-------home--------");
			
			sJson=expensetrackerDao.getAllExpenseTracker("1");
			if(sJson !=null){
				
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "expenseTrackerHome";
	}
	
	@RequestMapping(value = "/expenseTrackerSave")
	public  String expenseTrackerSave(@ModelAttribute("expensiveTrackerForm")Expensetracker expensetracker, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes reAttributes) {
		boolean isInsert = false;
		String sJson = "";
		List<Expensetracker> lstexpensetracker=null;
		 String sProductId ="";
		 Integer existId =null;
		try {
			System.out.println("--------expenseTrackeSave----------");
			
			
			if( expensetracker.getId() == 0){
				expensetracker.setStatus("1");
				expensetrackerDao.save(expensetracker);
				reAttributes.addFlashAttribute("msg", "Add record Sucessfull");
				 reAttributes.addFlashAttribute("cssMsg", "success");
			}else{
				expensetrackerDao.save(expensetracker);
				 reAttributes.addFlashAttribute("msg", "Record Updated Successfully");
				 reAttributes.addFlashAttribute("cssMsg", "warning");
			}
			
			/*String sMobileNo=customermasterBean.getMobile();
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
			}*/
			
			
		} catch (Exception e) {
			System.out.println("Exception in Product Controller in productSave()");
			e.printStackTrace();
		}
		return "redirect:expenseTrackerHome";
	}
	
	@RequestMapping(value = "/trckerDelete")
	public @ResponseBody String trckerDelete( @RequestParam("id") String id,@RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		  int dId=Integer.parseInt(id);
		  isDelete = expensetrackerDao.delete(dId,status);
		 
			  sJson=expensetrackerDao.getAllExpenseTracker("1");
			  System.out.println("deleted cusmer data--"+sJson);
				
		
		
		return sJson;
	}
	@RequestMapping(value = "/inActiveExpense")
	public @ResponseBody String inActiveExpense( @RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		String sJson = "";
		 try{
		  sJson=expensetrackerDao.getAllExpenseTracker(status);
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		return sJson;
	}
	
	
	
}
