/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author seb
 */
public class SelectedFilmModel {

  Integer id = 0;

  public SelectedFilmModel(Integer id) {
    this.id = id;

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public Boolean noNull() {
    return this.id != 0;
  }

}
