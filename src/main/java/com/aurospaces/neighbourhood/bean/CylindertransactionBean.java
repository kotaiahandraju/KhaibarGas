package com.aurospaces.neighbourhood.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author autogenerated
 */



public class CylindertransactionBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String cylindetId ;
protected String cylinderStatus ;
protected String createdBy,fillingStation , customerId,truckId ;


public String getTruckId() {
	return truckId;
}
public void setTruckId(String truckId) {
	this.truckId = truckId;
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
public String getCylindetId()
{
  return cylindetId;
}
public void setCylindetId(final String cylindetId)
{
  this.cylindetId = cylindetId;
}
public String getCylinderStatus()
{
  return cylinderStatus;
}
public void setCylinderStatus(final String cylinderStatus)
{
  this.cylinderStatus = cylinderStatus;
}
public String getCreatedBy()
{
  return createdBy;
}
public void setCreatedBy(final String createdBy)
{
  this.createdBy = createdBy;
}
public String getFillingStation() {
	return fillingStation;
}
public void setFillingStation(String fillingStation) {
	this.fillingStation = fillingStation;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}

}