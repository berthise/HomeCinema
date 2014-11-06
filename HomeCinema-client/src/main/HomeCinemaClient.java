/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.utils.Manager_if;
import main.utils.ModuleManager;
import main.utils.Menu;
import main.utils.ExitManager;
import managers.loggin.LogginManager;
import dtos.FilmDto;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import main.utils.ModuleMenuManager;
import main.utils.ReturnManager.ReturnMenuException;
import managers.films.FilmManager;
import managers.products.ProductManager;
import managers.users.UserManager;
import models.LoggedModel;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class HomeCinemaClient extends ModuleMenuManager {

  public static void main(String[] args) {
    System.out.println("Application d'administration ++");
    HomeCinemaClient client = new HomeCinemaClient();
    client.runMenu();
  }

  public HomeCinemaClient() {
    super(new LoggedModel());
    menu = new Menu(this,log, "Main menu:");
  }

  public void runMenu() {
    // TODO: runMenuConsole + runMenuGraphique
    try {
    menu.addManager((Manager_if) new ExitManager(log));
    menu.addManager((Manager_if) new FilmManager(log));
    menu.addManager((Manager_if) new ProductManager(log));
    menu.addManager((Manager_if) new UserManager(log));
    //   menu.addManager((Manager_if) new LogginManager(log));
      menu.runMenuConsole();
    } catch (ReturnMenuException e) {
      System.out.println("bye");
    }
  }
}
