/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.SessionManagedBean;
import beans.SessionManagedBean.SessionStates;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import static org.omg.IOP.CodecPackage.TypeMismatchHelper.type;
import static utils.Beans.findBean;
import static utils.Beans.getRequestPage;

/**
 *
 * @author seb
 */
public class Redirect {


//  static class RedirectTable {
//
//    static String to;
//    static List<String> from;
//
//    Boolean contains(String page) {
//      return from.contains(page);
//    }
//
//    String gotTo() {
//      return to;
//    }
//  }

//  private static final HashMap<RedirectType, RedirectTable> redirectMap
//	  = new HashMap<RedirectType, RedirectTable>() {
//	    {
//	      put(RedirectType.LOGIN, new RedirectTable() {
//		{
//		  to = "moncompte.xhtml";
//		  from = new ArrayList<String>() {
//		    {
//		      add("login.xhtml");
//		    }
//		  };
//		}
//	      });
//	      put(RedirectType.LOGOUT, new RedirectTable() {
//		{
//		  to = "index.xhtml";
//		  from = new ArrayList<String>() {
//		    {
//		      add("moncompte.xhtml");
//		    }
//		  };
//		}
//	      });
//	    }
//	  };
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
