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
public abstract class ModuleManager implements Manager_if {
  
  String entryName = ""; 
  ModuleManager() {
  }
  
  public ModuleManager(String entryName) {
    this.entryName = entryName;
  }

  public String getMenuEntry() {
    return entryName;
  }

  
  
}
