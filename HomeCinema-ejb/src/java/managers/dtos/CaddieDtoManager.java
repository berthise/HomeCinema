/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.CaddieDto;
import entities.Caddy;
import entities.Product;
import java.util.ArrayList;

/**
 *
 * @author titou
 */
public class CaddieDtoManager {
    
    public static CaddieDto getDto(Caddy caddie){
        if (caddie == null) {
            return null;
        }
        
        CaddieDto cdto = new CaddieDto();
        cdto.films = new ArrayList<>();
        
        for (Product p : caddie.getProducts()){
            cdto.films.add(ProductDtoManager.getDto(p));
        }
        
        return cdto;        
    }
}
