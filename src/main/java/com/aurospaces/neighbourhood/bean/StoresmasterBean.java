package com.aurospaces.neighbourhood.bean;


import java.util.Date;


public class StoresmasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String storeid ;
protected String storename ;
protected String location ;
protected String status ;
public String getStoreStatus() {
	return storeStatus;
}
public void setStoreStatus(String storeStatus) {
	this.storeStatus = storeStatus;
}
protected String storeStatus ;

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
public String getStoreid()
{
  return storeid;
}
public void setStoreid(final String storeid)
{
  this.storeid = storeid;
}
public String getStorename()
{
  return storename;
}
public void setStorename(final String storename)
{
  this.storename = storename;
}
public String getLocation()
{
  return location;
}
public void setLocation(final String location)
{
  this.location = location;
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