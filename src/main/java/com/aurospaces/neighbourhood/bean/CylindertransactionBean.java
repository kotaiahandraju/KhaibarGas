package com.aurospaces.neighbourhood.bean;


import java.util.Date;


/**
 *
 * @author autogenerated
 */



public class CylindertransactionBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String cylindetId ;
protected String cylinderStatus ;
protected String createdBy,fillingStation , customerId,truckId,item,customertype,storename,vat,ownercompany,netAmount;

protected String cylinderReturnTruck;
protected String cylinderDeliverTruck;
protected String dueAmount,payedAmount,previousDueAmount,invoiceId,securityDeposit,securityAmount,cylinderFilledDate,prviousInvoiceId;



public String getPrviousInvoiceId() {
	return prviousInvoiceId;
}
public void setPrviousInvoiceId(String prviousInvoiceId) {
	this.prviousInvoiceId = prviousInvoiceId;
}
public String getCylinderFilledDate() {
	return cylinderFilledDate;
}
public void setCylinderFilledDate(String cylinderFilledDate) {
	this.cylinderFilledDate = cylinderFilledDate;
}
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
public String getPreviousDueAmount() {
	return previousDueAmount;
}
public void setPreviousDueAmount(String previousDueAmount) {
	this.previousDueAmount = previousDueAmount;
}
public String getInvoiceId() {
	return invoiceId;
}
public void setInvoiceId(String invoiceId) {
	this.invoiceId = invoiceId;
}
public String getNetAmount() {
	return netAmount;
}
public void setNetAmount(String netAmount) {
	this.netAmount = netAmount;
}
public String getDueAmount() {
	return dueAmount;
}
public void setDueAmount(String dueAmount) {
	this.dueAmount = dueAmount;
}
public String getPayedAmount() {
	return payedAmount;
}
public void setPayedAmount(String payedAmount) {
	this.payedAmount = payedAmount;
}
public String getCylinderReturnTruck() {
	return cylinderReturnTruck;
}
public void setCylinderReturnTruck(String cylinderReturnTruck) {
	this.cylinderReturnTruck = cylinderReturnTruck;
}
public String getCylinderDeliverTruck() {
	return cylinderDeliverTruck;
}
public void setCylinderDeliverTruck(String cylinderDeliverTruck) {
	this.cylinderDeliverTruck = cylinderDeliverTruck;
}
public String getOwnercompany() {
	return ownercompany;
}
public void setOwnercompany(String ownercompany) {
	this.ownercompany = ownercompany;
}
public String getVat() {
	return vat;
}
public void setVat(String vat) {
	this.vat = vat;
}
public String getStorename() {
	return storename;
}
public void setStorename(String storename) {
	this.storename = storename;
}
public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}
public String getTruckId() {
	return truckId;
}
public void setTruckId(String truckId) {
	this.truckId = truckId;
}
public int getId()
{
  return id;
}
public String getCustomertype() {
	return customertype;
}
public void setCustomertype(String customertype) {
	this.customertype = customertype;
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
public String getCylindetId()
{
  return cylindetId;
}
public void setCylindetId(final String cylindetId)
{
  this.cylindetId = cylindetId;
}
public String getCylinderStatus()
{
  return cylinderStatus;
}
public void setCylinderStatus(final String cylinderStatus)
{
  this.cylinderStatus = cylinderStatus;
}
public String getCreatedBy()
{
  return createdBy;
}
public void setCreatedBy(final String createdBy)
{
  this.createdBy = createdBy;
}
public String getFillingStation() {
	return fillingStation;
}
public void setFillingStation(String fillingStation) {
	this.fillingStation = fillingStation;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}

}