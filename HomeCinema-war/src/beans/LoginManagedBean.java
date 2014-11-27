/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class LoginManagedBean {

  @SuppressWarnings("unchecked")
  public static <T> T findBean(String beanName) {
    FacesContext context = FacesContext.getCurrentInstance();
    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
  }

  private String email = "";
  private String password = "";

    public void login() {
    SessionManagedBean session = findBean("sessionManagedBean");
    FacesMessage message;
    
    if (session.login(this)) {
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue " + session.getNickName() + " !",  null);
    } else {
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiants incorrects !", null);
    }
    FacesContext.getCurrentInstance().addMessage(null, message);
  }


  public void tmpLogin() {
    this.email = "rob@mail.net";
    this.password = "password";
    this.login();
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
