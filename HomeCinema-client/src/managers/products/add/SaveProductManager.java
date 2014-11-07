/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add;

import dtos.FilmDto;
import dtos.VideoDto;
import ejbs.ManageProductRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import main.HomeCinemaClient;
import main.utils.ModuleManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class SaveProductManager extends ModuleManager {

  ProductModel product = null;

  public SaveProductManager(ProductModel p) {
    this("save product", p);
  }

  public SaveProductManager(String name, ProductModel p) {
    super(name);
    this.product = p;
  }

  @Override
  public void exec() {
    System.out.println("Start saving product");
    try {
    FilmDto film = product.getFilm().getfDto();
    VideoDto video = product.getFilm().getVideo().getvDto();
    VideoDto trailer = product.getFilm().getTrailer().getvDto();
    Integer price = product.getPrice();
    
    HomeCinemaClient.getManageProductRemote().
	    createProductWithFilm(film , video, trailer, price);
    } catch (NullPointerException e) {
      System.out.println("Erreur: produit incomplet.");
    }
    System.out.println("Saving done");

  }

}
