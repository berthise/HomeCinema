/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.SimpleUserDto;
import dtos.UserDto;
import ejbs.Ejbs;

import ejbs.ManageUserRemote;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import utils.Securite;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class UsersManagedBean {

  private ManageUserRemote userManager;

  private Set<SimpleUserDto> users;
  
  public String userFictif = "";
  public Integer nbUserFictif = 10;
  public Boolean activateFictif = false;

  public UsersManagedBean() throws NamingException {
    userManager = (ManageUserRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
    this.users = userManager.getAllUser();
  }

  public Set<SimpleUserDto> getUsers() {
    return this.users;
  }

  public void setUsers(Set<SimpleUserDto> array) {
    this.users = array;
  }

  public Integer getTotal() {
    return this.users.size();
  }

  public void delUser(Long id) {
    userManager.removeUser(id);
    this.users = userManager.getAllUser();
    FacesMessage message = new FacesMessage("Succès de la suppresion !");
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public String getUserFictif() {
    return userFictif;
  }

  public void setUserFictif(String userFictif) {
    this.userFictif = userFictif;
  }

  public Integer getNbUserFictif() {
    return nbUserFictif;
  }

  public void setNbUserFictif(Integer nbUserFictif) {
    this.nbUserFictif = nbUserFictif;
  }

  public Boolean getActivateFictif() {
    return activateFictif;
  }

  public void setActivateFictif(Boolean activateFictif) {
    this.activateFictif = activateFictif;
  }
  

  public void createNbUserFictif() {
    String pp = Securite.crypte("password");

    if (userFictif == "") {
      FacesMessage message = new FacesMessage("Donnez un nom au utilisateurs");
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    UserDto user = new UserDto();
    for (int i = 1; i<=nbUserFictif; i++) {
      try {
	user = new UserDto();
	user.birthDate = new Date();
	user.nickName = userFictif + String.format("%06d", i);
	user.email = user.nickName + "@mailquinexistepas.net";
	user.password = pp;
	user.lastName = "lastname";
	user.firstName = "firstname";
	user = Ejbs.user().signUp(user);
	if ( activateFictif ) {
	  Ejbs.user().activate(user.id);
	}
	this.users = userManager.getAllUser();
      } catch (SignupEmailException | SignupNickNameException ex) {
	FacesMessage message = new FacesMessage("Utilisateur " + user.nickName + " existe déja !");
        FacesContext.getCurrentInstance().addMessage(null, message);
	Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	return ;
      }
    }

  }


  
}
