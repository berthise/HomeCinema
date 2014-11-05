/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import models.*;
import dtos.UserDto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.SelectedUserModel;

/**
 *
 * @author seb
 */
public class LogAsThisUserManager extends ModuleManager implements Manager_if {

  SelectedUserModel model;

  public LogAsThisUserManager(LoggedModel log, SelectedUserModel model) {
    super(log);
    this.model = model;
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
