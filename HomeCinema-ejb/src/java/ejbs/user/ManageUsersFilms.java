/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.UsersFilmsDto;
import ejbs.ManageUsersFilmsRemote;
import entities.User;
import entities.UsersFilms;
import enums.UsersFilmsStates;
import java.util.Iterator;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.UsersFilmsDtoManager;

/**
 *
 * @author toure
 */
@Stateless
public class ManageUsersFilms implements ManageUsersFilmsRemote {

    @PersistenceContext
    EntityManager em;

    @Override
    public void updateCurrentTime(Long user, UsersFilmsDto ufdto) {
	
        User u = em.find(User.class, user);
        Iterator<UsersFilms> ufi = u.getFilms().iterator();
        boolean cont = true;
        while (ufi.hasNext() && cont) {
            UsersFilms uf = ufi.next();
            if (uf != null && Objects.equals(uf.getFilm().getId(), ufdto.film)) {
                uf.setCurrentPosition(ufdto.currentPosition);
                uf.setState(UsersFilmsStates.Viewed);
                em.merge(uf);
                cont = false;
            }
        }
    }

    @Override
    public UsersFilmsDto getCurrentTime(Long user, Long film) {
        User u = em.find(User.class, user);
        Iterator<UsersFilms> ufi = u.getFilms().iterator();
        boolean cont = true;
        UsersFilms uf = null;
        while (ufi.hasNext() && cont) {
            uf = ufi.next();
            if (uf != null && Objects.equals(uf.getFilm().getId(), film)) {
                cont = false;
            }
        }
        return UsersFilmsDtoManager.getDto(uf);
    }
}
