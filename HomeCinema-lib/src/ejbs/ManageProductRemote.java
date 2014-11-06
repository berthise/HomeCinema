/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import dtos.VideoDto;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManageProductRemote {
 
    public void createProductWithFilm(FilmDto fdto,VideoDto trailer , VideoDto vid,Integer price);
    public void addFilmsToProduct(Long pid,List<FilmDto> lfdto);
    public void addFilmToProduct(Long pid,FilmDto fdto);
}
