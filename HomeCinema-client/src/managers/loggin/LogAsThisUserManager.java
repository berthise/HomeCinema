/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.loggin;

import models.*;
import main.utils.Manager_if;
import main.utils.ModuleManager;

/**
 *
 * @author seb
 */
public class LogAsThisUserManager extends ModuleManager implements Manager_if {

  UserModel user;

  public LogAsThisUserManager(LoggedModel log, UserModel u) {
    super(log);
    this.user = u;
  }

  @Override
  public String getMenuEntry() {
    return "log as selected user";
  }

  @Override
  public void runMenuEntry() {
    log.connect(-1);
    System.out.println("Loggin done.");
  }

}
