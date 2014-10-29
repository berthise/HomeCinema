/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.FilmDtoManager;
import ejbs.ManageFilmRemote;
import entities.Film;
import javax.persistence.Query;

/**
 *
 * @author titou
 */
@Stateless
public class ManageFilm implements ManageFilmRemote {
    
    @PersistenceContext
    public EntityManager em;
    
    @Override
    public void createFilm(FilmDto fdto) {
        em.persist(FilmDtoManager.makeFilm(fdto));
    }
    
    @Override
    public FilmDto getDtoFromId(Long id) {
        Film f = em.find(Film.class, id);
        return FilmDtoManager.getDto(f);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
