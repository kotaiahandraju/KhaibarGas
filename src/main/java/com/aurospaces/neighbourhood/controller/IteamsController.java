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
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/admin")
public class IteamsController {
	
	
		@Autowired CylindermasterDao cylindermasterDao;
		@Autowired	ItemsDao iteamsmasterDao;
		private Logger logger = Logger.getLogger(StoreController.class);
		
		@RequestMapping(value = "/itemsHome")
		public String storeHome(@ModelAttribute("itemsForm")ItemsBean itemsmasterBean,HttpServletRequest request,
				HttpSession session) {
			ObjectMapper objectMapper = null;
			String sJson = null;
			List<ItemsBean> itemsList=null;
			try {
				
				sJson=iteamsmasterDao.getAllItems("1");
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
		public  String storeSave(@ModelAttribute("itemsForm")ItemsBean itemmasterBean, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes redirect) {
			boolean isInsert = false;
			String sJson = "";
			ItemsBean item=null;
			 String sProductId ="";
			 Integer existId =null;
			 Random ran = new Random();
			 int id = 0;
			try {
				System.out.println("--------customerSave----------");
				
				String sName=itemmasterBean.getName();
				item=iteamsmasterDao.getByItemName(sName);
				int dummyId =0;
				if(item != null){
					dummyId = item.getId();
				}
				if(itemmasterBean.getId() != 0)
				{
					id = itemmasterBean.getId();
					if(id == dummyId || item == null )
					{
						iteamsmasterDao.save(itemmasterBean);
						redirect.addFlashAttribute("msg", "Record Updated Successfully");
						redirect.addFlashAttribute("cssMsg", "warning");
					}
					else
					{
						redirect.addFlashAttribute("msg", "Already Record Exist");
						redirect.addFlashAttribute("cssMsg", "danger");
					}
				}
				if(itemmasterBean.getId() == 0 && item == null)
				{
					itemmasterBean.setStatus("1");
					iteamsmasterDao.save(itemmasterBean);
					
					redirect.addFlashAttribute("msg", "Record Inserted Successfully");
					redirect.addFlashAttribute("cssMsg", "success");
				}
				if(itemmasterBean.getId() == 0 && item != null)
				{
					redirect.addFlashAttribute("msg", "Already Record Exist");
					redirect.addFlashAttribute("cssMsg", "danger");
				}
				
				
			} catch (Exception e) {
				System.out.println("Exception in Product Controller in productSave()");
				redirect.addFlashAttribute("msg", "Failed");
				redirect.addFlashAttribute("cssMsg", "danger");
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
			 
				  sJson=iteamsmasterDao.getAllItems("1");
				//  System.out.println("deleted cusmer data--"+sJson);
					
			
			
			return sJson;
		}
		@RequestMapping(value = "/inActiveItem")
		public @ResponseBody String inActiveItem(@RequestParam("status") String status) throws JsonGenerationException, JsonMappingException, IOException {
			boolean isDelete = false;
			String sJson = "";
			Boolean itemsmasterBean=null;
			List<ItemsBean> items=null;
			ObjectMapper objectMapper = null;
			sJson=iteamsmasterDao.getAllItems(status);
			 /// System.out.println("inActiveItem data--"+sJson);
				 /// System.out.println("inActiveItem data--"+sJson);
					
			
			
			return sJson;
		}
		
	
			
		
	}



