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
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class LpoMasterController {
	
	@Autowired
	LpomasterDao lpomasterDao;
	
	
	@RequestMapping(value = "/lpoHome")
	public String lpoHome(@ModelAttribute("lpoForm")LpomasterBean lpomasterBean,HttpServletRequest request,
			HttpSession session) {
		String sJson = null;
		List<LpomasterBean> lpoList=null;
		try {
			
			sJson=lpomasterDao.getAllCustomer();
			if(sJson !=null){
				
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "lpoHome";
	}
	
	@RequestMapping(value = "/lpoSave")
	public  String lpoSave(@ModelAttribute("lpoForm")LpomasterBean lpomasterBean, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes reAttributes) {
		boolean isInsert = false;
		String sJson = "";
		List<LpomasterBean> lpomaster=null;
		 String sProductId ="";
		 Integer existId =null;
		try {
			System.out.println("--------customerSave----------"+lpomasterBean.getAmount());
			
			String sLpoNo=lpomasterBean.getLponumber();
			lpomaster=lpomasterDao.getByLpoNo(sLpoNo);
			System.out.println("customerSave"+lpomaster);
			if(lpomaster.size() ==0 || lpomaster ==null){
				lpomasterBean.setStatus("1");
				lpomasterDao.save(lpomasterBean);
				reAttributes.addFlashAttribute("msg", "Login Sucessfull");
			}else{
				for (LpomasterBean lpomasterBean2 : lpomaster) {
					
					 existId=lpomasterBean2.getId();
					 if(existId==lpomasterBean.getId()){
						 lpomasterDao.save(lpomasterBean);
						 reAttributes.addFlashAttribute("msg", "Record Updated Successfully");

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
		return "redirect:lpoHome";
	}
	
	@RequestMapping(value = "/lpoDelete")
	public @ResponseBody String lpoDelete( @RequestParam("id") String id, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		Boolean accessoriesmasterBean=null;
		List<AccessoriesmasterBean> accessories=null;
		ObjectMapper objectMapper = null;
		  int dId=Integer.parseInt(id);
		  isDelete = lpomasterDao.delete(dId);
		 
		  if(isDelete){
			  sJson=lpomasterDao.getAllCustomer();
			  System.out.println("deleted cusmer data--"+sJson);
				
			}
		
		
		return sJson;
	}
	

	
}
