package com.aurospaces.neighbourhood.controller;

import java.io.IOException;
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
import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.LpoitemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.db.dao.LpoitemsDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class LpoMasterController {
	
	@Autowired	LpomasterDao lpomasterDao;
	@Autowired LpoitemsDao lpoitemsDao;
	private Logger logger = Logger.getLogger(LpoMasterController.class);
	@RequestMapping(value = "/lpoHome")
	public String lpoHome(@ModelAttribute("lpoForm")LpomasterBean lpomasterBean,HttpServletRequest request,
			HttpSession session) {
		String sJson = null;
		List<LpomasterBean> lpoList=null;
		try {
			
			sJson=lpomasterDao.getAllCustomer("1");
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
		public  String lpoSave(@ModelAttribute("lpoForm")LpomasterBean lpomasterBean, 	@RequestParam("unit") String[] unit,@RequestParam("rate") String[] rate,
				@RequestParam("totalvalue") String[] totalvalue,@RequestParam("discount") String[] discount,@RequestParam("item1") String[] item,
				@RequestParam("taxable") String[] taxable,@RequestParam("manufacturingdate") String[] manufacturingdate,@RequestParam("expirydate") String[] expirydate,HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes redirect) {
			 LpoitemsBean objLpoitemsBean = null;
			 System.out.println("lpoSave");
			 int id = 0;
			 LpomasterBean lpo = null;
			try {
				System.out.println("--------lpoSave----------"+lpomasterBean.getAmount());
				
				String sLpoNo=lpomasterBean.getLponumber();
				LpomasterBean	lpomaster=lpomasterDao.getById(lpomasterBean.getId());
				
				int dummyId =0;
				if(lpomaster != null){
					dummyId = lpomaster.getId();
				}
				if(lpomasterBean.getId() != 0)
				{
					id = lpomasterBean.getId();
					if(id == dummyId || lpomaster == null )
					{
						lpomasterDao.save(lpomasterBean);
						lpomasterDao.updateLPONumber();
						lpo = lpomasterDao.getById(lpomasterBean.getId());
						LpomasterBean deleteLpoItems = lpomasterDao.getById(lpomasterBean.getId());
						lpoitemsDao.deleteLPONumber(deleteLpoItems.getLponumber());
						redirect.addFlashAttribute("msg", "Record Updated Successfully");
						redirect.addFlashAttribute("cssMsg", "warning");
					}
					else
					{
						objRequest.setAttribute("msg", "Already Record Exist");
						objRequest.setAttribute("cssMsg", "danger");
						redirect.addFlashAttribute("msg", "Already Record Exist");
						redirect.addFlashAttribute("cssMsg", "danger");
						return "lpoHome"; 
					}
				}
				if(lpomasterBean.getId() == 0 && lpomaster == null)
				{
					lpomasterBean.setStatus("1");
					lpomasterDao.save(lpomasterBean);
					lpomasterDao.updateLPONumber();
					 lpo = lpomasterDao.getById(lpomasterBean.getId());
					redirect.addFlashAttribute("msg", "Record Inserted Successfully");
					redirect.addFlashAttribute("cssMsg", "success");
				}
				if(lpomasterBean.getId() == 0 && lpomaster != null)
				{
					objRequest.setAttribute("msg", "Already Record Exist");
					objRequest.setAttribute("cssMsg", "danger");
					redirect.addFlashAttribute("msg", "Already Record Exist");
					redirect.addFlashAttribute("cssMsg", "danger");
					return "lpoHome"; 
				}
				for(int i=0; i<unit.length; i++)
				{
					objLpoitemsBean= new LpoitemsBean();
					objLpoitemsBean.setLponumber(lpo.getLponumber());
					if(unit != null && unit.length != 0){
						objLpoitemsBean.setQuantity(unit[i]);
					}
					if(rate != null && rate.length != 0){
						objLpoitemsBean.setPrice(rate[i]);
					}
					if(totalvalue != null && totalvalue.length != 0){
						objLpoitemsBean.setTotalprice(totalvalue[i]);
					}
					if(discount != null && discount.length != 0){
						objLpoitemsBean.setDiscount(discount[i]);
					}
					if(taxable != null && taxable.length != 0){
						objLpoitemsBean.setGrandtotal(taxable[i]);
					}
					if(item != null && item.length != 0){
						objLpoitemsBean.setItemid(item[i]);
					}
					if(expirydate != null && expirydate.length != 0){
						objLpoitemsBean.setExpirydate(expirydate[i]);
					}
					if(manufacturingdate != null && manufacturingdate.length != 0){
						objLpoitemsBean.setManufacturingdate(manufacturingdate[i]);
					}
					
					lpoitemsDao.save(objLpoitemsBean);
				}
				lpomasterDao.updateLPONumber();
				
			} catch (Exception e) {
				System.out.println("Exception in Product Controller in productSave()");
				e.printStackTrace();
			}
			return "redirect:lpoHome?lpoNum="+lpo.getLponumber();
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
			  sJson=lpomasterDao.getAllCustomer("1");
			  System.out.println("deleted cusmer data--"+sJson);
				
			}
		
		
		return sJson;
	}
	@RequestMapping(value = "/getLPOdetails")
	public @ResponseBody String getLPOdetails( LpomasterBean lpomasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<LpomasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(StringUtils.isNotBlank(lpomasterBean.getItem())){
				
			
			listOrderBeans = lpomasterDao.getLPOdetails(lpomasterBean);
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
			Map resultMap=lpomasterDao.getCountFromLPOItemsAndCylindermaster(lpomasterBean);
			jsonObj.put("retlist", resultMap.get("result"));
			jsonObj.put("count", resultMap.get("count"));
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
	@RequestMapping(value = "/viewLPOdetails")
	public @ResponseBody String viewLPOdetails( LpomasterBean lpomasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("viewLPOdetails  page...");
		List<LpoitemsBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(StringUtils.isNotBlank(lpomasterBean.getLponumber() )){
				
			
			listOrderBeans = lpomasterDao.viewLPOdetails(lpomasterBean);
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
	
	@ModelAttribute("items")
	public Map<Integer, String> populateCity() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,name from items where status='1' ";
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
	@RequestMapping(value = "/inActiveLPO")
	public @ResponseBody String inActiveLPO(@RequestParam("status") String status) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		Boolean itemsmasterBean=null;
		List<ItemsBean> items=null;
		ObjectMapper objectMapper = null;
		sJson=sJson=lpomasterDao.getAllCustomer(status);
		 /// System.out.println("inActiveItem data--"+sJson);
			 /// System.out.println("inActiveItem data--"+sJson);
				
		
		
		return sJson;
	}
	
	
}
