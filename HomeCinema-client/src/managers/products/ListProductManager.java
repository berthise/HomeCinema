/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import main.utils.ModuleManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class ListProductManager extends ModuleManager {

  ProductModel product = null;

  public ListProductManager(ProductModel p) {
    this("list products", p);
  }

  public ListProductManager(String name, ProductModel p) {
    super(name);
    this.product = p;
  }

  @Override
  public void exec() {
    System.out.println("List products .......");
    // if selected film, then add a * to it 
  }

}
