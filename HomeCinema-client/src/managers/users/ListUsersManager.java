/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import main.utils.ModuleManager;
import models.UserModel;

/**
 *
 * @author seb
 */
public class ListUsersManager extends ModuleManager {

  UserModel user = null;

  public ListUsersManager(UserModel u) {
    this("list users", u);
  }

  public ListUsersManager(String name, UserModel u) {
    super(name);
    this.user = u;
  }

  @Override
  public void exec() {
    System.out.println("List users .......");
    // if selected user, then add a * to it 
  }

}
