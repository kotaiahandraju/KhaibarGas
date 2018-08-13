/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CompanymasterBean;
import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.SecuredepositBean;
import com.aurospaces.neighbourhood.db.dao.CompanymasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.aurospaces.neighbourhood.db.dao.SecuredepositDao;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kotaiah
 *
 */
@Controller
@RequestMapping(value="admin")
public class SecuredepositController {
	@Autowired SecuredepositDao securedepositDao;
	@Autowired LpomasterDao lpomasterDao;
	@Autowired CompanymasterDao companymasterDao;
	@Autowired CylindermasterDao cylindermasterDao;
	@RequestMapping(value = "/securedeposit")
	public String accessoriesHome(@ModelAttribute("securedepositForm")SecuredepositBean securedepositBean, HttpServletRequest request,
			HttpSession session) {
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<SecuredepositBean> list=null;
		try {
			
			list=securedepositDao.getAllData();
			if(list !=null){
				objectMapper =new ObjectMapper();
				 sJson=objectMapper.writeValueAsString(list);
				 request.setAttribute("allObjects", sJson);
			}else{
				
				request.setAttribute("allObjects", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "securedeposit";
	}
	
	@RequestMapping(value="/saveSecurityDeposit")
	public String savePrivateCylinderFilled(@ModelAttribute("securedepositForm")SecuredepositBean securedepositBean,
			@RequestParam("unit") String[] unit, 
			@RequestParam("company1") String[] company, 
			@RequestParam("item1") String[] item, 
			HttpServletRequest request,HttpSession session) {
		try{
			if(securedepositBean.getSecurityDeposit().equals("Amount")){
				securedepositDao.save(securedepositBean);
			}
			if(securedepositBean.getSecurityDeposit().equals("Cylinder")){
				for(int i=0;i<item.length;i++){
					securedepositBean.setQuantity(unit[i]);
					securedepositBean.setItemId(item[i]);
					securedepositBean.setCompanyId(company[i]);
					securedepositBean.setAmount("");
					securedepositDao.save(securedepositBean);
					for(int j=0;j<Integer.parseInt(unit[i]);j++){
						CylindermasterBean cylindermasterBean = new CylindermasterBean();
						cylindermasterBean.setStore("100");
						cylindermasterBean.setSize(item[i]);
						cylindermasterBean.setCylinderstatus("8");
						cylindermasterBean.setLponumber("LPO1001000");
						cylindermasterBean.setColor("Red");
						cylindermasterBean.setStatus("1");
						cylindermasterBean.setOwnercompany(securedepositBean.getCompanyId());
						cylindermasterDao.save(cylindermasterBean);
					}
					
				}
				cylindermasterDao.updateCylinderIds();
			}
			System.out.println("security deposit ");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:securedeposit";
	}
	
	@RequestMapping(value = "/deleteSecurity")
	public @ResponseBody String deleteSecurity(CylindermasterBean objCylindermasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		System.out.println("deleteCylinder page...");
		List<CylindermasterBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {
			if (objCylindermasterBean.getId() != 0 && objCylindermasterBean.getStatus() != "") {
				delete = cylindermasterDao.deleteCylinder(objCylindermasterBean.getId(),objCylindermasterBean.getStatus());
				if (delete) {
					jsonObj.put("message", "deleted");
				} else {
					jsonObj.put("message", "delete fail");
				}
			}

			listOrderBeans = cylindermasterDao.getCylinders("1");
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
			jsonObj.put("message", "excetption" + e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}

	@ModelAttribute("items")
	public Map<Integer, String> populateCity() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,name from items where status='1' and itemType='Cylinder' ";
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
	@ModelAttribute("companys")
	public Map<Integer, String> populatecompanys() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id ,companyname from companymaster where status='1' and typeofcompany not in ('Owner') ";
			List<CompanymasterBean> list = companymasterDao.populate(sSql);
			for (CompanymasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getCompanyname());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
}
