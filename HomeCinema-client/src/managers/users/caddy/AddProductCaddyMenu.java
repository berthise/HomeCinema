/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.users.caddy;

import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ReturnManager;
import managers.products.ListProductManager;
import managers.products.SelectProductManager;
import models.ProductModel;
import models.UserModel;

/**
 *
 * @author seb
 */
public class AddProductCaddyMenu extends Menu {

  UserModel user;
  ProductModel product;

  public AddProductCaddyMenu(UserModel u) {
    this("add product to caddy", u);
  }

  public AddProductCaddyMenu(String name, UserModel u) {
    super(name, "Add film to caddy menu:");
    user = u;
    product = null;

  }

  @Override
  public String getMenuEntry() {
    return "add product to caddy user";
  }

  @Override
  public void createMenu() {
    this.addManager((Manager_if) new ReturnManager());
    this.addManager((Manager_if) new ListProductManager(product));
    this.addManager((Manager_if) new SelectProductManager(product));
    //      if ( product.notNull() ) {
//      menu.addManager((Manager_if) new AddSelectedToCaddyManager(product));
    //      menu.addManager((Manager_if) new SaveCaddyManager());

//      }
  }

  @Override
  public void updateMenu() {

  }

}
