/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.products.add;

import managers.films.*;
import UtilsJson.JsonReader;
import dtos.FilmDto;
import managers.users.*;
import models.UserModel;
import dtos.UserDto;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.FilmModel;
import models.LoggedModel;
import models.ProductModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author seb
 */
public class EditProductManager extends ModuleManager implements Manager_if {

  ProductModel product = null;

  public EditProductManager(LoggedModel log, ProductModel p) {
    super(log);
    product = p;
  }

  @Override
  public String getMenuEntry() {
    return "edit product";
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    product.initDto();
    try {
      System.out.println("Price:");
      product.setPrice(reader.nextInt());
      System.out.println("Name:");
      product.setName(reader.next());
    } catch (InputMismatchException e) {
      System.out.println("Error: abording product edition");
    }
  }
}
