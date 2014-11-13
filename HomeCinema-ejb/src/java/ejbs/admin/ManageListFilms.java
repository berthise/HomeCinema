/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import ejbs.ManageListFilmsRemote;
import entities.Film;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author narcisse
 */
@Stateless
public class ManageListFilms implements ManageListFilmsRemote {

    @PersistenceContext
    public EntityManager em;
    public ArrayList<FilmDto> listFilmDto = new ArrayList<>();

    @Override
    public List<FilmDto> findAllFilms() {

        Query q = em.createQuery("From Film f", Film.class);

        int i = 0;
        List<Film> listfilms = q.getResultList();
    
        FilmDto fdto = new FilmDto();
        while (i < listfilms.size()) {
            Film f = listfilms.get(i);
            
            fdto.title = f.getTitle();
           
            fdto.cover = f.getCoverId();
            listFilmDto.add(fdto);
            i++;
        }

        return listFilmDto;
    }

    @Override
    public List<FilmDto> FindFilmsByActor(String actorName) {
        Query q = em.createQuery("SELECT * \n"
                + "FROM APP.FILMS AS T1 \n"
                + "WHERE \n"
                + "    EXISTS \n"
                + "    (\n"
                + "        SELECT * FROM Persons as T2 \n"
                + "        WHERE T1.ID_Film = T2.ID_PERSON AND  T2.FIRST_NAME='" + actorName + "' \n"
                + "    ); ");
        // em.createQuery(
        //      "select object(o) from Films as o", Film.class);
        List<FilmDto> listFilmDto = new ArrayList<>();
        int i = 0;
        List<Film> listfilms = q.getResultList();

        FilmDto fdto = new FilmDto();
        while (i < listfilms.size()) {
            Film f = listfilms.get(i);
            fdto.id=f.getId();
            fdto.title = f.getTitle();
            fdto.overview = f.getOverview();
            fdto.cover = f.getCoverId();
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
        int i = 0;
        List<Film> listfilms = q.getResultList();

        FilmDto fdto = new FilmDto();
        while (i < listfilms.size()) {
            Film f = listfilms.get(i);
            fdto.id=f.getId();
            fdto.title = f.getTitle();
            fdto.overview = f.getOverview();
            fdto.cover = f.getCoverId();
            listFilmDto.add(fdto);
        }

        return listFilmDto;
    }

    @Override
    public List<FilmDto> FindFilmsByTitle(String title) {
        Query q = em.createQuery("SELECT * \n"
                + "FROM APP.FILMS AS T1 \n"
                + "WHERE T1.TITLE='" + title + "'; ");
        // em.createQuery(
        //      "select object(o) from Films as o", Film.class);
        List<FilmDto> listFilmDto = new ArrayList<>();
        int i = 0;
        List<Film> listfilms = q.getResultList();

        FilmDto fdto = new FilmDto();
        while (i < listfilms.size()) {
            Film f = listfilms.get(i);
            fdto.id=f.getId();
            fdto.title = f.getTitle();
            fdto.overview = f.getOverview();
            fdto.cover = f.getCoverId();
            listFilmDto.add(fdto);
        }

        return listFilmDto;
    }

    @Override
    public List<FilmDto> FindTopN(Integer N) {

        Query q = em.createQuery("SELECT * FROM APP.FILMS ORDER BY RATING DESC LIMIT " + N + ";");
        // em.createQuery(
        //      "select object(o) from Films as o", Film.class);
        List<FilmDto> listFilmDto = new ArrayList<>();
        int i = 0;
        List<Film> listfilms = q.getResultList();

        FilmDto fdto = new FilmDto();
        while (i < listfilms.size()) {
            Film f = listfilms.get(i);
            fdto.id=f.getId();
            fdto.title = f.getTitle();
            fdto.overview = f.getOverview();
            fdto.cover = f.getCoverId();
            listFilmDto.add(fdto);
            i++;
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
}
