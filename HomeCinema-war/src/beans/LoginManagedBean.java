/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import static utils.Beans.findBean;
import utils.Message;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class LoginManagedBean {

  private String email = "";
  private String password = "";

  public void login() {
    SessionManagedBean session = findBean("sessionManagedBean");

    if (session.login(this)) {
      Message.Info("Bienvenue " + session.getNickName() + " !");
    } else {
      Message.Error("Identifiants incorrects !", "Email, login ou mot de passe incorrect.");
    }
    session.checkRight();
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
