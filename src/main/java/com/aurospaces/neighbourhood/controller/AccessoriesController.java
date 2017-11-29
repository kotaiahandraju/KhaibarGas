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
import com.aurospaces.neighbourhood.db.dao.AccessoriesmasterDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class AccessoriesController {
	
	@Autowired
	AccessoriesmasterDao accessoriesmasterDao;
	
	
	@RequestMapping(value = "/accessoriesHome")
	public String accessoriesHome(@ModelAttribute("accessorForm") AccessoriesmasterBean accessoriesmasterBean, HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<AccessoriesmasterBean> accessories=null;
		try {
			
			accessories=accessoriesmasterDao.getAllAccessories();
			if(accessories !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(accessories);
				 System.out.println("accessory List---"+sJson);
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "accessoriesHome";
	}
	
	@RequestMapping(value = "/accessoriesSave")
	public  String accessoriesSave(@ModelAttribute("accessorForm")AccessoriesmasterBean accessoriesmasterBean, HttpSession objSession,HttpServletRequest objRequest,RedirectAttributes redirectAttributes) {
		boolean isInsert = false;
		String sJson = "";
		AccessoriesmasterBean bean=null;
		 String sProductId ="";
		try {
			
			System.out.println("accessoriesSave"+accessoriesmasterBean.getSuppliername());
			//accessoriesmasterBean.setAccessoriesstatus("1");
			
			if(accessoriesmasterBean.getId() == 0){
				accessoriesmasterBean.setStatus("1");
				redirectAttributes.addFlashAttribute("msg", "Record added successfully");
			}else{
				redirectAttributes.addFlashAttribute("msg", "Record updated successfully");
			}
			accessoriesmasterDao.save(accessoriesmasterBean);
			
		} catch (Exception e) {
			System.out.println("Exception in Product Controller in productSave()");
			e.printStackTrace();
		}
		return "redirect:accessoriesHome";
	}
	
	@RequestMapping(value = "/deleteDamage")
	public @ResponseBody String deleteDamage( @RequestParam("id") String id,@RequestParam("status") String status, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		Boolean accessoriesmasterBean=null;
		List<AccessoriesmasterBean> accessories=null;
		ObjectMapper objectMapper = null;
		  int dId=Integer.parseInt(id);
		  isDelete = accessoriesmasterDao.delete(dId,status);
		  
		  accessories=accessoriesmasterDao.getAllAccessories();
			
		  if(isDelete){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(accessories);
			}
		
		
		return sJson;
	}
	

	/*@RequestMapping(value = "/updateDamage")
	public @ResponseBody String updateDamage(@ModelAttribute Damage damage,
			@RequestParam("jsondata") JSONObject data, HttpSession objSession, HttpServletRequest objRequest) {
		boolean isupdate = false;
		String sJson = "";
		List<ProductStock> lstProductstock =null;
		 ProductStock productStock=null;
		 String sProductId ="";
		try {
			
			sProductId = data.getString("productId");
			if(sProductId !=""){
			boolean bStockAvailable = objProductStockService.checkStock( sProductId, data.getString("quantity"));
			System.out.println("bStockAvailable===="+bStockAvailable);
			if(!bStockAvailable){
				JSONObject json = new JSONObject();
				json.put("status", "ERRORS");
				json.put("message", "Product Stock is Low");
				sJson = json.toString();
				return sJson;
			}
			}
			String sDamageId = data.getString("damageId");
			damage.setDamageId(sDamageId);
			damage.setProductId(data.getString("productId"));
			damage.setQuantity(data.getString("quantity"));
			damage.setDescription(data.getString("description"));
			damage.setUpdatedBy(CommonUtils.getDate());
			damage.setUpdatedOn(CommonUtils.getDate());
			

			//updateStock
			objProductStockService.updateProductStock(sProductId,data.getString("quantity"),objSession);
			ProductStock oStock=  (ProductStock) objSession.getAttribute("sessionDamageStock");
			  String sNewStock=oStock.getNewStock();
			  String sOldStock = oStock.getOldStock();
			stockDetailsService.addStockDetails(sProductId,data.getString("quantity"), sDamageId, "Damage",sNewStock,sOldStock);
			
			
			isupdate = damageServiceImpl.updateDamage(damage);
			// System.out.println("isupdate" + isupdate);
			
			
			if (isupdate)
				sJson = damageServiceImpl.getAllDamage();
				//sJson = productStockService.getAllProductStock();
			// System.out.println("update: " + sJson);
		} catch (Exception ex) {
			System.out.println("Exception in  updatePurchaseInfo()");
			ex.printStackTrace();
		}
		return sJson;
	}

	@RequestMapping(value = "/deleteDamage")
	public @ResponseBody String deleteDamage( @RequestParam("jsondata") JSONObject data, HttpSession objSession,
			HttpServletRequest objRequest) throws JsonGenerationException, JsonMappingException, IOException {
		boolean isDelete = false;
		String sJson = "";
		List<ProductStock> lstProductstock =null;
		 ProductStock productStock=null;
		 
		String sDamageId=data.getString("damageId");
		isDelete = damageServiceImpl.deleteDamage(sDamageId);
		
		damageServiceImpl.addDeletedStock(productStock, data, lstProductstock);
		
		if (isDelete) {
			sJson = damageServiceImpl.getAllDamage();
		}
		return sJson;
	}*/
	
	
}
