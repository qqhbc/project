package com.yc.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="t_category")
public class Category
{
  private int id;
  private String text;
  private String description;
  private int parentId;
  private boolean leaf = true;
  @SuppressWarnings({ "unchecked", "rawtypes" })
private Set<Product> products = new HashSet();
  
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
  
  public String getText()
  {
    return this.text;
  }
  
  public void setText(String text)
  {
    this.text = text;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public int getParentId()
  {
    return this.parentId;
  }
  
  public void setParentId(int parentId)
  {
    this.parentId = parentId;
  }
  
  public boolean isLeaf()
  {
    return this.leaf;
  }
  
  public void setLeaf(boolean leaf)
  {
    this.leaf = leaf;
  }
  
  @OneToMany(mappedBy="category")
  @Cascade({org.hibernate.annotations.CascadeType.ALL})
  public Set<Product> getProducts()
  {
    return this.products;
  }
  
  public void setProducts(Set<Product> products)
  {
    this.products = products;
  }
}