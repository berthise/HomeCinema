/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.GenreDto;
import ejbs.ManageListFilmsRemote;
import entities.Film;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managers.dtos.FilmDtoManager;

/**
 *
 * @author narcisse
 */
@Stateless
public class ManageListFilms implements ManageListFilmsRemote {

  @PersistenceContext
  public EntityManager em;

  @Override
  public List<FilmDto> findAllFilms() {

    ArrayList<FilmDto> listFilmDto = new ArrayList<>();

    Query q = em.createQuery("From Film f", Film.class);
    List<Film> listfilms = q.getResultList();
    for (Film f : listfilms) {
      FilmDto fdto = FilmDtoManager.getDto(f);
      listFilmDto.add(fdto);
    }

    return listFilmDto;
  }


  public List<FilmDto> FindFilmsB(String actorName) {
    ArrayList<FilmDto> listFilmDto = new ArrayList<>();

    Query q = em.createQuery("FROM APP.FILMS AS T1 \n"
	    + "WHERE \n"
	    + "    EXISTS \n"
	    + "    (\n"
	    + "        SELECT * FROM Persons as T2 \n"
	    + "        WHERE T1.ID_Film = T2.ID_PERSON AND  T2.FIRST_NAME='" + actorName + "' \n"
	    + "    ); ");


    List<Film> listfilms = q.getResultList();
    for (Film f : listfilms) {
      FilmDto fdto = FilmDtoManager.getDto(f);
      listFilmDto.add(fdto);
    }


    return listFilmDto;
  }

  @Override
  public List<FilmDto> FindFilmsByYear(Date year) {
    Query q = em.createQuery("SELECT * \n"
	    + "FROM APP.FILMS AS T1 \n"
	    + "WHERE \n"
	    + "    EXISTS \n"
	    + "    (\n"
	    + "        SELECT * FROM Products as T2 \n"
	    + "        WHERE T1.ID_Film = T2.ID_Product AND  T2.ADD_DATE='" + year + "' \n"
	    + "    ); ");
    List<FilmDto> listFilmDto = new ArrayList<>();
    List<Film> listfilms = q.getResultList();
    for (Film f : listfilms) {
      FilmDto fdto = FilmDtoManager.getDto(f);
      listFilmDto.add(fdto);
    }

    return listFilmDto;
  }

  @Override
  public List<FilmDto> FindFilmsByTitle(String title) {
    Query q;
    q = em.createQuery("FROM APP.FILMS AS T1 \n"
	    + "WHERE T1.TITLE='" + title + "'; ");
    
    List<FilmDto> listFilmDto = new ArrayList<>();
    List<Film> listfilms = q.getResultList();
    for (Film f : listfilms) {
      FilmDto fdto = FilmDtoManager.getDto(f);
      listFilmDto.add(fdto);
    }

    return listFilmDto;
  }

  @Override
  public List<FilmDto> FindTopN(Integer N) {

    Query q = em.createQuery("SELECT * FROM APP.FILMS ORDER BY RATING DESC LIMIT " + N + ";");

       List<FilmDto> listFilmDto = new ArrayList<>();
    List<Film> listfilms = q.getResultList();
    for (Film f : listfilms) {
      FilmDto fdto = FilmDtoManager.getDto(f);
      listFilmDto.add(fdto);
    }


    return listFilmDto;

  }

  @Override
  public List<FilmDto> FindNewFilms() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<FilmDto> FilmsOrderedByalph() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<FilmDto> FilmsOrderedByYear() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

    @Override
    public List<FilmDto> FindFilmsByActor(String actorName) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FilmDto> FindFilmsB(Long actor, Long director, List<GenreDto> lgdto, String str) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
