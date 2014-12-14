/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.UserDtoNoPw;

import ejbs.ManageUserRemote;
import enums.UserStates;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class UserManagedBean {

  private ManageUserRemote userManager;

  private UserDtoNoPw user;

  public UserManagedBean() throws NamingException {
    userManager = (ManageUserRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
    this.user = new UserDtoNoPw();
  }

  public void setDtoFromId() throws IOException {
    if (user.id == null) {
      FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
    }
    UserDtoNoPw u = userManager.getUser(user.id);
    if (u == null) {
      FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
    }
    user = u;

  }

  public void save() {
    userManager.save(user);
    FacesMessage message = new FacesMessage("Succès de la modification !");
    FacesContext.getCurrentInstance().addMessage(null, message);
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

  public String getBirthDateString() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    return df.format(user.birthDate);
  }

  public Date getBirthDate() {
    return user.birthDate;
  }

    public void setBirthDateString(String s) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      user.birthDate = df.parse(s);
    } catch (ParseException ex) {
      user.birthDate = new Date();
      Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
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
  
  public boolean isActivate()
  {
      return user.state==UserStates.Activated;
  }
 
  
  public void activate()
  {
      this.userManager.activate(this.getId());
  }
  
    public void deactivate()
  {
      this.userManager.deactivate(this.getId());
  }

}
