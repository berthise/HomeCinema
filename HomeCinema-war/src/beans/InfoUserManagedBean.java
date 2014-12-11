/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import static utils.Beans.findBean;
import utils.Lang;
import utils.Message;

/**
 *
 * @author toure
 */
@ManagedBean
@ViewScoped
public class InfoUserManagedBean {

    private String password = "";
    private String newEmail = "";
    private String newPassword = "";
    private String confPassword = "";

    public InfoUserManagedBean() {
	SessionManagedBean session = findBean("sessionManagedBean");
	newEmail = session.getEmail();
    }

    public void changeEmail() {
	SessionManagedBean session = findBean("sessionManagedBean");

	if (session.changeEmail(this)) {
	    Message.Info(Lang.getString("infouser-info"));
	} else {
	    Message.Error(Lang.getString("infouser-erreur"));
	}
    }

    public void changePassword() {
	SessionManagedBean session = findBean("sessionManagedBean");

	if (session.changePassword(this)) {
	    Message.Info(Lang.getString("infouser-password-info"));
	} else {
	    Message.Error(Lang.getString("infouser-password-error"));
	}
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
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

    public String getConfPassword() {
	return confPassword;
    }

    public void setConfPassword(String confPassword) {
	this.confPassword = confPassword;
    }

}
