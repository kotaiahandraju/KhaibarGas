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

protected String stationname;
protected String unitpoint;
protected String fillingStatus;
protected String name;
protected String store;
protected String cylinderType;
protected String truckId;
protected String closingBalanceGas;
protected String usedGas;


public String getClosingBalanceGas() {
	return closingBalanceGas;
}
public void setClosingBalanceGas(String closingBalanceGas) {
	this.closingBalanceGas = closingBalanceGas;
}
public String getUsedGas() {
	return usedGas;
}
public void setUsedGas(String usedGas) {
	this.usedGas = usedGas;
}
public String getTruckId() {
	return truckId;
}
public void setTruckId(String truckId) {
	this.truckId = truckId;
}
public String getCylinderType() {
	return cylinderType;
}
public void setCylinderType(String cylinderType) {
	this.cylinderType = cylinderType;
}
public String getStore() {
	return store;
}
public void setStore(String store) {
	this.store = store;
}
/*protected String openingbalance;
protected String closingbalance;
*/


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFillingStatus() {
	return fillingStatus;
}
public void setFillingStatus(String fillingStatus) {
	this.fillingStatus = fillingStatus;
}
public String getStationname() {
	return stationname;
}
public void setStationname(String stationname) {
	this.stationname = stationname;
}
public String getUnitpoint() {
	return unitpoint;
}
public void setUnitpoint(String unitpoint) {
	this.unitpoint = unitpoint;
}
/*public String getOpeningbalance() {
	return openingbalance;
}
public void setOpeningbalance(String openingbalance) {
	this.openingbalance = openingbalance;
}
public String getClosingbalance() {
	return closingbalance;
}
public void setClosingbalance(String closingbalance) {
	this.closingbalance = closingbalance;
}
*/
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