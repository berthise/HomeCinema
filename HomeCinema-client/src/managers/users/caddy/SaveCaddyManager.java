/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import models.UserModel;
import main.utils.ModuleManager;

/**
 *
 * @author seb
 */
public class SaveCaddyManager extends ModuleManager {

  private UserModel user;

  public SaveCaddyManager(UserModel u) {
    this("save caddy", u);
  }

  public SaveCaddyManager(String name, UserModel u) {
    super(name);
    user = u;
  }

  @Override
  public void exec() {
    System.out.println("Saving caddy is done");
  }

}
