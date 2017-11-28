package com.aurospaces.neighbourhood.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.fasterxml.jackson.databind.ObjectMapper;




@Controller
@RequestMapping(value = "/admin")
public class CylinderController {
	
	
	private Logger logger = Logger.getLogger(CylinderController.class);
	@Autowired
	CylindermasterDao cylindermasterDao;
	@RequestMapping(value = "/CylinderHome")
	public String cylinderHome(@Valid @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			listOrderBeans = cylindermasterDao.getCylinders();
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
		return "cylinderHome";
	}
	
	@RequestMapping(value = "/addcylinder", method = RequestMethod.POST)
	public String addCylinder(@Valid @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,
			BindingResult bindingresults, Model model,RedirectAttributes redir) {
		
		//List<CylindermasterBean> cylinderMaster=null;
		
		int id = 0;
		
		try
		{
			
			if(StringUtils.isNotBlank(objCylindermasterBean.getExpirtdate1())){
				Date date=  KhaibarGasUtil.dateFormate(objCylindermasterBean.getExpirtdate1());
				objCylindermasterBean.setExpirydate(date);
			}
			objCylindermasterBean.setStatus("1");
			CylindermasterBean cylindermasterBean = cylindermasterDao.getByCylinderId(objCylindermasterBean);
			int dummyId =0;
			if(cylindermasterBean != null){
				dummyId = cylindermasterBean.getId();
			}
			if(objCylindermasterBean.getId() != 0)
			{
				id = objCylindermasterBean.getId();
				if(id == dummyId || cylindermasterBean == null )
				{
					cylindermasterDao.save(objCylindermasterBean);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");
				}
				else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
			}
			if(objCylindermasterBean.getId() == 0 && cylindermasterBean == null)
			{
				//get cylinder Capacity
				String capacity = objCylindermasterBean.getCapacity();
				//changing capcity to Id
				int capacityId = cylindermasterDao.getCylinderIdByCapacity(capacity);
				objCylindermasterBean.setCapacity(String.valueOf(capacityId));
				
				cylindermasterDao.save(objCylindermasterBean);
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}
			if(objCylindermasterBean.getId() == 0 && cylindermasterBean != null)
			{
				redir.addFlashAttribute("msg", "Already Record Exist");
				redir.addFlashAttribute("cssMsg", "danger");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:CylinderHome";
	}	
		


	@RequestMapping(value = "/deleteCylinder")
	public @ResponseBody String deleteEducation(CylindermasterBean objCylindermasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<CylindermasterBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {
			if (objCylindermasterBean.getId() != 0) {
				delete = cylindermasterDao.deleteCylinder(objCylindermasterBean.getId());
				if (delete) {
					jsonObj.put("message", "deleted");
				} else {
					jsonObj.put("message", "delete fail");
				}
			}

			listOrderBeans = cylindermasterDao.getCylinders();
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
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption" + e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}
	
<<<<<<< HEAD
=======
	
	
	@ModelAttribute
	public void addingcommonobject(Model model)
	{
		
		int cylindersCount=cylindermasterDao.getCylindersCount();
		//List<String> cylinderTypes= cylindermasterDao.getCylinderstypes();
		
		//System.out.println(cylinderTypes);
		model.addAttribute("cylindersCount",cylindersCount);
		
		
	}

>>>>>>> 5aab921032bab7a10afe3b14a53874e907282625
	@RequestMapping(value = "/cylinderMovetofillingStation")
	public String cylinderMovetofillingStation(  CylindermasterBean objCylindermasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			listOrderBeans = cylindermasterDao.getCylinders();
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
		return "movetoFillingStation";
	}
	
	
	
	@ModelAttribute("cylinderTypes")
	public Map<Integer, String> populateUsers() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			List<CylinderTypesBean> list= cylindermasterDao.getCylinderstypes();
			for(CylinderTypesBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	
	@RequestMapping("/getCylinderCapacity")
	public  @ResponseBody  String cylinderTypes(HttpServletRequest request, HttpSession session)
	{
			if(null != request.getParameter("cid"))
				return cylindermasterDao.getCylinderCapacityByID(Integer.parseInt(request.getParameter("cid")));
			else
				return "No data found";
	}
	

}
