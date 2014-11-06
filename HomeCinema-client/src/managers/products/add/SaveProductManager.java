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
public class SaveProductManager extends ModuleManager implements Manager_if {

  ProductModel product = null;

  public SaveProductManager(LoggedModel log, ProductModel product) {
    super(log);
    this.product = product;
  }

  @Override
  public String getMenuEntry() {
    return "save product";
  }

  @Override
  public void runMenuEntry() {
     System.out.println("Saving is done");
  }

}
