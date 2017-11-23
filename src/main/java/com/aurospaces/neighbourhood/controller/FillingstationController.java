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

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FillingstationController {
	private Logger logger = Logger.getLogger(CylinderController.class);
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
	
	
	/*@RequestMapping(value = "/addfillingstation", method = RequestMethod.POST)
	public String addCylinder(@Valid @ModelAttribute("fillingStationForm") FillingstationmasterBean objFillingstationmasterBean,BindingResult bindingresults,Model model)
    {
        try
        {
        	
		if(bindingresults.hasErrors())
		{
		return "fillingStationHome";
	    }
		
		
		objFillingstationmasterBean.setStatus("1");
		fillingstationmasterDao.save(objFillingstationmasterBean);
	      
		model.addAttribute("cylinder",new CylindermasterBean());
		//List<CylindermasterBean> list =cylindermasterDao.getCylinders();
	//	model.addAttribute("cylinders", cylindermasterDao.getCylinders());
		
		
        }catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		
		
		return "redirect:CylinderHome";
	}
	
		
	@RequestMapping(value = "/deleteCylinder")
	public @ResponseBody String deleteEducation( CylindermasterBean objCylindermasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		System.out.println("deleteEducation page...");
		List<FillingstationmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objCylindermasterBean.getId() != 0){
 				delete = fillingstationmasterDao.deleteFillingStationData(objCylindermasterBean.getId());
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
	
*/

}
