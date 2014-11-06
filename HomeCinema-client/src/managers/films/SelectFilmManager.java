/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.Manager_if;
import main.utils.ModuleManager;
import models.FilmModel;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class SelectFilmManager extends ModuleManager implements Manager_if {

  FilmModel film;

  public SelectFilmManager(LoggedModel log, FilmModel f) {
    super(log);
    this.film = f;
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
      Long id = reader.nextLong();
      film.initDto();
      // TODO set film from ejb
      //model.setId();
    } catch (InputMismatchException e) {
      System.out.println("Error: wrong film selection");
    }
  }

}
