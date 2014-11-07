/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.GenreDto;
import entities.Genre;
import javax.persistence.EntityManager;
import managers.dtos.GenreDtoManager;

/**
 *
 * @author titou
 */
public class ManageEntitieGenre {

    public static Genre getGenre(GenreDto gdto, EntityManager em) {
        Genre g = em.find(Genre.class, gdto.id);
        if (g == null) {
            GenreDtoManager.makeGenre(gdto);
            em.persist(g);
        }
        return g;
    }
    
}