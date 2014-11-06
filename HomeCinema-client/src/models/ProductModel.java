/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dtos.ProductDto;
import enums.ProductStates;
import java.util.Date;

/**
 *
 * @author seb
 */
public class ProductModel implements Model_if {

  ProductDto pDto = null;
  FilmModel film = null;

  public ProductModel() {
    pDto = new ProductDto();
    film = null;
  }

  public ProductModel(ProductDto productDto) {
    this.pDto = productDto;
    film = null;
  }

  public void AddFilm(FilmModel film) {
    this.film = film;
  }

  public FilmModel getFilm() {
    return film;
  }

  public Long getId() {
    return pDto.id;
  }

  public void setId(Long id) {
    pDto.id = id;
  }

  public Integer getPrice() {
    return pDto.price;
  }

  public void setPrice(Integer price) {
    pDto.price = price;
  }

  public Date getAddDate() {
    return pDto.addDate;
  }

  public void setAddDate(Date addDate) {
    pDto.addDate = addDate;
  }

  public String getName() {
    return pDto.name;
  }

  public void setName(String name) {
    pDto.name = name;
  }

  public ProductStates getState() {
    return pDto.state;
  }

  public void setState(ProductStates state) {
    pDto.state = state;
  }

  public Integer getNbSales() {
    return pDto.nbSales;
  }

  public void setNbSales(Integer nbSales) {
    pDto.nbSales = nbSales;
  }

  @Override
  public void displayConsole() {
    System.out.println("Product  Id: " + this.getId());
    System.out.println("         Price: " + this.getPrice());
    System.out.println("         Name: " + this.getName());
    System.out.println("         State: " + this.getState());
    System.out.println("         Nb Sales: " + this.getNbSales());
    System.out.println("         Film: ");
    if (film != null) {
      film.displayConsole();
    } else {
      System.out.println("         Null");
    }
  }

  public void initDto() {
    this.pDto = new ProductDto();
    pDto.id = new Long(0);
    pDto.addDate = new Date();
    pDto.name = "";
    pDto.nbSales = 0;
    pDto.price = 0;
    pDto.state = ProductStates.Unactivated;
  }

  public Boolean notNull() {
    return this.pDto != null;
  }
}
