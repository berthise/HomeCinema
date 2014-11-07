/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import java.util.ArrayList;
import main.utils.ReturnManager.ReturnException;

/**
 *
 * @author seb
 */
abstract public class Wizard extends ModuleManager implements Wizard_if {

  ArrayList<Manager_if> managers = null;
  String name;

//  public Wizard(String wizardName) {
//    this("wizard", wizardName);
//  }
  public Wizard(String entryName, String wizardName) {
    super(entryName);
    managers = new ArrayList<>();
    this.name = wizardName;
  }

  public void addManager(Manager_if m) {
    managers.add(m);
  }

  public void exec() throws ReturnException, AbortException {
    try {

      this.createWizard();
	System.out.println("---------------------------------");
	System.out.println("Wizard :" + this.name);
	System.out.println("---------------------------------");

	for (Manager_if m : managers) {
	  m.exec();
	  this.updateWizard();
	}

    } catch (ReturnException e) {
    } catch (AbortException e) {
      	System.out.println("Abordting ...");
    }
  }

  public void cleanWizard() {
    managers = new ArrayList<>();
  }

}
