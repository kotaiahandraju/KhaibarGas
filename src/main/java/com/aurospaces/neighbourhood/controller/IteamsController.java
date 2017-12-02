package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/admin")
public class IteamsController {
	
	
		
		@Autowired	ItemsDao iteamsmasterDao;
		private Logger logger = Logger.getLogger(StoreController.class);
		
		@RequestMapping(value = "/itemsHome")
		public String storeHome(@ModelAttribute("itemsForm")ItemsBean itemsmasterBean,HttpServletRequest request,
				HttpSession session) {
			ObjectMapper objectMapper = null;
			String sJson = null;
			List<ItemsBean> itemsList=null;
			try {
				
				sJson=iteamsmasterDao.getAllItems();
				if(sJson !=null){
					
					 request.setAttribute("allObjects", sJson);
				}else{
					
					request.setAttribute("allObjects", "''");
				}
				

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);

			}
			return "itemsHome";
		}
		
		@RequestMapping(value = "/itemsSave")
		public  String storeSave(@ModelAttribute("itemsForm")ItemsBean itemmasterBean, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes reAttributes) {
			boolean isInsert = false;
			String sJson = "";
			List<ItemsBean> storeBean=null;
			 String sProductId ="";
			 Integer existId =null;
			 Random ran = new Random();
			try {
				System.out.println("--------customerSave----------");
				
				String sName=itemmasterBean.getName();
				storeBean=iteamsmasterDao.getByItemName(sName);
				System.out.println("itemSave"+storeBean);
				if(storeBean.size() ==0 || storeBean ==null){
					itemmasterBean.setStatus("1");
					iteamsmasterDao.save(itemmasterBean);
					reAttributes.addFlashAttribute("msg", "Record Inserted Successfully with Id:");
					reAttributes.addFlashAttribute("cssMsg", "success");
				}else{
					for (ItemsBean iterarateList : storeBean) {
						
						 existId=iterarateList.getId();
						 if(existId==itemmasterBean.getId()){
							 iteamsmasterDao.save(itemmasterBean);
							 reAttributes.addFlashAttribute("msg", "Record Updated Successfully");
								reAttributes.addFlashAttribute("cssMsg", "warning");

						 }else{
							 reAttributes.addFlashAttribute("msg", "Already Record Exist");
								reAttributes.addFlashAttribute("cssMsg", "danger");

							}
					}
				}
				
				
			} catch (Exception e) {
				System.out.println("Exception in Product Controller in productSave()");
				e.printStackTrace();
			}
			return "redirect:itemsHome";
		}
		
		@RequestMapping(value = "/itemDelete")
		public @ResponseBody String storeDelete( @RequestParam("id") String id,@RequestParam("status") String status, HttpSession objSession,
				HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
			boolean isDelete = false;
			String sJson = "";
			Boolean itemsmasterBean=null;
			List<ItemsBean> items=null;
			ObjectMapper objectMapper = null;
			  int dId=Integer.parseInt(id);
			  	iteamsmasterDao.delete(dId,status);
			 
			  if(isDelete){
				  sJson=iteamsmasterDao.getAllItems();
				  System.out.println("deleted cusmer data--"+sJson);
					
				}
			
			
			return sJson;
		}
		
		
		
	}



