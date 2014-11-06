/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import main.utils.ModuleManager;
import models.FilmModel;

/**
 *
 * @author seb
 */
public class ListFilmsManager extends ModuleManager {
 
  FilmModel film = null;

  public ListFilmsManager(FilmModel f) {
    this("list des films", f);
  }
    
  public ListFilmsManager(String name, FilmModel f) {
    super(name);
    this.film = f;
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List films .......");
    // if selected film, then add a * to it 
  }

}
