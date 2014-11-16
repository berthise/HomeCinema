/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.FilmDto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author narcisse
 */
@ManagedBean
@ViewScoped
public class ListFilmBean {

  private List<FilmDto> listFilmDto = new ArrayList<>();

  public List<FilmDto> getAllFilms() {

    listFilmDto.clear();
    listFilmDto = Ejbs.films().findAllFilms();
    return listFilmDto;
  }

  public List<FilmDto> getAllFilmsByActor(String actor) {

    listFilmDto.clear();
    listFilmDto = Ejbs.films().FindFilmsByActor(actor);
    return listFilmDto;
  }

  public List<FilmDto> getAllFilmsByTitle(String title) {

    listFilmDto.clear();
    listFilmDto = Ejbs.films().FindFilmsByActor(title);
    return listFilmDto;

  }
}
