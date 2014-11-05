/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import managers.users.*;
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
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class CaddyManager extends ModuleMenuManager implements Manager_if {

  SelectedUserModel selectedUser;

  public CaddyManager(LoggedModel log, SelectedUserModel selectedUser) {
    super(log);
    this.selectedUser = selectedUser;
      menu = new Menu(this, log, "Caddy menu:");

  }

  @Override
  public String getMenuEntry() {
    return "caddy user";
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddFilmCaddyUserManager(log, selectedUser));
      menu.addManager((Manager_if) new ListCaddyManager(log, selectedUser));

      //menu.addManager((Manager_if) new LogAsThisUserManager(log, selectedUser));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
