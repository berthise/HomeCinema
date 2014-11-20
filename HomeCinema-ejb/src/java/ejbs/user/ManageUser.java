/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.FilmDto;
import dtos.SimpleUserDto;
import dtos.TransactionDto;
import dtos.UserDto;
import dtos.UserDtoNoPw;
import ejbs.ManageUserRemote;
import entities.Transaction;
import entities.User;
import entities.UsersFilms;
import enums.UserStates;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import managers.dtos.FilmDtoManager;
import managers.dtos.TransactionDtoManager;
import managers.dtos.UserDtoManager;

/**
 *
 * @author toure
 */
@Stateless
public class ManageUser implements ManageUserRemote {

    @PersistenceContext
    EntityManager em;

    @Override
    public void signUp(UserDto udto) {
	udto.addDate= new Date();
	udto.state= UserStates.Unactived;
        em.persist(UserDtoManager.createUser(udto));
    }
    
    @Override
    public void save(UserDtoNoPw udto) {
      UserDtoManager.mergeOrSave(udto, em);
    }

    @Override
    public List<TransactionDto> getTransaction(Long user) {
        User u = em.find(User.class, user);
        List<TransactionDto> res = new ArrayList<>();
        for (Transaction t : u.getTransactions()) {
            res.add(TransactionDtoManager.getDto(t));
        }
        return res;
    }

    @Override
    public UserDto login(String email, String password) {
        Long id = 1L;
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = query.getSingleResult();
        return UserDtoManager.getUser(user);
    }

    @Override
    public Set<SimpleUserDto> getAllUser() {
        Query q;

        q = em.createQuery("select u From User u ", User.class);
        List<User> lu = q.getResultList();
        Set<SimpleUserDto> ludto = new HashSet<>();
        for (User u : lu) {
            ludto.add(UserDtoManager.getSimpleDto(u));
        }
        return ludto;
    }

    @Override
    public UserDtoNoPw getUser(Long id) {
        User u = em.find(User.class, id);
        return UserDtoManager.getUserNoPw(u);
    }

    @Override
    public void removeUser(Long id) {
        User u = em.find(User.class, id);
        for (Transaction t : u.getTransactions()) {
            t.setUser(null);
            em.merge(t);
        }
        for (UsersFilms uf : u.getFilms()) {
            em.remove(uf);
        }
        if (u.getCaddy() != null) {
            em.remove(u.getCaddy());
        }
        em.remove(u);
    }

    @Override
    public void mergeOrSave(UserDtoNoPw udto) {
        UserDtoManager.mergeOrSave(udto, em);
    }

    @Override
    public List<FilmDto> getFilms(Long id) {
        User p = em.find(User.class, id);
        List<FilmDto> lfdto = new ArrayList<>();
        for (UsersFilms f : p.getFilms()) {
            lfdto.add(FilmDtoManager.getDto(f.getFilm()));
        }
        return lfdto;
    }

    @Override
    public boolean changePassword(Long user, String oldPass, String newPass) {
        User u = em.find(User.class, user);
        if (!u.getPassword().equals(oldPass)) {
            return false;
        } else {
            u.setPassword(newPass);
            em.merge(u);
            return true;
        }
    }

}
