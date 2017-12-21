package com.aurospaces.neighbourhood.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.StaffmasterBean;
import com.aurospaces.neighbourhood.db.dao.StaffmasterDao;
import com.aurospaces.neighbourhood.util.MiscUtils;
@Controller
@RequestMapping("admin")
public class StaffMasterController {

	private Logger logger = Logger.getLogger(StaffMasterController.class);

	@Autowired
	StaffmasterDao objStaffmasterDao;

	@RequestMapping(value="/staffMaster")
	public String staffMasterHome(@ModelAttribute("staffMaster")StaffmasterBean objStaffmasterBean, ModelMap model, HttpServletRequest request,
			HttpSession session){
System.out.println("hello staff");
		
		List<StaffmasterBean> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		try {

			listOrderBeans = objStaffmasterDao.getAllStaffmasterDetails();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in TariffMasterController class in tariffMasterHome method");
		}


		return "staffMasterHome";
	}

	@RequestMapping(value="/saveStaffDetails")
	public String saveStaffDetails( @RequestParam("file") MultipartFile file, @ModelAttribute("staffMaster")StaffmasterBean objStaffmasterBean, ModelMap model,BindingResult result, HttpServletRequest request,
			HttpSession session,RedirectAttributes redir)
	{
		

		System.out.println("saving staffDetails page..."+file.getOriginalFilename()+"----------requestImage------"+objStaffmasterBean.getImagePath());
		String name=null;
		String sTomcatRootPath = null;
		String sDirPath = null;
		String filepath = null;
		try{
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				name =file.getOriginalFilename();
				int n=name.lastIndexOf(".");
				String ext1 = FilenameUtils.getExtension(name);
				filepath= MiscUtils.generateRandomString(5)+"."+ext1;
				//filepath= name+file.getContentType();
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				File dir = new File(rootPath + File.separator + "documents");
				if (!dir.exists()) {
					dir.mkdirs();
				}

				File serverFile = new File(dir.getAbsolutePath() + File.separator + filepath);
				try {
					try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
						int i;
						while ((i = is.read()) != -1) {
							stream.write(i);
						}
						stream.flush();
					}
				} catch (IOException e) {
					System.out.println("error : " + e);
				}
				filepath= "documents/"+filepath;
				objStaffmasterBean.setDocuments(filepath);
				sTomcatRootPath = System.getProperty("catalina.base");
				sDirPath = sTomcatRootPath + File.separator + "webapps"+ File.separator + "documents" ;
				System.out.println(sDirPath);
				File file1 = new File(sDirPath);
				file.transferTo(file1);
			}
		
		int id = 0; 
		//int name = null;
		StaffmasterBean existModel=null;
		boolean isUpdate=false;

			/*if (result.hasErrors()) {
//				model.addAttribute("newUser", userObj);
				return "staffMasterHome";
			}*/
			objStaffmasterBean.setStatus("1");
			List<StaffmasterBean> staffMasterBean = objStaffmasterDao.getByName(objStaffmasterBean);
			
			
			if(staffMasterBean.size() == 0 || staffMasterBean == null)
			{
				objStaffmasterDao.save(objStaffmasterBean);
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}
			else
			{
				for(int i=0;i<staffMasterBean.size();i++)
				{
					existModel = staffMasterBean.get(i);
					//name = existModel.getId();
					int exId = existModel.getId();
					if(exId == objStaffmasterBean.getId())
					{
						 isUpdate=true;
					}
					else
					{
						redir.addFlashAttribute("msg", "Already Record Exist");
						redir.addFlashAttribute("cssMsg", "danger");
					}
				}
				if(isUpdate){
					//System.out.println("---------filePath------"+objStaffmasterBean.getDocuments());
					if(objStaffmasterBean.getDocuments() == "" || objStaffmasterBean.getDocuments() == null){
						objStaffmasterBean.setDocuments(objStaffmasterBean.getImagePath());
						//System.out.println("---------setImagepath------");
					}
					objStaffmasterDao.save(objStaffmasterBean);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error(e);
			logger.fatal("error in StaffMasterController class addStaffMasterDetails method");
			redir.addFlashAttribute("msg", e);
		}
		return "redirect:staffMaster";
		}
	
	
	@RequestMapping(value = "/deleteStaffMasterDetails")
	public @ResponseBody String deleteStaffMasterDetails( StaffmasterBean objStaffmasterBean,ModelMap model,HttpServletRequest request,HttpSession session,BindingResult objBindingResult,RedirectAttributes redir) {
		System.out.println("deleteStaffMasterDetails page...");
		List<StaffmasterBean> listOrderBeans  = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson=null;
		boolean delete = false;
		try{
			if(objStaffmasterBean.getId() != 0 && objStaffmasterBean.getStatus() !=""){
 				delete = objStaffmasterDao.delete(objStaffmasterBean.getId(),objStaffmasterBean.getStatus());
 				if(delete){
 					jsonObj.put("message", "Record Deleted Successfully");
 					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "danger");
 				}else{
 					jsonObj.put("message", "Failed to Delete..!");
 				}
 			}
 			listOrderBeans = objStaffmasterDao.getAllStaffmasterDetails();
			objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) 
			{
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
			//System.out.println(e);
			logger.error(e);
			logger.fatal("error in StaffMasterController class deleteStaffMasterDetails method");
			jsonObj.put("message", "excetption"+e);
			return String.valueOf(jsonObj);
		}
		return String.valueOf(jsonObj);
	}

}
