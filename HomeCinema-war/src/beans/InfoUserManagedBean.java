/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static beans.LoginManagedBean.findBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author toure
 */
@ManagedBean
@ViewScoped
public class InfoUserManagedBean {

  private String password = "";
  private String newEmail = "";
  private String newPassword = "";
  private String confPassword = "";
  
  public InfoUserManagedBean() {
     SessionManagedBean session = findBean("sessionManagedBean");
     newEmail = session.getEmail();
  }

  public void changeEmail() {
    SessionManagedBean session = findBean("sessionManagedBean");
    FacesMessage message;

    if (session.changeEmail(this)) {
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Adresse mail changée avec succès", null);
    } else {
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mot de passe incorrect !", null);
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public void changePassword() {
    SessionManagedBean session = findBean("sessionManagedBean");
    FacesMessage message;
    
    if (session.changePassword(this)) {
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mot de passe changé avec succès", null);
    } else {
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Votre ancien mot de passe est incorrect !", null);
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNewEmail() {
    return newEmail;
  }

  public void setNewEmail(String newEmail) {
    this.newEmail = newEmail;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getConfPassword() {
    return confPassword;
  }

  public void setConfPassword(String confPassword) {
    this.confPassword = confPassword;
  }

}
