/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import main.utils.Manager_if;
import main.utils.ModuleManager;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class ListProductManager extends ModuleManager implements Manager_if {

ProductModel product = null;

  public ListProductManager(LoggedModel log, ProductModel product) {
    super(log);
    this.product = product;
  }

  @Override
  public String getMenuEntry() {
    return "list products";
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List products .......");
    // if selected film, then add a * to it 
  }

}
