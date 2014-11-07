/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films.add;

import main.utils.ModuleManager;
import models.FilmModel;

/**
 *
 * @author seb
 */
public class DisplayFilmManager extends ModuleManager {

  FilmModel film = null;

  public DisplayFilmManager(FilmModel f) {
    this("display film", f);
  }

  public DisplayFilmManager(String name, FilmModel f) {
    super(name);
    film = f;
  }

  @Override
  public void exec() {

    film.displayConsole();

  }

}
