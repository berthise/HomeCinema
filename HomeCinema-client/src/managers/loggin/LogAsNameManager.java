/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.loggin;

import models.*;
import java.util.Scanner;
import main.utils.Manager_if;
import main.utils.ModuleManager;

/**
 *
 * @author seb
 */
public class LogAsNameManager extends ModuleManager implements Manager_if {


  public LogAsNameManager(LoggedModel log) {
    super(log);
  }

  @Override
  public String getMenuEntry() {
    return "log as name user";
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);

    System.out.println("Log as:");
    log.connect(reader.nextLine());
    System.out.println("Loggin done.");
  }

}
