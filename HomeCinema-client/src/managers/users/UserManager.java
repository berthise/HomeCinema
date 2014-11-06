/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import managers.users.caddy.CaddyManager;
import models.LoggedModel;
import models.UserModel;

/**
 *
 * @author seb
 */
public class UserManager extends ModuleMenuManager implements Manager_if {

  UserModel user;

  public UserManager(LoggedModel log) {
    super(log);
      menu = new Menu(this, log, "User menu:");
      user = new UserModel(null);
  }

  @Override
  public String getMenuEntry() {
    return "users";
  }
  
  @Override
  public void updateMenu() {
    menu.clean();
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddUserManager(log, user));
      menu.addManager((Manager_if) new ListUsersManager(log, user));
      menu.addManager((Manager_if) new SelectUserManager(log, user));
      if ( user.notNull() ) {
	menu.addManager((Manager_if) new CaddyManager(log, user));
      }
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddUserManager(log, user));
      menu.addManager((Manager_if) new ListUsersManager(log, user));
      menu.addManager((Manager_if) new SelectUserManager(log, user));
      if ( user.notNull() ) {
	menu.addManager((Manager_if) new CaddyManager(log, user));

      }
      //menu.addManager((Manager_if) new LogAsThisUserManager(log, selectedUser));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
