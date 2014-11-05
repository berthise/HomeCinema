/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import models.LoggedModel;
import models.SelectedFilmModel;

/**
 *
 * @author seb
 */
public class FilmManager extends ModuleMenuManager implements Manager_if {

  SelectedFilmModel film;

  public FilmManager(LoggedModel log) {
    super(log);
      menu = new Menu(this, log, "Films menu:");

  }

  @Override
  public String getMenuEntry() {
    return "films";
  }


  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new ListFilmsManager(log, film));
      menu.addManager((Manager_if) new SelectFilmManager(log, film));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
