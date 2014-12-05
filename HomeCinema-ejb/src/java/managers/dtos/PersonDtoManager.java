/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.PersonDto;
import entities.Person;

/**
 *
 * @author titou
 */
public class PersonDtoManager {
    public static Person makePerson(PersonDto gdto)
    {
        Person g = new Person();
        g.setName(gdto.name);
        g.setId(gdto.id);
        return g;
    }
    public static PersonDto getDto(Person g)
    {
        PersonDto gdto = new PersonDto();
        gdto.id=g.getId();
        gdto.name=g.getName();
        return gdto;
    }
}
