/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.add;

import models.UserModel;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ModuleManager;

/**
 *
 * @author seb
 */
public class EditUserManager extends ModuleManager {

  UserModel user = null;

  public EditUserManager(UserModel u) {
    this("edit user", u);
  }

  public EditUserManager(String name, UserModel u) {
    super(name);
    user = u;
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    user.initDto();
    try {
      System.out.println("Firstname:");
      user.setFirstname(reader.nextLine());
      System.out.println("Lastname:");
      user.setLastname(reader.nextLine());
      System.out.println("Email:");
      user.setEmail(reader.nextLine());
    } catch (InputMismatchException e) {
      System.out.println("Error: abording user edition");
    }
  }

}
