/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import managers.products.add.SaveProductManager;
import managers.products.add.DisplayProductManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import managers.products.add.EditProductManager;
import managers.products.add.film.AddFilmManager;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class AddProductManager extends ModuleMenuManager implements Manager_if {

  ProductModel product = null;

  public AddProductManager(LoggedModel log, ProductModel p) {
    super(log);
    menu = new Menu(this, log, "Add Product menu:");
    product = p;

  }

  @Override
  public String getMenuEntry() {
    return "add product";
  }

  @Override
  public void updateMenu() {
    menu.clean();
    menu.addManager((Manager_if) new ReturnManager(log));
    menu.addManager((Manager_if) new EditProductManager(log, product));
    if (product.notNull()) {
      menu.addManager((Manager_if) new AddFilmManager(log, product));
      menu.addManager((Manager_if) new DisplayProductManager(log, product));
      menu.addManager((Manager_if) new SaveProductManager(log, product));
    }
  }

  @Override
  public void runMenuEntry() {
    try {
    menu.addManager((Manager_if) new ReturnManager(log));
    menu.addManager((Manager_if) new EditProductManager(log, product));
    if (product.notNull()) {
      menu.addManager((Manager_if) new AddFilmManager(log, product));
      menu.addManager((Manager_if) new DisplayProductManager(log, product));
      menu.addManager((Manager_if) new SaveProductManager(log, product));
    }
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
