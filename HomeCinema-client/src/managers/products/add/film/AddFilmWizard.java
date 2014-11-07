/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add.film;

import managers.films.add.DisplayFilmManager;
import managers.films.add.EditFilmManager;
import main.utils.Manager_if;
import main.utils.ReturnManager;
import main.utils.Wizard;
import managers.films.add.AddTrailerManager;
import managers.films.add.AddVideoManager;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class AddFilmWizard extends Wizard {

  ProductModel product = null;

  public AddFilmWizard(ProductModel p) {
    this("add film wizard", p);

  }

  public AddFilmWizard(String name, ProductModel p) {
    super(name, "Add Film");
    this.product = p;

  }

  @Override
  public void updateWizard() {
    this.cleanWizard();
    this.createWizard();
  }
  
    @Override
  public void createWizard() {
    this.addManager((Manager_if) new EditFilmManager(product));
    this.addManager((Manager_if) new DisplayFilmManager(product.getFilm()));
    this.addManager((Manager_if) new AddVideoManager(product.getFilm()));
    this.addManager((Manager_if) new AddTrailerManager(product.getFilm()));
  }

}
