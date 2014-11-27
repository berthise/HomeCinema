/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.UserDtoNoPw;
import enums.UserStates;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class SessionManagedBean {

  private UserDtoNoPw user = new UserDtoNoPw();

  public Boolean login(LoginManagedBean login) {
    try {
      user = (UserDtoNoPw) Ejbs.user().login(login.getEmail(), login.getPassword());
      return true;
    } catch (EJBException e) {
      return false;
    }
  }

  public void logout() {

    try {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Au revoir " + user.nickName + " !", null);
      FacesContext.getCurrentInstance().addMessage(null, message);
      user = new UserDtoNoPw();

      FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
      FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    } catch (IOException ex) {
      Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Boolean changeEmail(InfoUserManagedBean info) {
    Boolean ret = Ejbs.user().changeEmail(user.id, info.getNewEmail(), info.getPassword());
    if (ret) {
      user.setEmail(info.getNewEmail());
    }

    return ret;
  }

  public Boolean changePassword(InfoUserManagedBean info) {
    Boolean ret = Ejbs.user().changePassword(user.id, info.getNewPassword(), info.getPassword());
    if (ret) {
      user.setEmail(info.getNewEmail());
    }

    return ret;
  }
  
  public void checkConnected() throws IOException {
    if (user.id == null) {
      FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
  }

  public void checkCaddyPaiement() throws IOException {
    if (user.caddieSize == 0) {
      FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
  }


  public Long getId() {
    return user.id;
  }

  public void setId(Long id) {
    user.id = id;
  }

  public String getNickName() {
    return user.nickName;
  }

  public void setNickName(String nickName) {
    user.nickName = nickName.trim();
  }

  public String getFirstName() {
    return user.firstName;
  }

  public void setFirstName(String firstName) {
    user.firstName = firstName.trim();
  }

  public String getLastName() {
    return user.lastName;
  }

  public void setLastName(String lastName) {
    user.lastName = lastName.trim();
  }

  public String getEmail() {
    return user.email;
  }

  public void setEmail(String email) {
    user.email = email.trim();
  }

  public Date getBirthDate() {
    return user.birthDate;
  }

  public void setBirthDate(Date birthDate) {
    user.birthDate = birthDate;
  }

  public Date getAddDate() {
    return user.addDate;
  }

  public void setAddDate(Date addDate) {
    user.addDate = addDate;
  }

  public UserStates getState() {
    return user.state;
  }

  public void setState(UserStates state) {
    user.state = state;
  }

  public void caddySizePlus() {
    user.caddieSize++;
  }

  public void caddySizeMinus() {
    user.caddieSize--;
  }

  public Integer getCaddySize() {
    return user.caddieSize;
  }
}
