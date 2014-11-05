/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import managers.users.*;
import models.SelectedUserModel;
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
public class ListCaddyManager extends ModuleManager implements Manager_if {

SelectedUserModel model = null;

  public ListCaddyManager(LoggedModel log, SelectedUserModel model) {
    super(log);
    this.model = model;
  }

  @Override
  public String getMenuEntry() {
    return "list caddy";
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List caddy user .......");
    // if selected user, then add a * to it 
  }

}
