/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.UserDtoNoPw;
import enums.UserStates;
import exception.RetrieveCodeException;
import exception.UncorrectPasswordException;
import exception.UnknownAccountException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static utils.Beans.getRequestPage;
import utils.Lang;
import utils.Message;
import utils.Pages;
import utils.Redirect;
import utils.Securite;
import utils.SendMail;
import utils.SendMailException;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class SessionManagedBean {

    private UserDtoNoPw user = new UserDtoNoPw();

    public enum SessionStates {

	LOGGED,
	NOT_LOGGED,
	LOGGED_PAY;
    }

    public void openPaiement() {
	if (getSessionState() == SessionStates.LOGGED && user.caddieSize > 0) {
	    user.setState(UserStates.Payment);
	    // TODO change state in ejbs
	    Redirect.redirectTo(Pages.PAYMENT);
	}
    }

    public void closePaiement() {
	if (getSessionState() == SessionStates.LOGGED_PAY) {

	    // TODO change state in ejbs
	    user.caddieSize = 0;
	    user.filmsSize = Ejbs.user().countFilms(user.id);
	    user.setState(UserStates.Activated);
	    Redirect.redirectTo(Pages.MON_COMPTE);
	}
    }

    public void cancelPaiement() {
	if (getSessionState() == SessionStates.LOGGED_PAY) {
	    Message.Error(Lang.getString("session-bean-error"));
	    // TODO change state in ejbs

	    user.setState(UserStates.Activated);
	}
    }

    public Boolean login(LoginManagedBean login) {
	try {
	    String ep = Securite.crypte(login.getPassword());

	    user = (UserDtoNoPw) Ejbs.user().login(login.getEmail(), ep);
	    return true;
	} catch (UncorrectPasswordException ex) {
	    Logger.getLogger(SessionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    public void retrievePass(ForgotPassManagedBean forgot) {
	try {
	    String newpass = Ejbs.user().retrievePassword(forgot.getEmail());
	    SendMail.send(forgot.getEmail(), "[HomeCinéma] " + Lang.getString("session-bean-email-1"),
		    Lang.getString("session-bean-email-2") + " \n\n"
		    + Lang.getString("session-bean-email-3") + ":\n"
		    + ForgotPassManagedBean.getUrl(newpass)
		    + "\n\n"
		    + Lang.getString("session-bean-email-4") + ",\n"
		    + "HomeCinéma");
	    Message.Info(Lang.getString("session-bean-info"));
	    Redirect.redirectTo(Pages.INDEX);

	} catch (SendMailException ex) {
	    Message.Info(Lang.getString("session-bean-error-email"));

	} catch (UnknownAccountException ex) {
	    Message.Error(Lang.getString("session-bean-error-compte"));
	}
    }

    public void changePassRetrieve(ForgotPassManagedBean forgot) {
	if (!forgot.getP1().equals(forgot.getP2())) {
	    Message.Error(Lang.getString("session-bean-error-mdp"));
	} else {
	    try {
		String p = Securite.crypte(forgot.getP1());
		Ejbs.user().changePasswordRetrieve(forgot.getCode(), p);
		Message.Info(Lang.getString("session-bean-info-succes"));
		Redirect.redirectTo(Pages.INDEX);
	    } catch (RetrieveCodeException ex) {
		Message.Error(Lang.getString("session-bean-error-recup"));
		Redirect.redirectTo(Pages.INDEX);
	    }
	}
    }

    public void logout() {

	Message.Info(Lang.getString("session-bean-bye") + " " + user.nickName + " !");
	user = new UserDtoNoPw();

	Redirect.redirectIfNeeded(getSessionState());

    }

    public Boolean changeEmail(InfoUserManagedBean info) {
	String ep = Securite.crypte(info.getPassword());

	Boolean ret = Ejbs.user().changeEmail(user.id, info.getNewEmail(), ep);
	if (ret) {
	    user.setEmail(info.getNewEmail());
	}

	return ret;
    }

    public Boolean changePassword(InfoUserManagedBean info) {
	String ep = Securite.crypte(info.getPassword());
	String newep = Securite.crypte(info.getNewPassword());
	Boolean ret = Ejbs.user().changePassword(user.id, newep, ep);
	if (ret) {
	    user.setEmail(info.getNewEmail());
	}

	return ret;
    }

    public void checkRight() {
	if (getSessionState() == SessionStates.LOGGED_PAY && !getRequestPage().equals(Pages.PAYMENT)) {
	    this.cancelPaiement();
	}
	Redirect.redirectIfNeeded(getSessionState());

    }

    public Boolean checkPaymentCanceled() {
	if (getSessionState() != SessionStates.LOGGED_PAY && getRequestPage().equals(Pages.PAYMENT)) {
	    Message.Error(Lang.getString("session-bean-cancel"));
	    Redirect.redirectTo(Pages.MON_COMPTE);
	    return true;
	}
	return false;

    }

    private SessionStates getSessionState() {
	if (user.id != null) {
	    if (this.getState() == UserStates.Payment) {
		return SessionStates.LOGGED_PAY;
	    }
	    return SessionStates.LOGGED;
	} else {
	    return SessionStates.NOT_LOGGED;
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
    
    public Integer getFilmsSize() {
	return user.filmsSize;
    }
}
