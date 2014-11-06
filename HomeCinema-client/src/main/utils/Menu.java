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

/**
 *
 * @author seb
 */
abstract public class Menu extends ModuleManager implements Menu_if {

  ArrayList<Manager_if> managers = null;
  String name;

  public Menu(String menuName) {
    this("", menuName);
  }

  public Menu(String entryName, String menuName) {
    super(entryName);
    managers = new ArrayList<>();
    this.name = menuName;
  }

  public void addManager(Manager_if m) {
    managers.add(m);
  }

  public void runMenuEntry() throws ReturnMenuException {
    try {

      this.createMenu();
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
	    this.updateMenu();
	    /* Update menu, it may change */
	  } catch (IndexOutOfBoundsException e) {
	    System.out.println("Erreur: entré incorrect, valeur non possible.");
	  }
	} catch (InputMismatchException e) {
	  System.out.println("Erreur: entré incorrect, caratere non numerique.");
	}

      }
    } catch (ReturnManager.ReturnMenuException e) {
    }
  }

  public void cleanMenu() {
    managers = new ArrayList<>();
  }
}
