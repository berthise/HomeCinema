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
public class SaveUserManager extends ModuleManager {

  UserModel user = null;

  public SaveUserManager(UserModel u) {
    this("save user", u);
  }

  public SaveUserManager(String name, UserModel u) {
    super(name);
    user = u;
  }

  @Override
  public void exec() {
    System.out.println("Saving is done");
  }

}
