package com.aurospaces.neighbourhood.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import java.util.Date;
import java.math.BigDecimal;



public class TrucksmasterBean 
{

protected int id   = 0;

protected Date createdTime ;

protected Date updatedTime ;

protected String trucknumber ;

protected Date registrationexpirydate ;

protected Date civildefensecardexpirydate ;

protected String servicedue ;

protected String make ;

protected String description ;

protected String capacityoftruck ;

protected String lponumber ;

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
public String getTrucknumber()
{
  return trucknumber;
}
public void setTrucknumber(final String trucknumber)
{
  this.trucknumber = trucknumber;
}
public Date getRegistrationexpirydate()
{
  return registrationexpirydate;
}
public void setRegistrationexpirydate(final Date registrationexpirydate)
{
  this.registrationexpirydate = registrationexpirydate;
}
public Date getCivildefensecardexpirydate()
{
  return civildefensecardexpirydate;
}
public void setCivildefensecardexpirydate(final Date civildefensecardexpirydate)
{
  this.civildefensecardexpirydate = civildefensecardexpirydate;
}
public String getServicedue()
{
  return servicedue;
}
public void setServicedue(final String servicedue)
{
  this.servicedue = servicedue;
}
public String getMake()
{
  return make;
}
public void setMake(final String make)
{
  this.make = make;
}
public String getDescription()
{
  return description;
}
public void setDescription(final String description)
{
  this.description = description;
}
public String getCapacityoftruck()
{
  return capacityoftruck;
}
public void setCapacityoftruck(final String capacityoftruck)
{
  this.capacityoftruck = capacityoftruck;
}
public String getLponumber()
{
  return lponumber;
}
public void setLponumber(final String lponumber)
{
  this.lponumber = lponumber;
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