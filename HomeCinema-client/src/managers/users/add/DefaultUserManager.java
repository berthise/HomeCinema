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
public class DefaultUserManager extends ModuleManager {

  UserModel user = null;

  public DefaultUserManager(UserModel u) {
    this("default value", u);
  }

  public DefaultUserManager(String name, UserModel u) {
    super(name);
    user = u;
  }

  @Override
  public void exec() {

    user.setFirstname("Boris");
    user.setLastname("Truchau");
    user.setEmail("boris.truchau@debian.org");
  }

}
