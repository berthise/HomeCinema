/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add;

import managers.users.*;
import models.UserModel;
import dtos.UserDto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.LoggedModel;
import models.ProductModel;

/**
 *
 * @author seb
 */
public class DisplayProductManager extends ModuleManager implements Manager_if {

  ProductModel product = null;

  public DisplayProductManager(LoggedModel log, ProductModel model) {
    super(log);
    product = model;
  }

  @Override
  public String getMenuEntry() {
    return "display product";
  }

  @Override
  public void runMenuEntry() {
    product.displayConsole();

  }

}
