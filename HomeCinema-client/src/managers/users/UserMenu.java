/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;
import managers.users.caddy.CaddyMenu;
import models.UserModel;

/**
 *
 * @author seb
 */
public class UserMenu extends Menu {

  UserModel user;

  public UserMenu() {
    this("users");
  }

  public UserMenu(String name) {
    super(name, "User");
    user = new UserModel(null);
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new AddUserMenu(user));
    this.addManager((Manager_if) new ListUsersManager(user));
    this.addManager((Manager_if) new SelectUserManager(user));
    if (user.notNull()) {
      this.addManager((Manager_if) new CaddyMenu(user));
    }
  }

  @Override
  public void updateMenu() {
    this.cleanMenu();
    this.createMenu();
  }

}
