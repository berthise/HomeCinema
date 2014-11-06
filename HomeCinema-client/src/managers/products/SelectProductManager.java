/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.Manager_if;
import main.utils.ModuleManager;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class SelectProductManager extends ModuleManager implements Manager_if {

  ProductModel product;

  public SelectProductManager(LoggedModel log, ProductModel p) {
    super(log);
    this.product = p;
  }

  @Override
  public String getMenuEntry() {
    return "select product";
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
