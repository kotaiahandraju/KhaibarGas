package com.aurospaces.neighbourhood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.KhaibarUsersBean;
import com.aurospaces.neighbourhood.db.dao.KhaibarUsersDao;


@Controller
@RequestMapping(value = "/admin")
public class ChangepasswordController 
{
	@Autowired KhaibarUsersDao userDao;
	@RequestMapping(value = "/changePassword")
	public String getPassword(Model model) 
	{
		return "changePassword";
		
	}
	
	@RequestMapping(value = "/changePassword", method= RequestMethod.POST)
	public  String passwordUpdation( @ModelAttribute("changePassword") 
			ModelMap model, HttpServletRequest request, HttpSession session, HttpServletResponse responses,RedirectAttributes redirect)	 
	{
		KhaibarUsersBean objuserBean = (KhaibarUsersBean) session.getAttribute("cacheUserBean");

		String currentpassword=request.getParameter("password");
		
		String newpassword=request.getParameter("npassword");
		
		String confirmpassword=request.getParameter("cpassword");
		
		if(!newpassword.equals(confirmpassword) )
		{
			//System.out.println("New Password and confirm password must be equal");
			redirect.addFlashAttribute("msg", "New Password and confirm password must be equal");
			redirect.addFlashAttribute("cssMsg", "warning");
			return "redirect:changePassword";
		}
		
		if(objuserBean.getPassword().equalsIgnoreCase(currentpassword)) 
		{
			/*objuserBean.setPassword(objuserBean.getPassword());*/
			userDao.UpdatePassword(newpassword, objuserBean.getId());
			request.setAttribute("msg", "Password Updated Successfully");
			redirect.addFlashAttribute("msg", "Password Updated Successfully");
			redirect.addFlashAttribute("cssMsg", "warning");
			return "redirect:../logoutHome";
		}
		else
		{
			redirect.addFlashAttribute("msg", "You Entered Wrong Password");
			redirect.addFlashAttribute("cssMsg", "danger");
			return "redirect:changePassword";
		}	
	}
}