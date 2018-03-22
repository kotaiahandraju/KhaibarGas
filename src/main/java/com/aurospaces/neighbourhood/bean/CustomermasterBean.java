package com.aurospaces.neighbourhood.bean;


import java.util.Date;

public class CustomermasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String customerid ;
protected String customername ;
protected String customeraddress ;
protected String mobile ;
protected String landline ;
protected String authorizedperson ;
protected String contactperson ;
protected String customertype ;
protected String status ;
protected String custStatus,cylinderId,netAmount,payedAmount,dueAmount,previousDueAmount,invoiceId,securityDeposit,securityAmount;



public String getSecurityAmount() {
	return securityAmount;
}
public void setSecurityAmount(String securityAmount) {
	this.securityAmount = securityAmount;
}
public String getSecurityDeposit() {
	return securityDeposit;
}
public void setSecurityDeposit(String securityDeposit) {
	this.securityDeposit = securityDeposit;
}
public String getInvoiceId() {
	return invoiceId;
}
public void setInvoiceId(String invoiceId) {
	this.invoiceId = invoiceId;
}
public String getPreviousDueAmount() {
	return previousDueAmount;
}
public void setPreviousDueAmount(String previousDueAmount) {
	this.previousDueAmount = previousDueAmount;
}
public String getNetAmount() {
	return netAmount;
}
public void setNetAmount(String netAmount) {
	this.netAmount = netAmount;
}
public String getPayedAmount() {
	return payedAmount;
}
public void setPayedAmount(String payedAmount) {
	this.payedAmount = payedAmount;
}
public String getDueAmount() {
	return dueAmount;
}
public void setDueAmount(String dueAmount) {
	this.dueAmount = dueAmount;
}
public String getCylinderId() {
	return cylinderId;
}
public void setCylinderId(String cylinderId) {
	this.cylinderId = cylinderId;
}
public String getCustStatus() {
	return custStatus;
}
public void setCustStatus(String custStatus) {
	this.custStatus = custStatus;
}
public int getId()
{
  return id;
}
public void setId(final int id)
{
  this.id = id;
}
public Date getCreatedTime()
{
  return createdTime;
}
public void setCreatedTime(final Date createdTime)
{
  this.createdTime = createdTime;
}
public Date getUpdatedTime()
{
  return updatedTime;
}
public void setUpdatedTime(final Date updatedTime)
{
  this.updatedTime = updatedTime;
}
public String getCustomerid()
{
  return customerid;
}
public void setCustomerid(final String customerid)
{
  this.customerid = customerid;
}
public String getCustomername()
{
  return customername;
}
public void setCustomername(final String customername)
{
  this.customername = customername;
}
public String getCustomeraddress()
{
  return customeraddress;
}
public void setCustomeraddress(final String customeraddress)
{
  this.customeraddress = customeraddress;
}
public String getMobile()
{
  return mobile;
}
public void setMobile(final String mobile)
{
  this.mobile = mobile;
}
public String getLandline()
{
  return landline;
}
public void setLandline(final String landline)
{
  this.landline = landline;
}
public String getAuthorizedperson()
{
  return authorizedperson;
}
public void setAuthorizedperson(final String authorizedperson)
{
  this.authorizedperson = authorizedperson;
}
public String getContactperson()
{
  return contactperson;
}
public void setContactperson(final String contactperson)
{
  this.contactperson = contactperson;
}
public String getCustomertype()
{
  return customertype;
}
public void setCustomertype(final String customertype)
{
  this.customertype = customertype;
}
public String getStatus()
{
  return status;
}
public void setStatus(final String status)
{
  this.status = status;
}

}