/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import utils.Message;
import utils.Pages;
import utils.Redirect;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class ContactManagedBean {

    String name, email, object, message;

    public ContactManagedBean() {
	name = email = object = message = null;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getObject() {
	return object;
    }

    public void setObject(String object) {
	this.object = object;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public void submit() {
	FacesContext context = FacesContext.getCurrentInstance();
	Application app = context.getApplication();
	ResourceBundle backendText = app.getResourceBundle(context, "msg");

	boolean state = true;
	if (state) {
	    Message.Info(backendText.getString("contact-success"));
	    Redirect.redirectTo(Pages.CONTACT);
	} else {
	    Message.Error(backendText.getString("contact-fail"));
	}
    }
}
