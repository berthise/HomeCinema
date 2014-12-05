/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import dtos.SimpleUserDto;
import ejbs.ManageFilmRemote;

import ejbs.ManageUserRemote;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class FilmsManagedBean {

  private ManageFilmRemote filmManager;

  private List<FilmDto> films;

  public FilmsManagedBean() throws NamingException {
    filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
    this.films = filmManager.getAllFilm();
  }

  public List<FilmDto> getFilms() {
    return this.films;
  }

  public void setFilms(List<FilmDto> array) {
    this.films = array;
  }

  public Integer getTotal() {
    return this.films.size();
  }



}
