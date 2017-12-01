package com.aurospaces.neighbourhood.bean;


import java.util.Date;
public class LpomasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String lponumber ;
protected String item ;
protected String remarks ;
protected String suppliername ;
protected String supplieraddress ;
protected String suppliercontactno ;
protected String supplieremail ;
protected String amount ;
protected String status ;
protected String lpoStatus ;
protected Date expiryDate ;
protected String expiryDate1 ;



public String getLpoStatus() {
	return lpoStatus;
}
public void setLpoStatus(String lpoStatus) {
	this.lpoStatus = lpoStatus;
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
public String getLponumber()
{
  return lponumber;
}
public void setLponumber(final String lponumber)
{
  this.lponumber = lponumber;
}
public String getItem()
{
  return item;
}
public void setItem(final String item)
{
  this.item = item;
}
public String getRemarks()
{
  return remarks;
}
public void setRemarks(final String remarks)
{
  this.remarks = remarks;
}
public String getSuppliername()
{
  return suppliername;
}
public void setSuppliername(final String suppliername)
{
  this.suppliername = suppliername;
}
public String getSupplieraddress()
{
  return supplieraddress;
}
public void setSupplieraddress(final String supplieraddress)
{
  this.supplieraddress = supplieraddress;
}
public String getSuppliercontactno()
{
  return suppliercontactno;
}
public void setSuppliercontactno(final String suppliercontactno)
{
  this.suppliercontactno = suppliercontactno;
}
public String getSupplieremail()
{
  return supplieremail;
}
public void setSupplieremail(final String supplieremail)
{
  this.supplieremail = supplieremail;
}
public String getAmount()
{
  return amount;
}
public void setAmount(final String amount)
{
  this.amount = amount;
}
public String getStatus()
{
  return status;
}
public void setStatus(final String status)
{
  this.status = status;
}
public Date getExpiryDate() {
	return expiryDate;
}
public void setExpiryDate(Date expiryDate) {
	this.expiryDate = expiryDate;
}
public String getExpiryDate1() {
	return expiryDate1;
}
public void setExpiryDate1(String expiryDate1) {
	this.expiryDate1 = expiryDate1;
}

}