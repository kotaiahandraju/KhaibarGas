/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylindertransactionBean;
import com.aurospaces.neighbourhood.bean.PaymentHistoryBean;
import com.aurospaces.neighbourhood.bean.PrintDataBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.PaymentHistoryDao;
import com.aurospaces.neighbourhood.db.dao.PrintDataDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;

/**
 * @author Kotaiah
 *
 */
@Controller
@RequestMapping(value="admin")
public class DueAmountPayController {
	@Autowired PaymentHistoryDao paymentHistoryDao;
	@Autowired PrintDataDao printDataDao;
	@Autowired CustomermasterDao customermasterDao;
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@RequestMapping(value = "/dueamount")
	public String dueamount(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,HttpServletRequest request, HttpSession session) {
		try {
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "dueamountpay";
	}
	@RequestMapping(value = "/savedueamount")
	public String savedueamount(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,
			HttpServletRequest request, HttpSession session) {
		KhaibarGasUtil objKhaibarGasUtil =new KhaibarGasUtil();
		String invoiceId = objKhaibarGasUtil.randNum();
		TransactionStatus objTransStatus = null;
		TransactionDefinition objTransDef = null;
		try{
			objTransStatus = transactionManager.getTransaction(objTransDef);
		PaymentHistoryBean paymentHistoryBean = new PaymentHistoryBean();
		paymentHistoryBean.setInvoiceId(invoiceId);
		paymentHistoryBean.setDueAmount(cylindertransactionBean.getDueAmount());
		paymentHistoryBean.setPaidAmount(cylindertransactionBean.getPayedAmount());
		paymentHistoryBean.setCustomerId(cylindertransactionBean.getCustomerId());
		paymentHistoryDao.save(paymentHistoryBean);
		
		
		PrintDataBean printDataBean =new PrintDataBean();
		printDataBean.setInvoiceid(invoiceId);
		printDataBean.setDueamount(cylindertransactionBean.getDueAmount());
		printDataBean.setPaidamount(cylindertransactionBean.getPayedAmount());
		printDataBean.setCustomerId(cylindertransactionBean.getCustomerId());
		printDataBean.setPreviousdueamount(cylindertransactionBean.getPreviousDueAmount());
		printDataDao.save(printDataBean);
		customermasterDao.updateDueAmount(cylindertransactionBean.getDueAmount(), cylindertransactionBean.getCustomerId(),invoiceId);
		transactionManager.commit(objTransStatus);
		}catch(Exception e){
			transactionManager.rollback(objTransStatus);
			e.printStackTrace();
		}
		
				return "redirect:dueamount?invoiceId="+invoiceId+"&previousInvoice="+cylindertransactionBean.getPrviousInvoiceId();
		
	}
	@RequestMapping(value = "/getDueamountInvoiceData")
	public @ResponseBody String getInvoiceData( ModelMap model,	HttpServletRequest request, HttpSession session) {
		List<Map<String, Object>> listOrderBeans = null;
		List<Map<String, Object>> listOrderBeans1 = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		String invoiceId = null;
		String previousInvoice = null;
		try {
			invoiceId =request.getParameter("invoiceId");
			previousInvoice = request.getParameter("previousInvoice");
			if (StringUtils.isNotBlank(invoiceId) && StringUtils.isNotBlank(previousInvoice)) {
				listOrderBeans = customermasterDao.getDueamountInvoiceData(invoiceId);
			}
			if (StringUtils.isNotBlank(previousInvoice)) {
				listOrderBeans1 = customermasterDao.getDueamountInvoiceData(previousInvoice);
			}
			objectMapper = new ObjectMapper();
			if (listOrderBeans != null) {

				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				jsonObj.put("allOrders2", listOrderBeans1);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", "''");
				jsonObj.put("allOrders2", "''");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			jsonObj.put("message", "excetption" + e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}

	
	
}
