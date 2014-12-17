/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.ProductStates;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Covers;
import utils.Covers.CoversSizes;

/**
 *
 * @author seb
 */
public class ProductDto implements Serializable {

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 97 * hash + Objects.hashCode(this.id);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final ProductDto other = (ProductDto) obj;
	if (!Objects.equals(this.id, other.id)) {
	    return false;
	}
	return true;
    }

  public Long id;
  public Double price;
  public java.util.Date addDate;
  public String name;
  public ProductStates state;
  public Integer nbSales;
  public String cover;

    public String getCover() {
	return cover;
    }
    public String getCoverW92URL() {
	return Covers.getURL(cover, CoversSizes.SMALL);
    }
    
    public String getCoverW300URL() {
      	return Covers.getURL(cover, CoversSizes.MEDIUM);
    }
    
        public String getCoverW396URL() {
      	return Covers.getURL(cover, CoversSizes.MEDIUM2);
    }

    public void setCover(String cover) {
	this.cover = cover;
    }

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
  
  public String getPriceString() {
    return String.format("%.2f", price);
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  
  public String getAddDateString() {
    return this.getAddDateString("yyyy-MM-dd");
  }

  public void setAddDate(String addDate) {
    this.setAddDateString(addDate, "yyyy-MM-dd");
  }

  public String getAddDateString(String formater) {
    SimpleDateFormat df = new SimpleDateFormat(formater);
    return df.format(this.addDate);
  }

  public void setAddDateString(String s, String formater) {
    SimpleDateFormat df = new SimpleDateFormat(formater);
    try {
      this.addDate = df.parse(s);
    } catch (ParseException ex) {
      this.addDate = new Date();
      Logger.getLogger(UserDtoNoPw.class.getName()).log(Level.SEVERE, null, ex);
    }
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
