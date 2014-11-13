/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.FilmDto;
import dtos.SimpleUserDto;
import dtos.UserDto;
import ejbs.ManageUserRemote;
import entities.Film;
import entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import managers.dtos.FilmDtoManager;
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
        em.persist(UserDtoManager.createUser(udto));
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

    public Set<SimpleUserDto> getAllUser() {
        Query q = em.createQuery("select u from User");
        List<User> lu = q.getResultList();
        Set<SimpleUserDto> ludto = new HashSet<>();
        for (User u : lu) {
            ludto.add(UserDtoManager.getSimpleDto(u));
        }
        return ludto;
    }
    
    public UserDto getUser(Long id)
    {
        User u = em.find(User.class, id);
        return UserDtoManager.getUser(u);
    }
}
