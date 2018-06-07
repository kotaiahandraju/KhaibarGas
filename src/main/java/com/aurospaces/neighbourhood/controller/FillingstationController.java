package com.aurospaces.neighbourhood.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.AddGasBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.db.dao.AddGasDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="admin")
public class FillingstationController {
	private Logger logger = Logger.getLogger(FillingstationController.class);
	@Autowired
	FillingstationmasterDao fillingstationmasterDao;
	@Autowired
	CylindermasterDao cylindermasterDao;
	@Autowired AddGasDao addGasDao;
	@RequestMapping(value = "/fillingStationHome")
	public String fillingStationHome(@Valid @ModelAttribute("fillingStationForm") FillingstationmasterBean objFillingstationmasterBean, ModelMap model, HttpServletRequest request,
			HttpSession session) {
		
//		 Random ran = new Random();
//		 String id = String.format("%04d", ran.nextInt(10000));
		 
//		 objFillingstationmasterBean.setUnitpoint(id);
		
	  logger.info("hi");
		
		ObjectMapper objectMapper = null;
		String sJson = null;
		List<FillingstationmasterBean> listOrderBeans = null;
		try {
			listOrderBeans =fillingstationmasterDao.getFillingStationAllData("1");
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
		int id = 0;
		AddGasBean addGasBean=null;
		
		try
		{
			objFillingstationmasterBean.setStatus("1");
			FillingstationmasterBean fillingstationmasterBean = fillingstationmasterDao.getByFillingstationById(objFillingstationmasterBean);
			int dummyId =0;
//			addGasBean =new AddGasBean();
//			addGasBean.setFillingStationId(String.valueOf(objFillingstationmasterBean.getId()));
//			addGasBean.setGasInKgs(objFillingstationmasterBean.getGasavailability());
//			addGasBean.setClosedgas(objFillingstationmasterBean.getClosingBalanceGas());
			if(fillingstationmasterBean != null){
				dummyId = fillingstationmasterBean.getId();
			}
			if(objFillingstationmasterBean.getId() != 0)
			{
				id = objFillingstationmasterBean.getId();
				if(id == dummyId || fillingstationmasterBean == null )
				{
					fillingstationmasterDao.save(objFillingstationmasterBean);
					//addGasDao.save(addGasBean);
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
				//addGasDao.save(addGasBean);
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}
			if(objFillingstationmasterBean.getId() == 0 && fillingstationmasterBean != null)
			{
				redir.addFlashAttribute("msg", "Already Record Exist");
				redir.addFlashAttribute("cssMsg", "danger");
			}
			fillingstationmasterDao.updateClosingGas();
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
 				
			listOrderBeans = fillingstationmasterDao.getFillingStationAllData("1");
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
	@RequestMapping(value = "/inActiveFillingStation")
	public @ResponseBody String inActiveFillingStation( CylindermasterBean objCylindermasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult) {
		List<FillingstationmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
 				
			listOrderBeans = fillingstationmasterDao.getFillingStationAllData(objCylindermasterBean.getStatus());
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
	
	
	@ModelAttribute("stationnames")
	public Map<Integer, String> fillingstationmaster() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			List<FillingstationmasterBean> list= fillingstationmasterDao.getFillingStationName();
			for(FillingstationmasterBean bean: list){
				statesMap.put(bean.getId(), bean.getStationname());
				System.out.println("---list---"+bean.getId()+"--------------"+ bean.getStationname());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	
	@RequestMapping("/updateGas")
	public  @ResponseBody  String updateGas(@RequestParam("stationId") String stationId,@RequestParam("newGasavail") String newGasavail,@RequestParam("closedgas") String closedgas,@RequestParam("lponumber") String lponumber, HttpServletRequest request, HttpSession session)
	{  
		boolean retlist=false;
		//String retlist=null;
		JSONObject obj = new JSONObject();
		 AddGasBean addGasBean = new AddGasBean();
		try {
			
			System.out.println("-----gasavail-------"+newGasavail+"-------stationId------"+stationId);
				retlist=fillingstationmasterDao.updateGas(Integer.parseInt(stationId), newGasavail);
				fillingstationmasterDao.updateClosingGas();
				FillingstationmasterBean bean = fillingstationmasterDao.getById(Integer.parseInt(stationId));
				addGasBean.setFillingStationId(stationId);
				addGasBean.setGasInKgs(newGasavail);
				addGasBean.setClosedgas(closedgas);
				addGasBean.setLponumber(lponumber);
				addGasBean.setFillingstationname(bean.getStationname());
				addGasDao.save(addGasBean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return String.valueOf(obj);
	}
	
	@ModelAttribute("LPONumbers")
	public Map<String, String> populateLPONumbers() {
		Map<String, String> statesMap = new LinkedHashMap<String, String>();
		try {
			String sSql = "SELECT * FROM lpoitems lpn,items i ,lpomaster lpm WHERE lpn.itemid=i.id AND lpm.lponumber=lpn.lponumber AND  i.itemType='Gas' AND lpm.status='1' GROUP BY lpn.lponumber";
			List<LpomasterBean> list = cylindermasterDao.populate(sSql);
			for (LpomasterBean bean : list) {
				statesMap.put(bean.getLponumber(), bean.getLponumber());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
}
