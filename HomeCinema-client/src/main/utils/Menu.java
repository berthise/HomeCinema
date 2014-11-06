/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ReturnManager.ReturnMenuException;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class Menu {

  ArrayList<Manager_if> managers = null;
  String name;
  LoggedModel log;
  ModuleMenuManager module;

  public Menu(ModuleMenuManager module, LoggedModel log, String name) {
    managers = new ArrayList<>();
    this.name = name;
    this.log = log;
    this.module = module;
  }

  public void addManager(Manager_if m) {
    managers.add(m);
  }

  public void runMenuConsole() throws ReturnMenuException {
    while (true) {
      System.out.println("---------------------------------");
      System.out.println(this.name);
      System.out.println("---------------------------------");

      int i = 0;
      for (Manager_if m : managers) {
	System.out.println(i + ": " + m.getMenuEntry());
	i++;
      }

      Scanner reader = new Scanner(System.in);
      System.out.println("Faite votre choix:");
      try {
	Integer entre = reader.nextInt();
	try {
	  managers.get(entre).runMenuEntry();

	  /* Update menu, it may change */
	  module.updateMenu();
	} catch (IndexOutOfBoundsException e) {
	  System.out.println("Erreur: entré incorrect, valeur non possible.");
	}
      } catch (InputMismatchException e) {
	System.out.println("Erreur: entré incorrect, caratere non numerique.");
      }

    }
  }

  public void clean() {
    managers = new ArrayList<>();
  }
}
