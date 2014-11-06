/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import managers.films.FilmManager;
import managers.users.UserManager;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public abstract class ModuleMenuManager extends ModuleManager{
  

  protected Menu menu;

  public ModuleMenuManager(LoggedModel log) {
    super(log);
  }

   public void updateMenu() {  }
}
