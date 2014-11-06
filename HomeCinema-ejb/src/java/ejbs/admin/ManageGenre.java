/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.GenreDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author titou
 */
@Stateless
public class ManageGenre {
        @PersistenceContext
    public EntityManager em;
    
        public void makeBaseGenre(List<GenreDto> lgdto)
        {
            
        }
    
    
}
