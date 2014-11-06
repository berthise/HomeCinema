/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add;

import main.utils.ModuleManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class DisplayProductManager extends ModuleManager {

  ProductModel product = null;

  public DisplayProductManager(ProductModel p) {
    this("display product", p);
  }

  public DisplayProductManager(String name, ProductModel p) {
    super(name);
    product = p;
  }

  @Override
  public void runMenuEntry() {
    product.displayConsole();

  }

}
