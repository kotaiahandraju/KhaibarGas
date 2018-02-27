package com.aurospaces.neighbourhood.bean;


import java.util.Date;





public class LpoitemsBean 
{

protected int id   = 0;

protected Date createdtime ;

protected Date updatedTime ;

protected String itemid ;

protected String lponumber ;

protected String quantity ;

protected String price ;

protected String totalprice ;

protected String discount ;

protected String grandtotal ;
protected String suppliername ;
protected String expirydate;
protected String manufacturingdate,amount,name,supplieraddress,lpoDate;




public String getLpoDate() {
	return lpoDate;
}
public void setLpoDate(String lpoDate) {
	this.lpoDate = lpoDate;
}
public String getSupplieraddress() {
	return supplieraddress;
}
public void setSupplieraddress(String supplieraddress) {
	this.supplieraddress = supplieraddress;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getExpirydate() {
	return expirydate;
}
public void setExpirydate(String expirydate) {
	this.expirydate = expirydate;
}
public String getManufacturingdate() {
	return manufacturingdate;
}
public void setManufacturingdate(String manufacturingdate) {
	this.manufacturingdate = manufacturingdate;
}
public String getSuppliername() {
	return suppliername;
}
public void setSuppliername(String suppliername) {
	this.suppliername = suppliername;
}
public int getId()
{
  return id;
}
public void setId(final int id)
{
  this.id = id;
}
public Date getCreatedtime()
{
  return createdtime;
}
public void setCreatedtime(final Date createdtime)
{
  this.createdtime = createdtime;
}
public Date getUpdatedTime()
{
  return updatedTime;
}
public void setUpdatedTime(final Date updatedTime)
{
  this.updatedTime = updatedTime;
}
public String getItemid()
{
  return itemid;
}
public void setItemid(final String itemid)
{
  this.itemid = itemid;
}
public String getLponumber()
{
  return lponumber;
}
public void setLponumber(final String lponumber)
{
  this.lponumber = lponumber;
}
public String getQuantity()
{
  return quantity;
}
public void setQuantity(final String quantity)
{
  this.quantity = quantity;
}
public String getPrice()
{
  return price;
}
public void setPrice(final String price)
{
  this.price = price;
}
public String getTotalprice()
{
  return totalprice;
}
public void setTotalprice(final String totalprice)
{
  this.totalprice = totalprice;
}
public String getDiscount()
{
  return discount;
}
public void setDiscount(final String discount)
{
  this.discount = discount;
}
public String getGrandtotal()
{
  return grandtotal;
}
public void setGrandtotal(final String grandtotal)
{
  this.grandtotal = grandtotal;
}

}