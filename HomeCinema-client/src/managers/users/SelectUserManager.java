/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

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
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class SelectUserManager extends ModuleManager implements Manager_if {

  SelectedUserModel model;

  public SelectUserManager(LoggedModel log, SelectedUserModel model) {
    super(log);
    this.model = model;
  }

  @Override
  public String getMenuEntry() {
    return "select user";
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Which user number:");
      model = new SelectedUserModel(reader.nextInt());
    } catch (InputMismatchException e) {
      System.out.println("Error: wrong user selection");
    }
  }

}
