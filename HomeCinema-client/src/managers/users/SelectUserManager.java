/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.Manager_if;
import main.utils.ModuleManager;
import models.LoggedModel;
import models.UserModel;

/**
 *
 * @author seb
 */
public class SelectUserManager extends ModuleManager implements Manager_if {

  UserModel user;

  public SelectUserManager(LoggedModel log, UserModel u) {
    super(log);
    this.user = u;
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
      Long id = reader.nextLong();
      user.initDto();

      // TODO set product from ejb
      //model.setId();
    } catch (InputMismatchException e) {
      System.out.println("Error: wrong user selection");
    }
  }

}
