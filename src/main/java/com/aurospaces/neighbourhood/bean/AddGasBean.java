package com.aurospaces.neighbourhood.bean;


import java.util.Date;



public class AddGasBean 
{
protected int id   = 0;
protected Date createdTime,date ;
protected Date updatedTime ;
protected String fillingStationId ;
protected String gasInKgs,units ,x,y;

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
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getUnits() {
	return units;
}
public void setUnits(String units) {
	this.units = units;
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
public String getGasInKgs() {
	return gasInKgs;
}
public void setGasInKgs(String gasInKgs) {
	this.gasInKgs = gasInKgs;
}


}