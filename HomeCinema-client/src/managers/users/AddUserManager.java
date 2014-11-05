/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

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
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class AddUserManager extends ModuleMenuManager implements Manager_if {

  UserModel userModel = null;

  public AddUserManager(LoggedModel log) {
    super(log);
    userModel = new UserModel();
    menu = new Menu(this, log, "Add User menu:");

  }

  @Override
  public String getMenuEntry() {
    return "add user";
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new EditUserManager(log, userModel));
      menu.addManager((Manager_if) new DefaultUserManager(log, userModel));
      menu.addManager((Manager_if) new DisplayUserManager(log, userModel));
      menu.addManager((Manager_if) new SaveUserManager(log, userModel));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

//  public void save() {
//    System.out.println("Saving user");
//  }
}
