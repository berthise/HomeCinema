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
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import managers.films.add.AddTrailerManager;
import managers.films.add.AddVideoManager;
import managers.products.add.DisplayProductManager;
import managers.products.add.SaveProductManager;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class AddFilmManager extends ModuleMenuManager implements Manager_if {

  ProductModel product = null;

  public AddFilmManager(LoggedModel log, ProductModel product) {
    super(log);
    menu = new Menu(this, log, "Add Film menu:");
    this.product = product;

  }

  @Override
  public String getMenuEntry() {
    return "add film";
  }
  
    @Override
  public void updateMenu() {
    menu.clean();
    menu.addManager((Manager_if) new ReturnManager(log));
    menu.addManager((Manager_if) new EditFilmManager(log, product));
    if (product.getFilm().notNull()) {
            menu.addManager((Manager_if) new DisplayFilmManager(log, product.getFilm()));
            menu.addManager((Manager_if) new AddVideoManager(log, product.getFilm()));
	    menu.addManager((Manager_if) new AddTrailerManager(log, product.getFilm()));
    }
  }


  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new EditFilmManager(log, product));

      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
