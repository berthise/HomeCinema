/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import managers.users.caddy.AddFilmCaddyUserManager;
import models.SelectedUserModel;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import managers.users.caddy.CaddyManager;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class UserManager extends ModuleMenuManager implements Manager_if {

  SelectedUserModel selectedUser;

  public UserManager(LoggedModel log) {
    super(log);
      menu = new Menu(this, log, "User menu:");

  }

  @Override
  public String getMenuEntry() {
    return "users";
  }
  
  @Override
  public void updateMenu() {
    menu.clean();
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddUserManager(log));
      menu.addManager((Manager_if) new ListUsersManager(log, selectedUser));
      menu.addManager((Manager_if) new SelectUserManager(log, selectedUser));
      if ( selectedUser != null) {
	menu.addManager((Manager_if) new CaddyManager(log, selectedUser));
      }
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddUserManager(log));
      menu.addManager((Manager_if) new ListUsersManager(log, selectedUser));
      menu.addManager((Manager_if) new SelectUserManager(log, selectedUser));
      if ( selectedUser != null) {
	menu.addManager((Manager_if) new CaddyManager(log, selectedUser));

      }
      //menu.addManager((Manager_if) new LogAsThisUserManager(log, selectedUser));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
