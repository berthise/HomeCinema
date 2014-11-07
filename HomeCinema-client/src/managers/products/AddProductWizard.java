/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products;

import managers.products.add.SaveProductManager;
import managers.products.add.DisplayProductManager;
import main.utils.Manager_if;
import main.utils.Wizard;
import managers.products.add.EditProductManager;
import managers.products.add.film.AddFilmWizard;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class AddProductWizard extends Wizard {

  ProductModel product = null;

  public AddProductWizard(ProductModel p) {
    this("add product wizard", p);
  }

  public AddProductWizard(String name, ProductModel p) {
    super(name, "Add Product");
    product = p;
  }

  @Override
  public void updateWizard() {
    this.cleanWizard();
    this.createWizard();
  }

  @Override
  public void createWizard() {
    this.addManager((Manager_if) new EditProductManager(product));
    this.addManager((Manager_if) new AddFilmWizard(product));
    this.addManager((Manager_if) new DisplayProductManager(product));
    this.addManager((Manager_if) new SaveProductManager(product));
  }
}
