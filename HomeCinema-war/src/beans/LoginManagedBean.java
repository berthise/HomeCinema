/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.UserDto;
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
public class LoginManagedBean {

    private UserDtoNoPw user;
    private String password;
    private String newEmail;
    private String oldPassword;
    private String newPassword;


    public LoginManagedBean() {
	user = new UserDto();
    }

    public void login() {
	try {
	  // TOOD UserDtoNoPw
	    user = (UserDtoNoPw) Ejbs.user().login(user.email, password);
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
	    user = (UserDtoNoPw) Ejbs.user().login("rob@mail.net", "password");
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

    public void changeEmail() {
        FacesMessage message;
        if (Ejbs.user().changeEmail(user.id, newEmail, oldPassword)) {
            user.setEmail(newEmail);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Adresse mail changée avec succès", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mot de passe incorrect !", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void changePassword() {
        FacesMessage message;
        if (Ejbs.user().changePassword(user.id, oldPassword, newPassword)) {
           // user.setPassword(newPassword);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mot de passe changé avec succès", null);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Votre ancien mot de passe est incorrect !", null);
        }
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

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
    public void caddySizePlus() {
      user.caddieSize ++;
    }
    
    public void caddySizeMinus() {
      user.caddieSize --;
    }
    
    public Integer getCaddySize() {
      return user.caddieSize;
    }
}
