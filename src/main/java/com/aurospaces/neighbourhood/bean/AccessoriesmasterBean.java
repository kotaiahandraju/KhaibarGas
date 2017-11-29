package com.aurospaces.neighbourhood.bean;


import java.util.Date;



public class AccessoriesmasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String typeofaccessory ;
protected String suppliername ;
protected String madein ;
protected String lponumber ;
protected String accessoriesstatus ;
protected String remarks ;
protected String status ;
//protected String accessoriesStatus ;

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
public String getTypeofaccessory()
{
  return typeofaccessory;
}
public void setTypeofaccessory(final String typeofaccessory)
{
  this.typeofaccessory = typeofaccessory;
}
public String getSuppliername()
{
  return suppliername;
}
public void setSuppliername(final String suppliername)
{
  this.suppliername = suppliername;
}
public String getMadein()
{
  return madein;
}
public void setMadein(final String madein)
{
  this.madein = madein;
}
public String getLponumber()
{
  return lponumber;
}
public void setLponumber(final String lponumber)
{
  this.lponumber = lponumber;
}
public String getAccessoriesstatus()
{
  return accessoriesstatus;
}
public void setAccessoriesstatus(final String accessoriesstatus)
{
  this.accessoriesstatus = accessoriesstatus;
}
public String getRemarks()
{
  return remarks;
}
public void setRemarks(final String remarks)
{
  this.remarks = remarks;
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