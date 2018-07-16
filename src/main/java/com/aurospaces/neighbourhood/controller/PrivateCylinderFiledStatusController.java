/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
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
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindertransactionBean;
import com.aurospaces.neighbourhood.bean.FillingstationmasterBean;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.PrintDataBean;
import com.aurospaces.neighbourhood.bean.UsedGasBean;
import com.aurospaces.neighbourhood.db.dao.CustomermasterDao;
import com.aurospaces.neighbourhood.db.dao.FillingstationmasterDao;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.aurospaces.neighbourhood.db.dao.LpomasterDao;
import com.aurospaces.neighbourhood.db.dao.PrintDataDao;
import com.aurospaces.neighbourhood.db.dao.PrivateCylinderFilledPriceDao;
import com.aurospaces.neighbourhood.db.dao.UsedGasDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;

/**
 * @author Kotaiah
 *
 */
@Controller
@RequestMapping(value="admin")
public class PrivateCylinderFiledStatusController {
	@Autowired ServletContext objContext;
	@Autowired LpomasterDao lpomasterDao;
	@Autowired FillingstationmasterDao fillingstationmasterDao;
	@Autowired ItemsDao itemsDao;
	@Autowired UsedGasDao usedGasDao;
	@Autowired CustomermasterDao customermasterDao;
	@Autowired PrivateCylinderFilledPriceDao privateCylinderFilledPriceDao;
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired  PrintDataDao printDataDao;
	@RequestMapping(value = "/privateCylinderFilled")
	public String cylinderDeliver(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,HttpServletRequest request, HttpSession session) {
		String sJson = null;
		List<LpomasterBean> lpoList = null;
		try {
			String sPropFilePath = objContext.getRealPath("Resources" + File.separator + "DataBase.properties");
			if (StringUtils.isNotBlank(sPropFilePath)) {
				Properties objProperties = new Properties();
				InputStream objStream = new FileInputStream(sPropFilePath);
				objProperties.load(objStream);
				String vat = objProperties.getProperty("vat");
				System.out.println(vat);
				request.setAttribute("vat", vat);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "privateCylinderFiled";
	}
	
	@RequestMapping(value="/savePrivateCylinderFilled")
	public String savePrivateCylinderFilled(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,
			@RequestParam("unit") String[] unit, @RequestParam("rate") String[] rate,
			@RequestParam("totalvalue") String[] totalvalue, @RequestParam("discount") String[] discount,
			@RequestParam("item1") String[] item, @RequestParam("taxable") String[] taxable,
			HttpServletRequest request,@RequestParam(value = "previousDueAmount", required=false) String previousDueAmount,
			@RequestParam(value = "grossamount", required=false) String grossamount,
			@RequestParam(value = "vatamount", required=false) String vatamount,
			@RequestParam(value = "totalNetamount", required=false) String totalNetamount,
			@RequestParam("payedAmount") String payedAmount,
			@RequestParam("dueAmount") String dueAmount,@RequestParam("netAmount") String netAmount, @RequestParam("vat") String vat,HttpSession session) {
		int totalgaswant = 0;
		KhaibarGasUtil objKhaibarGasUtil =new KhaibarGasUtil();
		String invoiceId = objKhaibarGasUtil.randNum();
		TransactionStatus objTransStatus = null;
		TransactionDefinition objTransDef = null;
		try{
			objTransDef = new DefaultTransactionDefinition();
			objTransStatus = transactionManager.getTransaction(objTransDef);
			FillingstationmasterBean fillingstationBean =  fillingstationmasterDao.getById(Integer.parseInt(cylindertransactionBean.getFillingStation()));
			for(int i=0;i<item.length;i++){
				ItemsBean itemsBean =  itemsDao.getById(Integer.parseInt(item[i]));
				String numberOnly = itemsBean.getName().replaceAll("[^0-9]", "");
				totalgaswant = totalgaswant+ (Integer.parseInt(unit[i]) * Integer.parseInt(numberOnly));
				
				PrintDataBean printDataBean =new PrintDataBean();
				printDataBean.setItems(item[i]);
				printDataBean.setQuantity(unit[i]);
				printDataBean.setPrice(rate[i]);
				printDataBean.setTotalamount(totalvalue[i]);
				printDataBean.setDiscount(discount[i]);
				printDataBean.setNetamount(taxable[i]);
				printDataBean.setTotalnetamount(totalNetamount);
				printDataBean.setGrossamount(grossamount);
				printDataBean.setPreviousdueamount(previousDueAmount);
				printDataBean.setInvoiceid(invoiceId);
				printDataBean.setVatamount(vatamount);
				printDataBean.setDueamount(dueAmount);
				printDataBean.setPaidamount(payedAmount);
				printDataBean.setCustomerId(cylindertransactionBean.getCustomerId());
				printDataBean.setFillingstationId(cylindertransactionBean.getFillingStation());
				printDataBean.setCylinderFilledDate(cylindertransactionBean.getCylinderFilledDate());
				
				String cylinderfilleddate=	cylindertransactionBean.getCylinderFilledDate();
				Date date = null;
				if(StringUtils.isNotBlank(cylinderfilleddate)){
				 date= KhaibarGasUtil.dateFormate(cylinderfilleddate);
				 printDataBean.setCreatedTime(date);
				}
				
				privateCylinderFilledPriceDao.save(printDataBean);
				
				printDataBean =new PrintDataBean();
				printDataBean.setItems(item[i]);
				printDataBean.setQuantity(unit[i]);
				printDataBean.setPrice(rate[i]);
				printDataBean.setTotalamount(totalvalue[i]);
				printDataBean.setDiscount(discount[i]);
				printDataBean.setNetamount(taxable[i]);
				printDataBean.setTotalnetamount(totalNetamount);
				printDataBean.setGrossamount(grossamount);
				printDataBean.setPreviousdueamount(previousDueAmount);
				printDataBean.setInvoiceid(invoiceId);
				printDataBean.setVatamount(vatamount);
				printDataBean.setDueamount(dueAmount);
				printDataBean.setPaidamount(payedAmount);
				printDataBean.setCustomerId(cylindertransactionBean.getCustomerId());
			
				printDataDao.save(printDataBean);
				
			}
			System.out.println(totalgaswant);
			if( (Integer.parseInt(fillingstationBean.getClosingBalanceGas()) < totalgaswant)){
				request.setAttribute("msg", "Gas is available in filling station : "+fillingstationBean.getClosingBalanceGas()+" KG's only");
				transactionManager.rollback(objTransStatus);
				return "privateCylinderFiled";
			}
			
		
			fillingstationmasterDao.updateUsedGas(Integer.parseInt(cylindertransactionBean.getFillingStation()), totalgaswant);
			fillingstationmasterDao.updateClosingGas();
			FillingstationmasterBean objfillFillingstationmasterBean = fillingstationmasterDao.getById(Integer.parseInt(cylindertransactionBean.getFillingStation()));
			UsedGasBean objUsedGasBean = new UsedGasBean();
			objUsedGasBean.setFillingStationId(cylindertransactionBean.getFillingStation());
			objUsedGasBean.setGasInKgs(String.valueOf(totalgaswant));
			objUsedGasBean.setClosedgas(objfillFillingstationmasterBean.getClosingBalanceGas());
			objUsedGasBean.setFillingstationname(objfillFillingstationmasterBean.getStationname());
			objUsedGasBean.setCustomerType("Private");
			objUsedGasBean.setInvoiceId(invoiceId);
			String cylinderfilleddate=	cylindertransactionBean.getCylinderFilledDate();
			Date date = null;
			if(StringUtils.isNotBlank(cylinderfilleddate)){
			 date= KhaibarGasUtil.dateFormate(cylinderfilleddate);
			 objUsedGasBean.setCreatedTime(date);
			}
			usedGasDao.save(objUsedGasBean);
			if(StringUtils.isNotBlank(dueAmount)){
			customermasterDao.updateDueAmount(dueAmount, cylindertransactionBean.getCustomerId(),invoiceId);
			}
			
			
		
			transactionManager.commit(objTransStatus);
			System.out.println("testing");
		}catch(Exception e){
			e.printStackTrace();
			transactionManager.rollback(objTransStatus);
		}
				return "redirect:privateCylinderFilled?invoiceId="+invoiceId;
		
	}
	@RequestMapping(value = "/getInvoiceDataFilledCylinders")
	public @ResponseBody String getInvoiceData( ModelMap model,	HttpServletRequest request, HttpSession session) {
		List<Map<String, Object>> listOrderBeans = null;
		List<Map<String, Object>> listOrderBeans1 = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		String invoiceId = null;
		try {
			invoiceId =request.getParameter("invoiceId");
			if (StringUtils.isNotBlank(invoiceId)) {
				listOrderBeans = privateCylinderFilledPriceDao.getInvoiceData(invoiceId);
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
	@ModelAttribute("customers")
	public Map<Integer, String> populateCustomers() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,CONCAT(`customerid`,'( ',customername,' )') as name from customermaster where status='1' and customertype='PRIVATE' ";
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
	@ModelAttribute("fillingstation")
	public Map<Integer, String> populateFillingstation() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,stationname from fillingstationmaster where status='1' ";
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
