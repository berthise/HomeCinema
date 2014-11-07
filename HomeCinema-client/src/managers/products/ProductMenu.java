/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class ProductMenu extends Menu {

  ProductModel product = null;

  public ProductMenu() {
    this("products");
  }

  public ProductMenu(String name) {
    super(name, "Product");
    product = new ProductModel(null);
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new AddProductMenu(product));
    this.addManager((Manager_if) new AddProductWizard(product));
    this.addManager((Manager_if) new ListProductManager(product));
    this.addManager((Manager_if) new SelectProductManager(product));
  }

  @Override
  public void updateMenu() {

  }

}
