/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import main.utils.Manager_if;
import main.utils.ModuleManager;
import models.FilmModel;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class ListFilmsManager extends ModuleManager implements Manager_if {
 
  FilmModel film = null;

  public ListFilmsManager(LoggedModel log, FilmModel f) {
    super(log);
    this.film = f;
  }

  @Override
  public String getMenuEntry() {
    return "list films";
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List films .......");
    // if selected film, then add a * to it 
  }

}
