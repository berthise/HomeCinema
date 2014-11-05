/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import managers.users.*;
import dtos.UserDto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.SelectedUserModel;
import models.LoggedModel;
import models.SelectedFilmModel;

/**
 *
 * @author seb
 */
public class SelectFilmManager extends ModuleManager implements Manager_if {

  SelectedFilmModel model;

  public SelectFilmManager(LoggedModel log, SelectedFilmModel model) {
    super(log);
    this.model = model;
  }

  @Override
  public String getMenuEntry() {
    return "select film";
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Which film number:");
      model = new SelectedFilmModel(reader.nextInt());
    } catch (InputMismatchException e) {
      System.out.println("Error: wrong film selection");
    }
  }

}
