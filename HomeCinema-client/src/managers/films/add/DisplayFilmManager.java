/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films.add;

import managers.users.*;
import models.UserModel;
import dtos.UserDto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.FilmModel;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class DisplayFilmManager extends ModuleManager implements Manager_if {

  FilmModel film = null;

  public DisplayFilmManager(LoggedModel log, FilmModel f) {
    super(log);
    film = f;
  }

  @Override
  public String getMenuEntry() {
    return "display film";
  }

  @Override
  public void runMenuEntry() {

    film.displayConsole();

  }

}
