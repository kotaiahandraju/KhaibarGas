package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.CylindertransactionBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.TariffmasterBean;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindertransactionDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;

@Controller
@RequestMapping("admin")
public class TransactionController {
	@Autowired CylindermasterDao cylindermasterDao;
	@Autowired FillingstationmasterDao fillingstationmasterDao;
	@Autowired CylindertransactionDao cylindertransactionDao;
	private Logger logger = Logger.getLogger(TransactionController.class);
	
	@RequestMapping(value = "/cylinderMovetofillingStation")
	public String cylinderMovetofillingStation( @ModelAttribute("fillingStationForm") FillingstationmasterBean fillingstationmasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			listOrderBeans = cylindermasterDao.getEmptyCylinders();
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
	
	@RequestMapping(value="/updateCylinderStatus")
	public @ResponseBody String tariffMasterHome(FillingstationmasterBean fillingstationmasterBean, ModelMap model, HttpServletRequest request,HttpSession session){
    JSONObject objJson = new JSONObject();
    CylindertransactionBean cylindertransactionBean =null;
		try {
			String fillingStation =request.getParameter("fillingStation");
			String cylenderId =request.getParameter("cylenderId");
			String cylinderStatus =request.getParameter("cylinderStatus");
			String[] cylenderId1 =cylenderId.split(",");
			for(int i=0;i<cylenderId1.length;i++){
			cylindertransactionBean = new CylindertransactionBean();
			cylindertransactionBean.setCylinderStatus(cylinderStatus);
			cylindertransactionBean.setFillingStation(fillingStation);
			cylindertransactionBean.setCylindetId(cylenderId1[i]);
			cylindertransactionDao.save(cylindertransactionBean);
			cylindermasterDao.updateCylinderStatus(cylenderId1[i], cylinderStatus);
			objJson.put("msg", "Updated");
			}
			
		} catch (Exception e) {
			objJson.put("msg", e);
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TransactionController class in updateCylinderStatus method");
		}


		return "tariffMasterHome";
	}
	
	@ModelAttribute("fillingstation")
	public Map<Integer, String> populateCity() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,stationname from fillingstationmaster";
			List<FillingstationmasterBean> list = fillingstationmasterDao.populate(sSql);
			for (FillingstationmasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getStationname());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
}
