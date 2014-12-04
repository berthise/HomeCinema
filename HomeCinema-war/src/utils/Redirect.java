/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.SessionManagedBean.SessionStates;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import static utils.Beans.getRequestPage;

/**
 *
 * @author seb
 */
public class Redirect {


  private static HashMap<SessionStates, HashMap<String, String>> redirectMap
	  = new HashMap<SessionStates, HashMap<String, String>>() {
	    {
	      put(SessionStates.NOT_LOGGED, new HashMap<String, String>() {
		{
		  put("moncompte.xhtml", "index.xhtml");
		  put("paiement.xhtml", "index.xhtml");
		}
	      });
	      put(SessionStates.LOGGED, new HashMap<String, String>() {
		{
		  put("login.xhtml", "moncompte.xhtml");
		  put("signup.xhtml", "moncompte.xhtml");
		  put("paiement.xhtml", "moncompte.xhtml");
		}
	      });
	      put(SessionStates.LOGGED_PAY, new HashMap<String, String>() {
		{
		  put("*", "paiement.xhtml");
		}
	      });
	    }
	  };

  public static void redirectIfNeeded(SessionStates type) {

    HashMap<String,String> redirectTable = redirectMap.get(type);
    
    String requestPage = getRequestPage();

   if (type == SessionStates.LOGGED_PAY && !requestPage.equals("paiement.xhtml")) {
      requestPage = "*";
    }
    
    if (redirectTable.containsKey(requestPage)) {
      try {
	FacesContext.getCurrentInstance().getExternalContext().
		getFlash().setKeepMessages(true);
	FacesContext.getCurrentInstance().getExternalContext().
		redirect(redirectTable.get(requestPage));
      } catch (IOException ex) {
	Logger.getLogger(Redirect.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public static void redirectTo(String to) {
    try {
    	FacesContext.getCurrentInstance().getExternalContext().
		getFlash().setKeepMessages(true);
	FacesContext.getCurrentInstance().getExternalContext().
		redirect(to);
	      } catch (IOException ex) {
	Logger.getLogger(Redirect.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

}
