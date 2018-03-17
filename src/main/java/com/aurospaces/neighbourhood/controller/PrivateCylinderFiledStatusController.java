/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@RequestMapping(value = "/privateCylinderFilled")
	public String cylinderDeliver(@ModelAttribute("lpoForm") CylindertransactionBean cylindertransactionBean,
			HttpServletRequest request, HttpSession session) {
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
		try{
			KhaibarGasUtil objKhaibarGasUtil =new KhaibarGasUtil();
			String invoiceId = objKhaibarGasUtil.randNum();
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
				privateCylinderFilledPriceDao.save(printDataBean);
				
			}
			System.out.println(totalgaswant);
			if( (Integer.parseInt(fillingstationBean.getClosingBalanceGas()) < totalgaswant)){
				request.setAttribute("msg", "Gas is available in filling station : "+fillingstationBean.getClosingBalanceGas()+" KG's only");
				return "privateCylinderFilled";
			}
			fillingstationmasterDao.updateUsedGas(Integer.parseInt(cylindertransactionBean.getFillingStation()), totalgaswant);
			fillingstationmasterDao.updateClosingGas();
			FillingstationmasterBean objfillFillingstationmasterBean = fillingstationmasterDao.getById(Integer.parseInt(cylindertransactionBean.getFillingStation()));
			UsedGasBean objUsedGasBean = new UsedGasBean();
			objUsedGasBean.setFillingStationId(cylindertransactionBean.getFillingStation());
			objUsedGasBean.setGasInKgs(String.valueOf(totalgaswant));
			objUsedGasBean.setClosedgas(objfillFillingstationmasterBean.getClosingBalanceGas());
			objUsedGasBean.setFillingstationname(objfillFillingstationmasterBean.getStationname());
			usedGasDao.save(objUsedGasBean);
			if(StringUtils.isNotBlank(dueAmount)){
			customermasterDao.updateDueAmount(dueAmount, cylindertransactionBean.getCustomerId());
			}
			
			
		
			
			System.out.println("testing");
		}catch(Exception e){
			e.printStackTrace();
		}
				return "redirect:privateCylinderFilled";
		
	}
	@ModelAttribute("customers")
	public Map<Integer, String> populateCustomers() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = " select id,customername as name from customermaster where status='1' and customertype='PRIVATE' ";
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
