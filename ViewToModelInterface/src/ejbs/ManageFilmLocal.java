/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import javax.ejb.Local;

/**
 *
 * @author titou
 */
@Local
public interface ManageFilmLocal {
 
    public void createFilm (FilmDto fdto);
    
}
