/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

/**
 *
 * @author seb
 */
public class ReturnManager extends ModuleManager {

  public ReturnManager() {
    super("return");
  }

  
  @Override
  public void exec() throws ReturnException{ 
    throw new ReturnException();
  }

  public static class ReturnException extends Exception {

    public ReturnException() {
    }
  }
}
