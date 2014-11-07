/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.utils;

import main.utils.ReturnManager.ReturnException;

/**
 *
 * @author seb
 */
public interface Manager_if {
  
    public String getMenuEntry();
    
    public void exec() throws ReturnException, AbortException;
}
