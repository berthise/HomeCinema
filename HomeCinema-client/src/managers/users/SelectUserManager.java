/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ModuleManager;
import models.UserModel;

/**
 *
 * @author seb
 */
public class SelectUserManager extends ModuleManager {

  UserModel user;

  public SelectUserManager(UserModel u) {
    this("select user", u);
  }

  public SelectUserManager(String name, UserModel u) {
    super(name);
    this.user = u;
  }

  @Override
  public void exec() {
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
