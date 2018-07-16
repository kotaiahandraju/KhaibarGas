package com.aurospaces.neighbourhood.bean;


import java.util.Date;

public class Expensetracker 
{


protected int id = 0;

protected String accountHead,amount ;

protected String dateOfExpense ;

protected String itemDescription ;

protected String paymentType ;

protected String paymentRemarks ;

protected Date createdTime ;

protected Date updatedTime ;
protected String status ;
protected String trackrstatus,fromDate,toDate,month ,customerType,fillingStationId,customertype,customerId,gasType,items;



public String getItems() {
	return items;
}
public void setItems(String items) {
	this.items = items;
}
public String getGasType() {
	return gasType;
}
public void setGasType(String gasType) {
	this.gasType = gasType;
}
public String getCustomertype() {
	return customertype;
}
public void setCustomertype(String customertype) {
	this.customertype = customertype;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}
public String getFillingStationId() {
	return fillingStationId;
}
public void setFillingStationId(String fillingStationId) {
	this.fillingStationId = fillingStationId;
}
public String getCustomerType() {
	return customerType;
}
public void setCustomerType(String customerType) {
	this.customerType = customerType;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getTrackrstatus() {
	return trackrstatus;
}
public void setTrackrstatus(String trackrstatus) {
	this.trackrstatus = trackrstatus;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getId()
{
  return id;
}
public void setId(final int id)
{
  this.id = id;
}
public String getAccountHead()
{
  return accountHead;
}
public void setAccountHead(final String accountHead)
{
  this.accountHead = accountHead;
}
public String getDateOfExpense()
{
  return dateOfExpense;
}
public void setDateOfExpense(final String dateOfExpense)
{
  this.dateOfExpense = dateOfExpense;
}
public String getItemDescription()
{
  return itemDescription;
}
public void setItemDescription(final String itemDescription)
{
  this.itemDescription = itemDescription;
}
public String getPaymentType()
{
  return paymentType;
}
public void setPaymentType(final String paymentType)
{
  this.paymentType = paymentType;
}
public String getPaymentRemarks()
{
  return paymentRemarks;
}
public void setPaymentRemarks(final String paymentRemarks)
{
  this.paymentRemarks = paymentRemarks;
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

}