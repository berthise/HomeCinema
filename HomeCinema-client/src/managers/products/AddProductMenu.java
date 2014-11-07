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
import main.utils.ReturnManager;
import managers.products.add.EditProductManager;
import managers.products.add.film.AddFilmMenu;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class AddProductMenu extends Menu {

  ProductModel product = null;

  public AddProductMenu(ProductModel p) {
    this("add product", p);
  }

  public AddProductMenu(String name, ProductModel p) {
    super(name, "Add Product");
    product = p;
  }

  @Override
  public void updateMenu() {
    this.cleanMenu();
    this.createMenu();
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new EditProductManager(product));
    if (product.notNull()) {
      this.addManager((Manager_if) new AddFilmMenu(product));
      this.addManager((Manager_if) new DisplayProductManager(product));
      this.addManager((Manager_if) new SaveProductManager(product));
    }
  }
}
