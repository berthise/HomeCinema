/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import managers.users.add.EditUserManager;
import managers.users.add.SaveUserManager;
import managers.users.add.DisplayUserManager;
import managers.users.add.DefaultUserManager;
import models.UserModel;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;

/**
 *
 * @author seb
 */
public class AddUserMenu extends Menu {

  UserModel user = null;

  public AddUserMenu(UserModel u) {
    this("add user", u);
  }

  public AddUserMenu(String name, UserModel u) {
    super(name, "Add User");
    user = u;
  }

  @Override
  public void updateMenu() {
    this.cleanMenu();
    this.createMenu();
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new EditUserManager(user));
    this.addManager((Manager_if) new DefaultUserManager(user));
    if (user.notNull()) {
      this.addManager((Manager_if) new DisplayUserManager(user));
      this.addManager((Manager_if) new SaveUserManager(user));
    }
  }

}
