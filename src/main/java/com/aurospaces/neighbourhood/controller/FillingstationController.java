package com.aurospaces.neighbourhood.controller;

import java.util.Date;
import java.util.List;

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

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class FillingstationController {
	private Logger logger = Logger.getLogger(FillingstationController.class);
	@Autowired
	FillingstationmasterDao fillingstationmasterDao;
	
	@RequestMapping(value = "/fillingStationHome")
	public String fillingStationHome(@Valid @ModelAttribute("fillingStationForm") FillingstationmasterBean objFillingstationmasterBean, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
	  logger.info("hi");
		
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<FillingstationmasterBean> listOrderBeans = null;
		try {
			listOrderBeans =fillingstationmasterDao.getFillingStationAllData();
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
		return "fillingStationHome";
	}
	
	

	
	@RequestMapping(value = "/addfillingstation", method = RequestMethod.POST)
	public String addCylinder(@Valid @ModelAttribute("fillingStationForm") FillingstationmasterBean objFillingstationmasterBean,
			BindingResult bindingresults, Model model,RedirectAttributes redir) {
		
		//List<CylindermasterBean> cylinderMaster=null;
		
		int id = 0;
		
		try
		{
			
			objFillingstationmasterBean.setStatus("1");
			FillingstationmasterBean fillingstationmasterBean = fillingstationmasterDao.getByFillingstationById(objFillingstationmasterBean);
			int dummyId =0;
			if(fillingstationmasterBean != null){
				dummyId = fillingstationmasterBean.getId();
			}
			if(objFillingstationmasterBean.getId() != 0)
			{
				id = objFillingstationmasterBean.getId();
				if(id == dummyId || fillingstationmasterBean == null )
				{
					fillingstationmasterDao.save(objFillingstationmasterBean);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");
				}
				else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
			}
			if(objFillingstationmasterBean.getId() == 0 && fillingstationmasterBean == null)
			{
				fillingstationmasterDao.save(objFillingstationmasterBean);
				
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}
			if(objFillingstationmasterBean.getId() == 0 && fillingstationmasterBean != null)
			{
				redir.addFlashAttribute("msg", "Already Record Exist");
				redir.addFlashAttribute("cssMsg", "danger");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:fillingStationHome";
	}	

	@RequestMapping(value = "/deletefillingstation")
	public @ResponseBody String deleteEducation( CylindermasterBean objCylindermasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<FillingstationmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objCylindermasterBean.getId() != 0){
 				delete = fillingstationmasterDao.deleteFillingStationData(objCylindermasterBean.getId(),objCylindermasterBean.getStatus());
 				if(delete){
 					jsonObj.put("message", "deleted");
 				}else{
 					jsonObj.put("message", "delete fail");
 				}
 			}
 				
			listOrderBeans = fillingstationmasterDao.getFillingStationAllData();
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
