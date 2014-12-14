/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import static utils.Beans.findBean;
import utils.Lang;
import utils.Message;
import utils.Redirect;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class LoginManagedBean {

    private String email = "";
    private String password = "";

    public void login() {
	SessionManagedBean session = findBean("sessionManagedBean");

	if (session.login(this)) {
	    Message.Info(Lang.getString("login-bean-bienvenue") + " " + session.getNickName() + " !");
	} else {
	    Message.Error(Lang.getString("login-bean-error-1"), Lang.getString("login-bean-error-1"));
	}
//	     System.out.println("redirect to = "+((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI());
//	return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
	session.checkRight();
    }

    /*public void tmpLogin() {
	this.email = "rob@mail.net";
	this.password = "password";
	this.login();
    }*/

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
