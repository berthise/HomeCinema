/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.SimpleUserDto;

import ejbs.ManageUserRemote;
import java.util.Set;
import javax.faces.application.FacesMessage;
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
public class UsersManagedBean {

  private ManageUserRemote userManager;

  private Set<SimpleUserDto> users;

  public UsersManagedBean() throws NamingException {
    userManager = (ManageUserRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
    this.users = userManager.getAllUser(false);
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
    // userManager.removeUser(id);
    FacesMessage message = new FacesMessage("Succès de la suppresion ! TODO: decommenter instruction");
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

}
