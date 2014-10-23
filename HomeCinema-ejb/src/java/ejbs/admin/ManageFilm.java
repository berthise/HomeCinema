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
import ejbs.ManageFilmLocal;

/**
 *
 * @author titou
 */
@Stateless
public class ManageFilm implements ManageFilmLocal {

    @PersistenceContext
    public EntityManager em;

    @Override
    public void createFilm(FilmDto fdto) {
        em.persist(FilmDtoManager.makeFilm(fdto));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
