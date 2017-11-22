package com.aurospaces.neighbourhood.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import java.util.Date;
import java.math.BigDecimal;





public class StoresmasterBean 
{

protected int id   = 0;

protected Date createdTime ;

protected Date updatedTime ;

protected String storeid ;

protected String storename ;

protected String location ;

protected String status ;

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