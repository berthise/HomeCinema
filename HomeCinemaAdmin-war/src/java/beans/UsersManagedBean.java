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
  public Boolean caddieFictif = false;
  

  public Integer limit = 20;
  public Integer offset = 0;

  public UsersManagedBean() throws NamingException {
    userManager = (ManageUserRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
    this.users = userManager.getAllUser(offset,limit);
  }

  public Set<SimpleUserDto> getUsers() {
    return this.users;
  }

  public void setUsers(Set<SimpleUserDto> array) {
    this.users = array;
  }

  public Long getTotal() {
    return userManager.countAllUser();
  }

  public void delUser(Long id) {
    userManager.removeUser(id);
    this.users = userManager.getAllUser(offset,limit);
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
  
  public void reload() {
        this.users = userManager.getAllUser(offset,limit);

  }

  public void createNbUserFictif() {
    
    if (userFictif.isEmpty()) {
      FacesMessage message = new FacesMessage("Donnez un nom au utilisateurs");
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    try {
    Ejbs.user().createNbFictiveUsers(userFictif, nbUserFictif, activateFictif, caddieFictif);
    this.reload();
    } catch (SignupEmailException ex) {
	FacesMessage message = new FacesMessage("Utilisateur " + userFictif + "%%%%%% existe déja !");
        FacesContext.getCurrentInstance().addMessage(null, message);
	Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void deleteUserFictif() {
    if (userFictif.isEmpty()) {
      FacesMessage message = new FacesMessage("Donnez le nom des utilisateur a sup");
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    Integer nb = Ejbs.user().deleteFictiveUsers(userFictif);
    this.reload();
          FacesMessage message = new FacesMessage(nb+ " utilisateur sup");
      FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Boolean getCaddieFictif() {
    return caddieFictif;
  }

  public void setCaddieFictif(Boolean caddieFictif) {
    this.caddieFictif = caddieFictif;
  }


  
}
