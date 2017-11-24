package com.aurospaces.neighbourhood.bean;


import java.util.Date;


public class TrucksmasterBean 
{
protected int id   = 0;
protected Date createdTime ;
protected Date updatedTime ;
protected String trucknumber ;
protected Date registrationexpirydate ;
protected Date civildefensecardexpirydate ;
protected Date servicedue ;
protected String make ;
protected String description ;
protected String capacityoftruck ;
protected String lponumber ;
protected String status ;
protected String civildefensecardexpirydate1;
protected String registrationexpirydate1;
protected String truckStatus;
protected String typeOfService;
protected String servicedue1;


public String getServicedue1() {
	return servicedue1;
}
public void setServicedue1(String servicedue1) {
	this.servicedue1 = servicedue1;
}
public String getTypeOfService() {
	return typeOfService;
}
public void setTypeOfService(String typeOfService) {
	this.typeOfService = typeOfService;
}
public String getTruckStatus() {
	return truckStatus;
}
public void setTruckStatus(String truckStatus) {
	this.truckStatus = truckStatus;
}
public String getCivildefensecardexpirydate1() {
	return civildefensecardexpirydate1;
}
public void setCivildefensecardexpirydate1(String civildefensecardexpirydate1) {
	this.civildefensecardexpirydate1 = civildefensecardexpirydate1;
}
public String getRegistrationexpirydate1() {
	return registrationexpirydate1;
}
public void setRegistrationexpirydate1(String registrationexpirydate1) {
	this.registrationexpirydate1 = registrationexpirydate1;
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

public Date getServicedue() {
	return servicedue;
}
public void setServicedue(Date servicedue) {
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