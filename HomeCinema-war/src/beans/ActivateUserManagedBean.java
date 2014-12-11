/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import exception.ActivatedCodeException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import utils.Lang;
import utils.Message;
import utils.Pages;
import utils.Redirect;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class ActivateUserManagedBean {

    private Long id = (long) 0;
    private String code = null;

    private final static String USER = "u";
    private final static String CODE = "c";

    public static String getUrl(Long id, String code) {
	return "http://" + Pages.DOMAIN + Pages.ROOT + Pages.ACTIVATE + "?" + ActivateUserManagedBean.USER + "="
		+ id + "&" + ActivateUserManagedBean.CODE + "=" + code;
    }

    public void activate() {
	/* get pramaters comme cela car activate appeler en preValidate */
	HttpServletRequest req = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
	if (req.getParameter(ActivateUserManagedBean.USER) != null) {
	    id = Long.parseLong(req.getParameter(ActivateUserManagedBean.USER));
	    code = req.getParameter(ActivateUserManagedBean.CODE);
	}
	try {
	    if (id != 0 && code != null) {
		Ejbs.user().activate(id, code);
		Message.Info(Lang.getString("bean-activate-info"));
	    }
	} catch (ActivatedCodeException ex) {
	    Message.Error(Lang.getString("bean-activate-error"));

	    Logger.getLogger(ActivateUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	Redirect.redirectTo(Pages.INDEX);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

}
