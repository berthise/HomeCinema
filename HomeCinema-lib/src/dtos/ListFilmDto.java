/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author narcisse
 */
public class ListFilmDto {

  public ArrayList<FilmDto> listFilmDto = new ArrayList<>();

  public ArrayList<FilmDto> getListFilmDto() {
    return listFilmDto;
  }

  public void setListFilmDto(ArrayList<FilmDto> listFilmDto) {
    this.listFilmDto = listFilmDto;
  }

}
