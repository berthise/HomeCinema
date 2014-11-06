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
import managers.products.add.DisplayProductManager;
import managers.products.add.EditProductManager;
import managers.products.add.SaveProductManager;
import managers.products.add.film.AddFilmManager;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class AddUserManager extends ModuleMenuManager implements Manager_if {

  UserModel user = null;

  public AddUserManager(LoggedModel log, UserModel u) {
    super(log);
    user = u;
    menu = new Menu(this, log, "Add User menu:");

  }

  @Override
  public String getMenuEntry() {
    return "add user";
  }

  @Override
  public void updateMenu() {
    menu.clean();
    menu.addManager((Manager_if) new ReturnManager(log));
    menu.addManager((Manager_if) new EditUserManager(log, user));
    menu.addManager((Manager_if) new DefaultUserManager(log, user));
    if (user.notNull()) {
      menu.addManager((Manager_if) new DisplayUserManager(log, user));
      menu.addManager((Manager_if) new SaveUserManager(log, user));
    }
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new EditUserManager(log, user));
      menu.addManager((Manager_if) new DefaultUserManager(log, user));
      if (user.notNull()) {
	menu.addManager((Manager_if) new DisplayUserManager(log, user));
	menu.addManager((Manager_if) new SaveUserManager(log, user));
      }
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

//  public void save() {
//    System.out.println("Saving user");
//  }
}
