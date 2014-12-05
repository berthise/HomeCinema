/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author seb
 */
public class Message {

  public static void Info(String message) {
    Info(message, null);
  }

  public static void Warning(String message) {
    Warning(message, null);
  }

  public static void Error(String message) {
    Error(message, null);
  }

  public static void Fatal(String message) {
    Fatal(message, null);
  }

  public static void Info(String message, String detail) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
	    message, detail);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public static void Warning(String message, String detail) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
	    message, detail);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public static void Error(String message, String detail) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
	    message, detail);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public static void Fatal(String message, String detail) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,
	    message, detail);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

}
