/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films;

import dtos.FilmDto;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.HomeCinemaClient;
import main.utils.ModuleManager;
import models.FilmModel;

/**
 *
 * @author seb
 */
public class SelectFilmManager extends ModuleManager {

  FilmModel film;

  public SelectFilmManager(FilmModel f) {
    this("select a film", f);
  }

  public SelectFilmManager(String name, FilmModel f) {
    super(name);
    this.film = f;
  }

  @Override
  public void exec() {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Which film number:");
      Long id = reader.nextLong();
      film.initDto();
      // TODO set film from ejb
      //model.setId();
      FilmDto fdto = HomeCinemaClient.getManageFilmRemote().getDtoFromId(id);
      film.setfDto(fdto);
    } catch (InputMismatchException e) {
      System.out.println("Error: wrong film selection");
    }
  }

}
