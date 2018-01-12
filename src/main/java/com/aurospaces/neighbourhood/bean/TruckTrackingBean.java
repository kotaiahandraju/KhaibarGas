package com.aurospaces.neighbourhood.bean;


import java.util.Date;





public class TruckTrackingBean 
{

protected int id   = 0;

protected Date createdTime ;

protected Date updatedTime ;

protected String truckId ;
protected String outDate ;

protected String location ;

protected String ReadingKM ;

protected String PetrolLevel ;

protected String driverName,truckStatus,status,trucknumber,truckTrackingStatus,tableJoinId;




public String getTableJoinId() {
	return tableJoinId;
}
public void setTableJoinId(String tableJoinId) {
	this.tableJoinId = tableJoinId;
}
public String getTruckTrackingStatus() {
	return truckTrackingStatus;
}
public void setTruckTrackingStatus(String truckTrackingStatus) {
	this.truckTrackingStatus = truckTrackingStatus;
}
public String getTrucknumber() {
	return trucknumber;
}
public void setTrucknumber(String trucknumber) {
	this.trucknumber = trucknumber;
}
public String getTruckStatus() {
	return truckStatus;
}
public void setTruckStatus(String truckStatus) {
	this.truckStatus = truckStatus;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
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
public String getTruckId()
{
  return truckId;
}
public void setTruckId(final String truckId)
{
  this.truckId = truckId;
}
public String getOutDate()
{
  return outDate;
}
public void setOutDate(final String outDate)
{
  this.outDate = outDate;
}
public String getLocation()
{
  return location;
}
public void setLocation(final String location)
{
  this.location = location;
}
public String getReadingKM()
{
  return ReadingKM;
}
public void setReadingKM(final String ReadingKM)
{
  this.ReadingKM = ReadingKM;
}
public String getPetrolLevel()
{
  return PetrolLevel;
}
public void setPetrolLevel(final String PetrolLevel)
{
  this.PetrolLevel = PetrolLevel;
}
public String getDriverName()
{
  return driverName;
}
public void setDriverName(final String driverName)
{
  this.driverName = driverName;
}

}