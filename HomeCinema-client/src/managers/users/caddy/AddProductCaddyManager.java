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
import managers.films.ListFilmsManager;
import managers.films.SelectFilmManager;
import managers.products.ListProductManager;
import managers.products.SelectProductManager;
import models.LoggedModel;
import models.ProductModel;
import models.UserModel;

/**
 *
 * @author seb
 */
public class AddProductCaddyManager extends ModuleMenuManager implements Manager_if {

  UserModel user;
  ProductModel product;

  public AddProductCaddyManager(LoggedModel log, UserModel u) {
    super(log);
    user = u;
    product = null;
      menu = new Menu(this,log, "Add film to caddy menu:");

  }

  @Override
  public String getMenuEntry() {
    return "add product to caddy user";
  }
  
    @Override
  public void updateMenu() {
    menu.clean();
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new ListProductManager(log, product));
      menu.addManager((Manager_if) new SelectProductManager(log, product));
//      if ( product.notNull() ) {
//      menu.addManager((Manager_if) new AddSelectedToCaddyManager(log, userModel));
//      }
//      menu.addManager((Manager_if) new SaveCaddyManager(log));
  }

  @Override
  public void runMenuEntry() {
    try {
      menu.addManager((Manager_if) new ReturnManager(log));
      menu.addManager((Manager_if) new ListProductManager(log, product));
      menu.addManager((Manager_if) new SelectProductManager(log, product));
//      if ( film != null ) {
//      menu.addManager((Manager_if) new AddSelectedToCaddyManager(log, userModel));
//      }
//      menu.addManager((Manager_if) new SaveCaddyManager(log));
      menu.runMenuConsole();
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }
}
