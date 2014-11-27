/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.PersonDto;
import ejbs.ManagePersonRemote;
import entities.Film;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managers.dtos.PersonDtoManager;

/**
 *
 * @author titou
 */
public class ManagePerson implements ManagePersonRemote {
    
        @PersistenceContext
    public EntityManager em;
	
    public PersonDto getPerson(String n)
    {
	Query q = em.createQuery("From Person p where p.name=:n", Person.class);
	q.setParameter("n", n);
	Person p = (Person) q.getSingleResult();
	return PersonDtoManager.getDto(p);
    }
    
}
