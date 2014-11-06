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
public class EditUserManager extends ModuleManager implements Manager_if {

  UserModel user = null;

  public EditUserManager(LoggedModel log, UserModel model) {
    super(log);
    user = model;
  }

  @Override
  public String getMenuEntry() {
    return "edit user";
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
