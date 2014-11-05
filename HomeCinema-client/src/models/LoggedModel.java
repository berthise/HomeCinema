/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author seb
 */
public class LoggedModel {
  UserModel userModel = null;
  ArrayList<Integer> caddy = null;
  
  public void connect(Integer userId) {
    // get ebjs ....
    userModel = new UserModel();
    userModel.setFirstname("Alice");
    userModel.setLastname("Free");
    userModel.setEmail("alice.free@debian.org");
  }
  
    public void connect(String firstname) {
    // get ebjs ....
    userModel = new UserModel();
    userModel.setFirstname("Bob");
    userModel.setLastname("Marley");
    userModel.setEmail("bob.marley@debian.org");
  }
  
  public void disconnect() {
    userModel = null;
  }
  
  public Boolean isLogged () {
    return userModel != null;
  }
  
  public void displayLogInfo() {
    if ( userModel != null ) {
      System.out.println("Loggé sous le nom de " + userModel.getFirstname());
    } else {
      System.out.println("Pas loggé");

    }
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public ArrayList<Integer> getCaddy() {
    return caddy;
  }
  
  
}
