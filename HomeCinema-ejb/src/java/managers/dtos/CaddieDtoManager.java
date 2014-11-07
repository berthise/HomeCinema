/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.CaddieDto;
import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.ProductDto;
import dtos.VideoDto;
import entities.Film;
import entities.Product;
import entities.Video;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class CaddieDtoManager {
    
    public static CaddieDto getDto(List<Product> caddie){
        if (caddie == null) {
            return null;
        }
        
        CaddieDto cdto = new CaddieDto();
        cdto.films = new ArrayList<>();
        
        for (Product p : caddie){
            cdto.films.add(ProductDtoManager.getDto(p));
        }
        
        return cdto;        
    }
}
