/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.UserDto;
import ejbs.ManageUserRemote;
import enums.UserStates;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class LoginManagedBean {

    private UserDto user;

    private static ManageUserRemote mur = null;

    public LoginManagedBean() {
        if (mur == null) {
            try {
                InitialContext ic = new InitialContext();
                mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
        }
        user = new UserDto();
    }

    public void login() {
        user = mur.login(user.email, user.password);
        String message = (user.lastName == null) ? "Identifiants incorrects" : "Vous êtes maintenant connecté";
        FacesContext.getCurrentInstance().addMessage("login", new FacesMessage(message));
    }
    
    public String logout() {
        user = new UserDto();
        return "/HomeCinema/?faces-redirect=true";
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
}
