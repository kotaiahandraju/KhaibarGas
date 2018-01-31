package com.aurospaces.neighbourhood.bean;


import java.util.Date;



public class UsedGasBean 
{
protected int id   = 0;
protected Date createdTime,date ;
protected Date updatedTime ;
protected String fillingStationId,closedgas,fillingstationname ;
protected String gasInKgs,units ,x,y,fromDate,toDate,month,expirtdate1,AddedGas;


public String getAddedGas() {
	return AddedGas;
}
public void setAddedGas(String addedGas) {
	AddedGas = addedGas;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Date getUpdatedTime() {
	return updatedTime;
}
public void setUpdatedTime(Date updatedTime) {
	this.updatedTime = updatedTime;
}
public String getFillingStationId() {
	return fillingStationId;
}
public void setFillingStationId(String fillingStationId) {
	this.fillingStationId = fillingStationId;
}
public String getClosedgas() {
	return closedgas;
}
public void setClosedgas(String closedgas) {
	this.closedgas = closedgas;
}
public String getFillingstationname() {
	return fillingstationname;
}
public void setFillingstationname(String fillingstationname) {
	this.fillingstationname = fillingstationname;
}
public String getGasInKgs() {
	return gasInKgs;
}
public void setGasInKgs(String gasInKgs) {
	this.gasInKgs = gasInKgs;
}
public String getUnits() {
	return units;
}
public void setUnits(String units) {
	this.units = units;
}
public String getX() {
	return x;
}
public void setX(String x) {
	this.x = x;
}
public String getY() {
	return y;
}
public void setY(String y) {
	this.y = y;
}
public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getMonth() {
	return month;
}
public void setMonth(String month) {
	this.month = month;
}
public String getExpirtdate1() {
	return expirtdate1;
}
public void setExpirtdate1(String expirtdate1) {
	this.expirtdate1 = expirtdate1;
}


}