/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import exception.ActivatedCodeException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import utils.EjbException;
import utils.Message;
import utils.Pages;
import utils.Redirect;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class ActivateUserManagedBean {

  private Long id = (long) 0;
  private String code = null;

  private final static String USER = "u";
  private final static String CODE = "c";

  public static String getUrl(Long id, String code) {
    return Pages.ACTIVATE + "?" + ActivateUserManagedBean.USER + "="
	    + id + "&" + ActivateUserManagedBean.CODE + "=" + code;
  }

  public void activate() {
//    try {
      try {
	if (id != 0 && code != null) {
	  Ejbs.user().activate(id, code);
	  Message.Info("Succes de l'activation de l'utilisateur");
	} else {
	  Message.Error("Erreur lors de l'activation de l'utilisateur");
	}
      } catch (ActivatedCodeException ex) {
		  Message.Error("Erreur lors de l'activation de l'utilisateur");

	Logger.getLogger(ActivateUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
      }
//      FacesContext.getCurrentInstance().getExternalContext().dispatch(Pages.INDEX);
//    Redirect.redirectTo(Pages.INDEX);
//    } catch (IOException ex) {
//      Logger.getLogger(ActivateUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
