/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import main.utils.ModuleManager;
import models.LoggedModel;

/**
 *
 * @author seb
 */
public class ReturnManager extends ModuleManager implements Manager_if {

  public ReturnManager(LoggedModel log) {
    super(log);
  }
  
  @Override
  public String getMenuEntry() {
    return "return";
  }
  
  @Override
  public void runMenuEntry() throws ReturnMenuException{ 
    throw new ReturnMenuException();
  }

  public static class ReturnMenuException extends Exception {

    public ReturnMenuException() {
    }
  }
}
