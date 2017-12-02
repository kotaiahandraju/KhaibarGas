package com.aurospaces.neighbourhood.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import java.util.Date;
import java.math.BigDecimal;





public class LpoitemsBean 
{

protected int id   = 0;

protected Date createdtime ;

protected Date updatedTime ;

protected String itemid ;

protected String lponumber ;

protected String quantity ;

protected String price ;

protected String totalprice ;

protected String discount ;

protected String grandtotal ;

public int getId()
{
  return id;
}
public void setId(final int id)
{
  this.id = id;
}
public Date getCreatedtime()
{
  return createdtime;
}
public void setCreatedtime(final Date createdtime)
{
  this.createdtime = createdtime;
}
public Date getUpdatedTime()
{
  return updatedTime;
}
public void setUpdatedTime(final Date updatedTime)
{
  this.updatedTime = updatedTime;
}
public String getItemid()
{
  return itemid;
}
public void setItemid(final String itemid)
{
  this.itemid = itemid;
}
public String getLponumber()
{
  return lponumber;
}
public void setLponumber(final String lponumber)
{
  this.lponumber = lponumber;
}
public String getQuantity()
{
  return quantity;
}
public void setQuantity(final String quantity)
{
  this.quantity = quantity;
}
public String getPrice()
{
  return price;
}
public void setPrice(final String price)
{
  this.price = price;
}
public String getTotalprice()
{
  return totalprice;
}
public void setTotalprice(final String totalprice)
{
  this.totalprice = totalprice;
}
public String getDiscount()
{
  return discount;
}
public void setDiscount(final String discount)
{
  this.discount = discount;
}
public String getGrandtotal()
{
  return grandtotal;
}
public void setGrandtotal(final String grandtotal)
{
  this.grandtotal = grandtotal;
}

}