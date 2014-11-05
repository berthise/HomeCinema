/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import models.LoggedModel;

/**
 *
 * @author seb
 */
public class ExitManager extends ModuleManager implements Manager_if {

  public ExitManager(LoggedModel log) {
    super(log);
  }
  
  @Override
  public String getMenuEntry() {
    return "exit";
  }
  
  @Override
  public void runMenuEntry() throws ReturnManager.ReturnMenuException { 
    throw new ReturnManager.ReturnMenuException();
  }
}
