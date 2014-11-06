/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users;

import main.utils.Manager_if;
import main.utils.ModuleManager;
import models.LoggedModel;
import models.UserModel;

/**
 *
 * @author seb
 */
public class ListUsersManager extends ModuleManager implements Manager_if {

UserModel user = null;

  public ListUsersManager(LoggedModel log, UserModel u) {
    super(log);
    this.user = u;
  }

  @Override
  public String getMenuEntry() {
    return "list user";
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List users .......");
    // if selected user, then add a * to it 
  }

}
