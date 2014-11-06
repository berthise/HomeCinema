/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.UserDto;
import ejbs.ManageUserRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
