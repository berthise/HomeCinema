/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.ProductStates;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author seb
 */
public class ProductDto implements Serializable {

  public Long id;
  public Double price;
  public java.util.Date addDate;
  public String name;
  public ProductStates state;
  public Integer nbSales;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductStates getState() {
    return state;
  }

  public void setState(ProductStates state) {
    this.state = state;
  }

  public Integer getNbSales() {
    return nbSales;
  }

  public void setNbSales(Integer nbSales) {
    this.nbSales = nbSales;
  }

}
