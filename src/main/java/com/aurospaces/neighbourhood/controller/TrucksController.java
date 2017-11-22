package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.TrucksmasterBean;
import com.aurospaces.neighbourhood.db.dao.TrucksmasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping(value = "/admin")
public class TrucksController {
	@Autowired TrucksmasterDao objTrucksmasterDao;
	@RequestMapping(value = "/truckHome")
	public String LoginHome(@ModelAttribute("truckForm") TrucksmasterBean objTrucksmasterBean, ModelMap model,
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
	@RequestMapping(value = "/addTruck")
	public String addTruck(@ModelAttribute("truckForm") TrucksmasterBean objTrucksmasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session,RedirectAttributes redirect) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {
			objTrucksmasterDao.save(objTrucksmasterBean);
			redirect.addFlashAttribute("msg", "Truck Successfully Created");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:truckHome";
	}
}
