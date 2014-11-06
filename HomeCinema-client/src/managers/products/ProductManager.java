/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class ProductManager extends ModuleMenuManager implements Manager_if {

  ProductModel product = null;

  public ProductManager(LoggedModel log) {
    super(log);
    menu = new Menu(this, log, "Product menu:");
    product = new ProductModel(null);
  }

  @Override
  public String getMenuEntry() {
    return "products";
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddProductManager(log, product));
      menu.addManager((Manager_if) new ListProductManager(log, product));
      menu.addManager((Manager_if) new SelectProductManager(log, product));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
