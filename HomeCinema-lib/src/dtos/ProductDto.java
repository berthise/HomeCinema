/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.ProductStates;
import java.io.Serializable;
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
  public Integer price;
  public java.util.Date addDate;
  public String name;
  public ProductStates state;
  public Integer nbSales;
}
