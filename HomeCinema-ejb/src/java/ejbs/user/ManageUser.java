/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.UserDto;
import ejbs.ManageUserRemote;
import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
}
