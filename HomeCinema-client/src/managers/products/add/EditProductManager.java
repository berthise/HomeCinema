/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ModuleManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class EditProductManager extends ModuleManager {

  ProductModel product = null;

  public EditProductManager(ProductModel p) {
    this("edit product", p);
  }

  public EditProductManager(String name, ProductModel p) {
    super(name);
    product = p;
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    product.initDto();
    try {
      System.out.println("Price:");
      product.setPrice(reader.nextInt());
      System.out.println("Name:");
      product.setName(reader.next());
    } catch (InputMismatchException e) {
      System.out.println("Error: abording product edition");
    }
  }
}
