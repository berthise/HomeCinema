/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.FilmDto;
import dtos.SimpleUserDto;
import dtos.UserDto;
import dtos.UserDtoNoPw;
import ejbs.ManageUserRemote;
import entities.Film;
import entities.Product;
import entities.User;
import entities.UsersFilms;
import enums.UserStates;
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

    public Set<SimpleUserDto> getAllUser(boolean rem) {
        Query q;
        if (!rem)
         q = em.createQuery("select u From User u where u.state <> :remove",User.class).setParameter("remove", UserStates.Removed);
        else
            q = em.createQuery("select u From User u ",User.class);
        List<User> lu = q.getResultList(); 
        Set<SimpleUserDto> ludto = new HashSet<>();
        for (User u : lu) {
            ludto.add(UserDtoManager.getSimpleDto(u));
        }
        return ludto;
    }
    
    public UserDtoNoPw getUser(Long id)
    {
        User u = em.find(User.class, id);
        return UserDtoManager.getUserNoPw(u);
    }
    
    public void removeUser (Long id)
    {
        User u = em.find(User.class, id);
        u.setBirthDate(null);
        u.setCaddy(null);
        //u.setEmail(null);
        u.setLastName(null);
        //u.setNickName(null);
        u.setFirstName(null);
        u.setPassword(null);
        u.setFilms(null);
        u.setState(UserStates.Removed);
        em.merge(u);
    }
    
    public void mergeOrSave (UserDtoNoPw udto)
    {
        UserDtoManager.mergeOrSave(udto, em);
    }
    
    public List<FilmDto> getFilms(Long id)
    {
                User p = em.find(User.class, id);
        List<FilmDto> lfdto = new ArrayList<>();
        for ( UsersFilms f : p.getFilms())
        {
            lfdto.add(FilmDtoManager.getDto(f.getFilm()));
        }
        return lfdto;
    }
    
}
