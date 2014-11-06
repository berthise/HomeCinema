/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.VideoDto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.FilmDtoManager;
import ejbs.ManageFilmRemote;
import entities.Film;
import javax.persistence.Query;
import javax.ejb.EJB;
import managers.entities.ManageEntitieVideo;


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
        Film f = FilmDtoManager.makeFilm(fdto);
        em.persist(f);
    }
    
    @Override
    public FilmDto getDtoFromId(Long id) {
        Film f = em.find(Film.class, id);
        return FilmDtoManager.getDto(f);
    }



}
