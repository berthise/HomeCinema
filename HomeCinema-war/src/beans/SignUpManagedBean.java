/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.UserDto;
import ejbs.ManageUserRemote;
import enums.UserStates;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class SignUpManagedBean {

    private UserDto user;
    private String birthDay;

    private static ManageUserRemote mur = null;

    public SignUpManagedBean() {
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

    public void singUp() {
        user.addDate = new Date();
        convertDate(birthDay);
        user.state = UserStates.Unactived;
        mur.signUp(user);
        FacesMessage message = new FacesMessage("Succès de l'inscription !");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void convertDate(String birthDay) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
            user.birthDate = df.parse(birthDay);
        } catch (ParseException ex) {
            ex.printStackTrace();
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
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
