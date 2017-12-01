package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class LpoMasterController {
	
	@Autowired
	LpomasterDao lpomasterDao;
	
	private Logger logger = Logger.getLogger(LpoMasterController.class);
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
			
			if(StringUtils.isNotBlank(lpomasterBean.getExpiryDate1())){
			Date date=	KhaibarGasUtil.dateFormate(lpomasterBean.getExpiryDate1());
			lpomasterBean.setExpiryDate(date);
			}
			String sLpoNo=lpomasterBean.getLponumber();
			lpomaster=lpomasterDao.getByLpoNo(sLpoNo);
			System.out.println("customerSave"+lpomaster);
			if(lpomaster.size() ==0 || lpomaster ==null){
				lpomasterBean.setStatus("1");
				lpomasterDao.save(lpomasterBean);
				reAttributes.addFlashAttribute("msg", "Record Inserted Successfully");
				reAttributes.addFlashAttribute("cssMsg", "success");
			}else{
				for (LpomasterBean lpomasterBean2 : lpomaster) {
					
					 existId=lpomasterBean2.getId();
					 if(existId==lpomasterBean.getId()){
						 lpomasterDao.save(lpomasterBean);
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
		return "redirect:lpoHome";
	}
	
	@RequestMapping(value = "/lpoDelete")
	public @ResponseBody String lpoDelete( @RequestParam("id") String id, @RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		Boolean accessoriesmasterBean=null;
		List<AccessoriesmasterBean> accessories=null;
		ObjectMapper objectMapper = null;
		  int dId=Integer.parseInt(id);
		  isDelete = lpomasterDao.delete(dId,status);
		 
		  if(isDelete){
			  sJson=lpomasterDao.getAllCustomer();
			  System.out.println("deleted cusmer data--"+sJson);
				
			}
		
		
		return sJson;
	}
	@RequestMapping(value = "/getLPOdetails")
	public @ResponseBody String deletetruckMaster( LpomasterBean lpomasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<TrucksmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			
			/*if(objTrucksmasterBean.getId() != 0 && objTrucksmasterBean.getStatus() != ""){
 				delete = objTrucksmasterDao.delete(objTrucksmasterBean.getId(),objTrucksmasterBean.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = objTrucksmasterDao.getAllTrucks();
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
			}*/
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
	
	@ModelAttribute("items")
	public Map<Integer, String> populateCity() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,name from items ";
			List<CylinderTypesBean> list = lpomasterDao.populate(sSql);
			for (CylinderTypesBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}

	
}
