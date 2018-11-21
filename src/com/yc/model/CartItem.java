package com.yc.model;

public class CartItem
{
  private int productId;
  private String productName;
  private int count;
  private double price;
  
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
  
  public int getCount()
  {
    return this.count;
  }
  
  public void setCount(int count)
  {
    this.count = count;
  }
  
  public double getPrice()
  {
    return this.price;
  }
  
  public void setPrice(double price)
  {
    this.price = price;
  }
  
  public double getTotalPrice()
  {
    return this.count * this.price;
  }
}
