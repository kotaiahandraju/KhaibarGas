package com.aurospaces.neighbourhood.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.db.dao.TrucksmasterDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping(value = "/admin")
public class StaffMasterController {
	private Logger logger = Logger.getLogger(StaffMasterController.class);
	@Autowired TrucksmasterDao objTrucksmasterDao;
	@RequestMapping(value = "/staffMasterHome")
	public String staffMasterHome(@ModelAttribute("truckForm") TrucksmasterBean objTrucksmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<TrucksmasterBean> listOrderBeans = null; 
		try {
			listOrderBeans = objTrucksmasterDao.getAllTrucks();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "truckHome";
	}
	@RequestMapping(value = "/addStaffMaster")
	public String addStaffMaster(@ModelAttribute("truckForm") TrucksmasterBean objTrucksmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session,RedirectAttributes redirect) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		int id = 0;
		try {
			if(StringUtils.isNotBlank(objTrucksmasterBean.getRegistrationexpirydate1())){
				Date date=  KhaibarGasUtil.dateFormate(objTrucksmasterBean.getRegistrationexpirydate1());
				objTrucksmasterBean.setRegistrationexpirydate(date);
			}
			if(StringUtils.isNotBlank(objTrucksmasterBean.getCivildefensecardexpirydate1())){
				Date date=  KhaibarGasUtil.dateFormate(objTrucksmasterBean.getCivildefensecardexpirydate1());
				objTrucksmasterBean.setCivildefensecardexpirydate(date);
			}
			objTrucksmasterBean.setStatus("1");
			TrucksmasterBean trucksmasterBean = objTrucksmasterDao.getByName(objTrucksmasterBean);
			int dummyId =0;
			if(trucksmasterBean != null){
				dummyId = trucksmasterBean.getId();
			}
			if(objTrucksmasterBean.getId() != 0)
			{
				id = objTrucksmasterBean.getId();
				if(id == dummyId || trucksmasterBean == null )
				{
					objTrucksmasterDao.save(objTrucksmasterBean);
					redirect.addFlashAttribute("msg", "Updated");
				}
				else
				{
					redirect.addFlashAttribute("msg", "AlreadyExist");
				}
			}
			if(objTrucksmasterBean.getId() == 0 && trucksmasterBean == null)
			{
				objTrucksmasterDao.save(objTrucksmasterBean);
				redirect.addFlashAttribute("msg", "inserted");
			}
			if(objTrucksmasterBean.getId() == 0 && trucksmasterBean != null)
			{
				redirect.addFlashAttribute("msg", "AlreadyExist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:truckHome";
	}
	@RequestMapping(value = "/deleteStaffMaster")
	public @ResponseBody String deleteEducation( TrucksmasterBean objTrucksmasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<TrucksmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objTrucksmasterBean.getId() != 0){
 				delete = objTrucksmasterDao.delete(objTrucksmasterBean.getId());
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
