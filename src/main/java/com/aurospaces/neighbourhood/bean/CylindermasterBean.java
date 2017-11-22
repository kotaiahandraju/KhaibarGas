package com.aurospaces.neighbourhood.bean;


import java.util.Date;

public class CylindermasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String cylinderid ;
protected String size ;
protected String capacity ;
protected String cylinderstatus ;
protected String customerid ;
protected String location ;
protected String lponumber ;
protected String color ;
protected String madein ;
protected Date expirydate ;
protected String expirtdate1;
protected String ownercompany ;
protected String remarks ;


public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
protected String status ;


public String getExpirtdate1() {
	return expirtdate1;
}
public void setExpirtdate1(String expirtdate1) {
	this.expirtdate1 = expirtdate1;
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
public String getCylinderid()
{
  return cylinderid;
}
public void setCylinderid(final String cylinderid)
{
  this.cylinderid = cylinderid;
}
public String getSize()
{
  return size;
}
public void setSize(final String size)
{
  this.size = size;
}
public String getCapacity()
{
  return capacity;
}
public void setCapacity(final String capacity)
{
  this.capacity = capacity;
}
public String getCylinderstatus()
{
  return cylinderstatus;
}
public void setCylinderstatus(final String cylinderstatus)
{
  this.cylinderstatus = cylinderstatus;
}
public String getCustomerid()
{
  return customerid;
}
public void setCustomerid(final String customerid)
{
  this.customerid = customerid;
}
public String getLocation()
{
  return location;
}
public void setLocation(final String location)
{
  this.location = location;
}
public String getLponumber()
{
  return lponumber;
}
public void setLponumber(final String lponumber)
{
  this.lponumber = lponumber;
}
public String getColor()
{
  return color;
}
public void setColor(final String color)
{
  this.color = color;
}
public String getMadein()
{
  return madein;
}
public void setMadein(final String madein)
{
  this.madein = madein;
}
public Date getExpirydate()
{
  return expirydate;
}
public void setExpirydate(final Date expirydate)
{
  this.expirydate = expirydate;
}
public String getOwnercompany()
{
  return ownercompany;
}
public void setOwnercompany(final String ownercompany)
{
  this.ownercompany = ownercompany;
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