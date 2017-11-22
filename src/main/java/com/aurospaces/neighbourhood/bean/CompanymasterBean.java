package com.aurospaces.neighbourhood.bean;


import java.util.Date;


public class CompanymasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String companycode ;
protected String companyname ;
protected String contactpersonname ;
protected String contactpersonmobile ;
protected String emailid ;
protected String remarks ;
protected String typeofcompany ;
protected String customertype ;
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
public String getCompanycode()
{
  return companycode;
}
public void setCompanycode(final String companycode)
{
  this.companycode = companycode;
}
public String getCompanyname()
{
  return companyname;
}
public void setCompanyname(final String companyname)
{
  this.companyname = companyname;
}
public String getContactpersonname()
{
  return contactpersonname;
}
public void setContactpersonname(final String contactpersonname)
{
  this.contactpersonname = contactpersonname;
}
public String getContactpersonmobile()
{
  return contactpersonmobile;
}
public void setContactpersonmobile(final String contactpersonmobile)
{
  this.contactpersonmobile = contactpersonmobile;
}
public String getEmailid()
{
  return emailid;
}
public void setEmailid(final String emailid)
{
  this.emailid = emailid;
}
public String getRemarks()
{
  return remarks;
}
public void setRemarks(final String remarks)
{
  this.remarks = remarks;
}
public String getTypeofcompany()
{
  return typeofcompany;
}
public void setTypeofcompany(final String typeofcompany)
{
  this.typeofcompany = typeofcompany;
}
public String getCustomertype()
{
  return customertype;
}
public void setCustomertype(final String customertype)
{
  this.customertype = customertype;
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