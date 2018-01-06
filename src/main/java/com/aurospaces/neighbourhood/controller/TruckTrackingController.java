package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.TruckTrackingBean;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.aurospaces.neighbourhood.db.dao.TruckTrackingDao;
import com.fasterxml.jackson.databind.ObjectMapper;




@Controller
@RequestMapping(value = "/admin")
public class TruckTrackingController {
	
	@Autowired
	ItemsDao itemsDao;
	
	private Logger logger = Logger.getLogger(TruckTrackingController.class);
	@Autowired
	TruckTrackingDao truckTrackingDao;
	@RequestMapping(value = "/TruckTrakingHome")
	public String TruckTrakingHome(@ModelAttribute("TruckTrackingForm") TruckTrackingBean truckTrackingBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<TruckTrackingBean> listOrderBeans = null;
		try {
			listOrderBeans = truckTrackingDao.getTruckTrackingAll("1");
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
		return "TruckTrakingHome";
	}
	
	
	
	@RequestMapping(value = "/addTruckTrack", method = RequestMethod.POST)
	public String addTruckTrack( @ModelAttribute("TruckTrackingForm") TruckTrackingBean truckTrackingBean,
			BindingResult bindingresults, Model model,RedirectAttributes redir) {
		
		try
		{
			
			truckTrackingBean.setStatus("1");
			
			if(truckTrackingBean.getId() == 0){
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}else{
				redir.addFlashAttribute("msg", "Record Updated Successfully");
				redir.addFlashAttribute("cssMsg", "warning");
			}
			truckTrackingDao.save(truckTrackingBean);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			redir.addFlashAttribute("msg", "Fail");

		}
		return "redirect:TruckTrakingHome";
	}	
		


	@RequestMapping(value = "/deleteTruckTracking")
	public @ResponseBody String deleteTruckTracking(TruckTrackingBean truckTrackingBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		List<TruckTrackingBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {
			if (truckTrackingBean.getId() != 0 && truckTrackingBean.getStatus() !="") {
				delete = truckTrackingDao.delete(truckTrackingBean.getId(),truckTrackingBean.getStatus());
				if (delete) {
					jsonObj.put("message", "deleted");
				} else {
					jsonObj.put("message", "delete fail");
				}
			}

			listOrderBeans = truckTrackingDao.getTruckTrackingAll("1");
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
	@RequestMapping(value = "/inActiveTruckTracking")
	public @ResponseBody String inActiveTruckTracking(TruckTrackingBean truckTrackingBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		List<TruckTrackingBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {

			listOrderBeans = truckTrackingDao.getTruckTrackingAll(truckTrackingBean.getStatus());
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

	@ModelAttribute("trucks")
	public Map<Integer, String> populateTrucks() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			List<ItemsBean> list = itemsDao.getItems();
			for (ItemsBean bean : list) {
				statesMap.put(bean.getId(), bean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
}
