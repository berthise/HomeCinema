/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.UsersFilmsDto;
import ejbs.ManageUsersFilmsRemote;
import entities.UsersFilms;
import enums.UsersFilmsStates;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import static managers.dtos.UsersFilmsDtoManager.getDto;

/**
 *
 * @author toure
 */
public class ManageEntitieUsersFilms implements ManageUsersFilmsRemote {

    @PersistenceContext
    EntityManager em;

    @Override
    public void updateCurrentTime(UsersFilmsDto ufdto) {
        TypedQuery<UsersFilms> query = em.createQuery("SELECT uf FROM UsersFilms uf WHERE uf.film = :film AND u.user = :user", UsersFilms.class);
        query.setParameter("film", ufdto.film);
        query.setParameter("user", ufdto.user);
        UsersFilms usersFilms = query.getSingleResult();
        if (usersFilms != null) {
            usersFilms.setCurrentPosition(ufdto.currentPosition);
            usersFilms.setState(UsersFilmsStates.Viewed);
            em.merge(usersFilms);
        }
    }

    @Override
    public UsersFilmsDto getCurrentTime(Long user, Long film) {
        TypedQuery<UsersFilms> query = em.createQuery("SELECT uf FROM UsersFilms uf WHERE uf.film = :film AND u.user = :user", UsersFilms.class);
        query.setParameter("film", film);
        query.setParameter("user", user);
        UsersFilms usersFilms = query.getSingleResult();
        return getDto(usersFilms);
    }

}
