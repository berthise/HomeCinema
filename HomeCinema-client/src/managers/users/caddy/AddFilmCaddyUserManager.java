/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import models.UserModel;
import dtos.UserDto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import managers.films.ListFilmsManager;
import managers.films.SelectFilmManager;
import managers.users.AddUserManager;
import managers.users.ListUsersManager;
import managers.users.SelectUserManager;
import models.LoggedModel;
import models.SelectedFilmModel;
import models.SelectedUserModel;

/**
 *
 * @author seb
 */
public class AddFilmCaddyUserManager extends ModuleMenuManager implements Manager_if {

  SelectedUserModel user;
  SelectedFilmModel film;

  public AddFilmCaddyUserManager(LoggedModel log, SelectedUserModel model) {
    super(log);
    user = model;
    film = null;
      menu = new Menu(this,log, "Add film to caddy menu:");

  }

  @Override
  public String getMenuEntry() {
    return "add film to caddy user";
  }
  
    @Override
  public void updateMenu() {
    menu.clean();
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new ListFilmsManager(log, film));
      menu.addManager((Manager_if) new SelectFilmManager(log, film));
//      if ( film != null ) {
//      menu.addManager((Manager_if) new AddSelectedToCaddyManager(log, userModel));
//      }
//      menu.addManager((Manager_if) new SaveCaddyManager(log));
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new ListFilmsManager(log, film));
      menu.addManager((Manager_if) new SelectFilmManager(log, film));
//      if ( film != null ) {
//      menu.addManager((Manager_if) new AddSelectedToCaddyManager(log, userModel));
//      }
//      menu.addManager((Manager_if) new SaveCaddyManager(log));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }
}
