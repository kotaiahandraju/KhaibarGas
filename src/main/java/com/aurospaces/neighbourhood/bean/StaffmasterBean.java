package com.aurospaces.neighbourhood.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import java.util.Date;
import java.math.BigDecimal;





public class StaffmasterBean 
{

protected int id   = 0;

protected Date createdTime ;

protected Date updatedTime ;

protected String staffcode ;

protected String staffno ;

protected String designation ;

protected String nationality ;

protected String firstname ;

protected String lastname ;

protected String mobile ;

protected String customertype ;

protected String documents ;

protected String active ;

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
public String getStaffcode()
{
  return staffcode;
}
public void setStaffcode(final String staffcode)
{
  this.staffcode = staffcode;
}
public String getStaffno()
{
  return staffno;
}
public void setStaffno(final String staffno)
{
  this.staffno = staffno;
}
public String getDesignation()
{
  return designation;
}
public void setDesignation(final String designation)
{
  this.designation = designation;
}
public String getNationality()
{
  return nationality;
}
public void setNationality(final String nationality)
{
  this.nationality = nationality;
}
public String getFirstname()
{
  return firstname;
}
public void setFirstname(final String firstname)
{
  this.firstname = firstname;
}
public String getLastname()
{
  return lastname;
}
public void setLastname(final String lastname)
{
  this.lastname = lastname;
}
public String getMobile()
{
  return mobile;
}
public void setMobile(final String mobile)
{
  this.mobile = mobile;
}
public String getCustomertype()
{
  return customertype;
}
public void setCustomertype(final String customertype)
{
  this.customertype = customertype;
}
public String getDocuments()
{
  return documents;
}
public void setDocuments(final String documents)
{
  this.documents = documents;
}
public String getActive()
{
  return active;
}
public void setActive(final String active)
{
  this.active = active;
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