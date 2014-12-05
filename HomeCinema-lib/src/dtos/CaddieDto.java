/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author titou
 */
public class CaddieDto implements Serializable {

  public List<ProductDto> films;

  public List<ProductDto> getFilms() {
    return films;
  }

  public void setFilms(List<ProductDto> films) {
    this.films = films;
  }

}
