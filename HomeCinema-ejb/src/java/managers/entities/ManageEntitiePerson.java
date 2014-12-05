/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.PersonDto;
import entities.Person;
import javax.persistence.EntityManager;
import managers.dtos.PersonDtoManager;

/**
 *
 * @author titou
 */
public class ManageEntitiePerson {

    public static Person getPerson(PersonDto gdto, EntityManager em) {
        Person g = em.find(Person.class, gdto.id);
        if (g == null) {
            g= PersonDtoManager.makePerson(gdto);
            em.persist(g);
        }
        return g;
    }
    
}
