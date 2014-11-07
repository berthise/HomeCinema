/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;
import models.UserModel;

/**
 *
 * @author seb
 */
public class CaddyMenu extends Menu {

  UserModel user;

  public CaddyMenu(UserModel u) {
    this("caddy user", u);
  }

  public CaddyMenu(String name, UserModel u) {
    super(name, "Caddy");
    this.user = u;

  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new AddProductCaddyMenu(user));
    this.addManager((Manager_if) new ListCaddyManager(user));
    this.addManager((Manager_if) new SaveCaddyManager(user));
  }

  @Override
  public void updateMenu() {

  }

}
