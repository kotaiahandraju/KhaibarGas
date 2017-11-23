package com.aurospaces.neighbourhood.bean;


import java.util.Date;

public class FillingstationmasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String gasavailability;
protected String numberoffillingmachines ;
protected String quantity ;
protected String gascapacity;
protected String availablegas;
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
public String getGasavailability()
{
  return gasavailability;
}
public void setGasavailability(final String gasavailability)
{
  this.gasavailability = gasavailability;
}
public String getNumberoffillingmachines()
{
  return numberoffillingmachines;
}
public void setNumberoffillingmachines(final String numberoffillingmachines)
{
  this.numberoffillingmachines = numberoffillingmachines;
}
public String getQuantity()
{
  return quantity;
}
public void setQuantity(final String quantity)
{
  this.quantity = quantity;
}
public String getGascapacity()
{
  return gascapacity;
}
public void setGascapacity(final String gascapacity)
{
  this.gascapacity = gascapacity;
}
public String getAvailablegas()
{
  return availablegas;
}
public void setAvailablegas(final String availablegas)
{
  this.availablegas = availablegas;
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