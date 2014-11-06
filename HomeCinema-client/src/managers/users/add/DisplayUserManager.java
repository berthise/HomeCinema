/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.add;

import models.UserModel;
import main.utils.ModuleManager;

/**
 *
 * @author seb
 */
public class DisplayUserManager extends ModuleManager {

  UserModel user = null;

  public DisplayUserManager(UserModel u) {
    this("display user", u);
  }

  public DisplayUserManager(String name, UserModel u) {
    super(name);
    user = u;
  }

  @Override
  public void runMenuEntry() {
    user.displayConsole();

  }

}
