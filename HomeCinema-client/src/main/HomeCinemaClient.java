/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import main.utils.ReturnManager.ReturnMenuException;

/**
 *
 * @author titou
 */
public class HomeCinemaClient {

  private static InitialContext ic;
  private static ManageProductRemote mpr = null;
  private static ManageFilmRemote mfr = null;


  public static void main(String[] args) {
    System.out.println("Application d'administration ++");
    MainMenu menu = new MainMenu();
    try {
      HomeCinemaClient.makeContext();
      menu.runMenuEntry();
    } catch (ReturnMenuException e) {
      System.out.println("bye");
    }
  }

  public static void makeContext() {
    try {
      Properties props = new Properties();
      //props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
      //props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
      //props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
      //props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
      //props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

      ic = new InitialContext();
    } catch (NamingException ex) {
      Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  // TOOD find an other solution.
  public static ManageProductRemote getManageProductRemote() {
    if (mpr == null) {
      try {
	mpr = (ManageProductRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageProduct!ejbs.ManageProductRemote");
      } catch (NamingException ex) {
	Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return mpr;
  }
  
    public static ManageFilmRemote getManageFilmRemote() {
    if (mfr == null) {
      try {
	mfr = (ManageFilmRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
      } catch (NamingException ex) {
	Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return mfr;
  }

}
