package com.aurospaces.neighbourhood.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.KhaibarUsersBean;
import com.aurospaces.neighbourhood.db.dao.KhaibarUsersDao;

@Controller
public class LoginController {
	@Autowired KhaibarUsersDao objKhaibarUsersDao;
	@RequestMapping(value = "/LoginHome")
	public String LoginHome(Map<String, Object> model1, ModelMap model, HttpServletRequest request,
			HttpSession session)  {
		System.out.println("LoginHome page...");
		KhaibarUsersBean loginBean = new KhaibarUsersBean();
		model.put("loginForm", loginBean);
		try {
			KhaibarUsersBean objuserBean = (KhaibarUsersBean) session.getAttribute("cacheUserBean");
			if (objuserBean != null) {
				int rolId =Integer.parseInt(objuserBean.getRoleId());
				if(rolId == 1 || rolId == 2 || rolId == 3 ){
					return "redirect:admin/dashboard";
				}else{
//					return "redirect:employeeHome1.htm";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "loginPage1";
	}

	@RequestMapping(value = "/loginAction")
	public  String loginAction( @ModelAttribute("loginForm") KhaibarUsersBean userObj, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse responses,RedirectAttributes redirect)	 {
		System.out.println("loginAction page...");
		KhaibarUsersBean objUserBean = null;
		JSONObject obj = new JSONObject();
		String object1 = null;
		try {
			if (result.hasErrors()) {
//				model.addAttribute("newUser", userObj);
				return "loginPage";
			}
			objUserBean = objKhaibarUsersDao.loginChecking(userObj);
			if (objUserBean != null ) {
				if(objUserBean.getRoleId().equals("1")){
					session.setAttribute("cacheUserBean", objUserBean);
					session.setAttribute("roleId", objUserBean.getRoleId());
					session.setAttribute("userName", objUserBean.getUserName());
					return "redirect:admin/dashboard";
				}
				
					
			} else {
				redirect.addFlashAttribute("msg", "Login Failed");
				return "redirect:HomePage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return null;
	}
}
