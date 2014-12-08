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
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static utils.Beans.getRequestPage;
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
      user.setState(UserStates.Activated);
      Redirect.redirectTo(Pages.MON_COMPTE);
    }
  }

  public void cancelPaiement() {
    if (getSessionState() == SessionStates.LOGGED_PAY) {
      Message.Error("Annulation du paiement !");
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
      SendMail.send(forgot.getEmail(), "[HomeCinema] Changement du mot de passe",
	      "Bonjour \n\n"
	      + "Pour changer votre mot de passe:\n"
	      + ForgotPassManagedBean.getUrl(newpass)
	      + "\n\n"
	      + "Merci,\n"
	      + "HomeCinema");
      Message.Info("Vous allez recevoir un mail avec un lien pour changer votre mot de passe.");
      Redirect.redirectTo(Pages.INDEX);

    } catch (SendMailException ex) {
      Message.Info("Email inconnu !");

    } catch (UnknownAccountException ex) {
      Message.Error("Compte inconnu :");
    }
  }

  public void changePassRetrieve(ForgotPassManagedBean forgot) {
    if (!forgot.getP1().equals(forgot.getP2())) {
      Message.Error("La confirmation du mot de pass est incorreect !");
    } else {
      try {
	String p = Securite.crypte(forgot.getP1());
	Ejbs.user().changePasswordRetrieve(forgot.getCode(), p);
	Message.Info("Votre mot de passe a été changé avec succé !");
	Redirect.redirectTo(Pages.INDEX);
      } catch (RetrieveCodeException ex) {
	Message.Error("Le recuperation du mot de passe, incorrect !");
	Redirect.redirectTo(Pages.INDEX);

      }
    }
  }

  public void logout() {

    Message.Info("Au revoir " + user.nickName + " !");
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
      Message.Error("Votre paiment à été annulé !");
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
}
