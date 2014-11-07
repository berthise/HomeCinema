/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ExitManager;
import managers.films.FilmMenu;
import managers.products.ProductMenu;
import managers.users.UserMenu;

/**
 *
 * @author titou
 */
public class MainMenu extends Menu {

  public MainMenu() {
    super("Main");
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ExitManager());
    this.addManager((Manager_if) new FilmMenu());
    this.addManager((Manager_if) new ProductMenu());
    this.addManager((Manager_if) new UserMenu());
  }

  @Override
  public void updateMenu() {
  }
}
