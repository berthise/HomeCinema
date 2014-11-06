/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager;
import models.LoggedModel;
import models.UserModel;

/**
 *
 * @author seb
 */
public class CaddyManager extends ModuleMenuManager implements Manager_if {

  UserModel user;

  public CaddyManager(LoggedModel log, UserModel u) {
    super(log);
    this.user = u;
      menu = new Menu(this, log, "Caddy menu:");

  }

  @Override
  public String getMenuEntry() {
    return "caddy user";
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new AddProductCaddyManager(log, user));
      menu.addManager((Manager_if) new ListCaddyManager(log, user));
      menu.addManager((Manager_if) new SaveCaddyManager(log, user));

      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

}
