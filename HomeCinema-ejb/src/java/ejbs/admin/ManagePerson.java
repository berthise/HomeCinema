/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.PersonDto;
import ejbs.ManagePersonRemote;
import entities.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managers.dtos.PersonDtoManager;

/**
 *
 * @author titou
 */
@Stateless
public class ManagePerson implements ManagePersonRemote {

    @PersistenceContext
    public EntityManager em;

    @Override
    public PersonDto getPerson(String n) {
	Query q = em.createQuery("From Person p where p.name=:n", Person.class);
	q.setParameter("n", n);
	List<Person> r = q.getResultList();
	if (r.isEmpty())
	    return null;
	else
	    return PersonDtoManager.getDto(r.get(0));
    }

    @Override
    public PersonDto getPerson(Long id) {
	Person p = em.find(Person.class, id);
	return PersonDtoManager.getDto(p);
    }
}
