package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
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
			BindingResult bindingresults, Model model,RedirectAttributes redirect) {
		
		List<CylindermasterBean> cylinderMaster=null;

		try
        {
        
		String cyId=objCylindermasterBean.getCylinderid();
		cylinderMaster=cylindermasterDao.getByCylinderId(cyId);
		
		if(cylinderMaster.size() ==0 || cylinderMaster ==null){

			objCylindermasterBean.setStatus("1");
	      cylindermasterDao.save(objCylindermasterBean);
	      redirect.addFlashAttribute("msg", "Updated");
		}
		else{
			System.out.println("data already exit");
			redirect.addFlashAttribute("msg", "AlreadyExist");
		}
			model.addAttribute("cylinder", new CylindermasterBean());
			// List<CylindermasterBean> list =cylindermasterDao.getCylinders();
			// model.addAttribute("cylinders",
			// cylindermasterDao.getCylinders());

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

}
