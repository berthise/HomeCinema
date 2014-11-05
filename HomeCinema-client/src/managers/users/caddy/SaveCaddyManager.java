/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

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
public class SaveCaddyManager extends ModuleManager implements Manager_if {


  public SaveCaddyManager(LoggedModel log) {
    super(log);
  }

  @Override
  public String getMenuEntry() {
    return "save caddy";
  }

  @Override
  public void runMenuEntry() {
     System.out.println("Saving caddy is done");
  }

}
