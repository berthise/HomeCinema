/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dtos.FilmDto;
import dtos.UserDto;

/**
 *
 * @author seb
 */
public class FilmModel {

  FilmDto filmDto = null;

  public FilmModel() {
    filmDto = new FilmDto();

  }
  
  public FilmModel(FilmDto filmDto) {
    this.filmDto = filmDto;

  }

  public String getTitle() {
    return filmDto.title;
  }

  public void setTitle(String title) {
    filmDto.title = title;
  }

  public String getOverview() {
    return filmDto.overview;
  }

  public void setOverview(String overview) {
    filmDto.overview = overview;
  }
  
}
