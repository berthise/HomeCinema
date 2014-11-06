/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.add;

import models.UserModel;
import dtos.UserDto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class SaveUserManager extends ModuleManager implements Manager_if {

  UserModel userModel = null;

  public SaveUserManager(LoggedModel log, UserModel model) {
    super(log);
    userModel = model;
  }

  @Override
  public String getMenuEntry() {
    return "save user";
  }

  @Override
  public void runMenuEntry() {
     System.out.println("Saving is done");
  }

}
