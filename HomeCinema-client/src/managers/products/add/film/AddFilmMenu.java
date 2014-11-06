/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add.film;

import managers.films.add.DisplayFilmManager;
import managers.films.add.EditFilmManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;
import managers.films.add.AddTrailerManager;
import managers.films.add.AddVideoManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class AddFilmMenu extends Menu {

  ProductModel product = null;

  public AddFilmMenu(ProductModel p) {
    this("add film", p);

  }

  public AddFilmMenu(String name, ProductModel p) {
    super(name, "Add Film menu:");
    this.product = p;

  }

  @Override
  public void updateMenu() {
    this.cleanMenu();
    this.createMenu();
  }
  
    @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new EditFilmManager(product));
    if ( product.getFilm() != null && product.getFilm().notNull()) {
      this.addManager((Manager_if) new DisplayFilmManager(product.getFilm()));
      this.addManager((Manager_if) new AddVideoManager(product.getFilm()));
      this.addManager((Manager_if) new AddTrailerManager(product.getFilm()));
    }
  }

}
