package com.yc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_saleitem")
public class SaleItem
{
  private int id;
  private int productId;
  private String productName;
  private double price;
  private int count;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public int getProductId()
  {
    return this.productId;
  }
  
  public void setProductId(int productId)
  {
    this.productId = productId;
  }
  
  public String getProductName()
  {
    return this.productName;
  }
  
  public void setProductName(String productName)
  {
    this.productName = productName;
  }
  
  public double getPrice()
  {
    return this.price;
  }
  
  public void setPrice(double price)
  {
    this.price = price;
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public void setCount(int count)
  {
    this.count = count;
  }
}