/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import main.utils.ModuleManager;
import models.UserModel;

/**
 *
 * @author seb
 */
public class ListCaddyManager extends ModuleManager {

  UserModel user = null;

  public ListCaddyManager(UserModel u) {
    this("list caddy", u);
  }

  public ListCaddyManager(String name, UserModel u) {
    super(name);
    this.user = u;
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List caddy user .......");
    // if selected user, then add a * to it 
  }

}
