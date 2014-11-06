/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.loggin;

import managers.loggin.LogAsNameManager;
import managers.films.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ModuleManager;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import main.utils.ReturnManager;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class LogginManager extends ModuleMenuManager implements Manager_if {


  public LogginManager(LoggedModel log) {
    super(log);
      menu = new Menu(this, log, "Loggin menu:");

  }

  @Override
  public String getMenuEntry() {
    if (log.isLogged()) {
      return "deconnection";
    } else {
      return "connection";
    }
  }

  @Override
  public void runMenuEntry() {
    if (log.isLogged()) {
      log.disconnect();
      return;
    }
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new LogAsNameManager(log));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
