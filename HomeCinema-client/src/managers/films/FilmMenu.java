/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;
import managers.films.add.DisplayFilmManager;
import models.FilmModel;

/**
 *
 * @author seb
 */
public class FilmMenu extends Menu {

  FilmModel film;

  public FilmMenu() {
    this("films");
  }

  public FilmMenu(String name) {
    super(name, "Films Menu: ");
    film = new FilmModel(null);
  }

  @Override
  public void updateMenu() {
    this.cleanMenu();
    this.createMenu();
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new ListFilmsManager(film));
    this.addManager((Manager_if) new SelectFilmManager(film));
    if (film.notNull()) {
      this.addManager((Manager_if) new DisplayFilmManager(film));
    }

  }

}
