/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.CaddieDto;
import entities.Caddy;
import entities.Product;
import enums.Lang;
import java.util.ArrayList;

/**
 *
 * @author titou
 */
public class CaddieDtoManager {

  /*
  return caddie vide si caddie null
  */
  public static CaddieDto getDto(Caddy caddie,Lang lang) {

    CaddieDto cdto = new CaddieDto();
    cdto.films = new ArrayList<>();
    if (caddie == null) {
      return cdto;
    }

    for (Product p : caddie.getProducts()) {
      cdto.films.add(ProductDtoManager.getDto(p,lang));
    }

    return cdto;
  }
}
