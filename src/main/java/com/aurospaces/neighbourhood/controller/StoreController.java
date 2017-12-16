package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.db.dao.StoresmasterDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/admin")
public class StoreController {
	
	@Autowired	StoresmasterDao storesmasterDao;
	private Logger logger = Logger.getLogger(StoreController.class);
	
	@RequestMapping(value = "/storeHome")
	public String storeHome(@ModelAttribute("storeForm")StoresmasterBean storesmasterBean,HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CustomermasterBean> customerList=null;
		try {
			
			sJson=storesmasterDao.getAllStore();
			if(sJson !=null){
				
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "storeHome";
	}
	
	@RequestMapping(value = "/storeSave")
	public  String storeSave(@ModelAttribute("storeForm")StoresmasterBean storesmasterBean, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes reAttributes) {
		boolean isInsert = false;
		String sJson = "";
		List<StoresmasterBean> storeBean=null;
		 String sProductId ="";
		 Integer existId =null;
		 Random ran = new Random();
		try {
			System.out.println("--------storeSave----------");
			
			String sName=storesmasterBean.getStorename();
			storeBean=storesmasterDao.getByStoreName(sName);
			System.out.println("customerSave"+storeBean);
			if(storeBean.size() ==0 || storeBean ==null){
				//storesmasterBean.setStoreid(CommonUtils.getAutoGenId());
				storesmasterBean.setStatus("1");
//				String str=storesmasterBean.getStorename().substring(0,3) +String.format("%03d", ran.nextInt(1000));
//				storesmasterBean.setStoreid(str);
				storesmasterDao.save(storesmasterBean);
				reAttributes.addFlashAttribute("msg", "Record Inserted Successfully");
				reAttributes.addFlashAttribute("cssMsg", "success");
			}else{
				for (StoresmasterBean iterarateList : storeBean) {
					
					 existId=iterarateList.getId();
					 if(existId==storesmasterBean.getId()){
						 storesmasterDao.save(storesmasterBean);
						 reAttributes.addFlashAttribute("msg", "Record Updated Successfully");
							reAttributes.addFlashAttribute("cssMsg", "warning");

					 }else{
						 reAttributes.addFlashAttribute("msg", "Already Record Exist");
							reAttributes.addFlashAttribute("cssMsg", "danger");

						}
				}
			}
			
			storesmasterDao.updateStoreIds();
		} catch (Exception e) {
			System.out.println("Exception in Product Controller in productSave()");
			e.printStackTrace();
		}
		return "redirect:storeHome";
	}
	
	@RequestMapping(value = "/storeDelete")
	public @ResponseBody String storeDelete( @RequestParam("id") String id,@RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		Boolean accessoriesmasterBean=null;
		List<AccessoriesmasterBean> accessories=null;
		ObjectMapper objectMapper = null;
		  int dId=Integer.parseInt(id);
		  isDelete = storesmasterDao.delete(dId,status);
		 
		  if(isDelete){
			  sJson=storesmasterDao.getAllStore();
			  System.out.println("deleted cusmer data--"+sJson);
				
			}
		
		
		return sJson;
	}
	
	@RequestMapping(value = "/getStoreDetails")
	public @ResponseBody String deletetruckMaster( StoresmasterBean storesmasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<StoresmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(storesmasterBean.getId() != 0){
			listOrderBeans = storesmasterDao.getStoreDetails(storesmasterBean);
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
	
}
