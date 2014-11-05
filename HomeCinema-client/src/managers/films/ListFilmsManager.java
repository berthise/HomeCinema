/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import managers.users.*;
import models.SelectedUserModel;
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
import models.SelectedFilmModel;

/**
 *
 * @author seb
 */
public class ListFilmsManager extends ModuleManager implements Manager_if {

SelectedFilmModel model = null;

  public ListFilmsManager(LoggedModel log, SelectedFilmModel model) {
    super(log);
    this.model = model;
  }

  @Override
  public String getMenuEntry() {
    return "list films";
  }

  @Override
  public void runMenuEntry() {
    System.out.println("List films .......");
    // if selected film, then add a * to it 
  }

}
