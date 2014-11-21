/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.UserDto;
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
public class LoginManagedBean {

    private UserDto user;

    public LoginManagedBean() {
	user = new UserDto();
    }

    public void login() {
	try {
	    user = Ejbs.user().login(user.email, user.password);
	    //user = mur.login("rob@mail.net", "password");
	    FacesMessage message = new FacesMessage("Bienvenue " + user.nickName + " !");
	    message.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (EJBException e) {
	    FacesMessage message = new FacesMessage("Identifiants incorrects !");
	    message.setSeverity(FacesMessage.SEVERITY_ERROR);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }

    
    public void tmpLogin() {
	try {
	    //user = Ejbs.user().login(user.email, user.password);
	    user = Ejbs.user().login("rob@mail.net", "password");
	    FacesMessage message = new FacesMessage("Bienvenue " + user.nickName + " !");
	    message.setSeverity(FacesMessage.SEVERITY_INFO);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (EJBException e) {
	    FacesMessage message = new FacesMessage("Identifiants incorrects !");
	    message.setSeverity(FacesMessage.SEVERITY_ERROR);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
    }
	
    public void logout() {

	try {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Au revoir " + user.nickName + " !", null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    user = new UserDto();

	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	} catch (IOException ex) {
	    Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	//return "/HomeCinema-war/?faces-redirect=true";
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

    public String getPassword() {
	return user.password;
    }

    public void setPassword(String password) {
	user.password = password;
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

    public void checkConnected() throws IOException {
	System.out.println("?????");
	if (user.id == null) {
	    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
    }
}
