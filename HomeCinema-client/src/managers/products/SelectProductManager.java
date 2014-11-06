/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ModuleManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class SelectProductManager extends ModuleManager {

  ProductModel product;

  public SelectProductManager(ProductModel p) {
    this("select product", p);
  }

  public SelectProductManager(String name, ProductModel p) {
    super(name);
    this.product = p;
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Which product number:");
      Long id = reader.nextLong();
      product.initDto();
      // TODO set product from ejb
      //model.setId();
    } catch (InputMismatchException e) {
      System.out.println("Error: wrong product selection");
    }
  }

}
