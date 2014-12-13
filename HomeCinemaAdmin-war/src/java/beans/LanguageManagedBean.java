/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.Lang;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;



/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class LanguageManagedBean {

    private String localeCode;


 

    public String getLocaleCode() {
	return localeCode;
    }

    public void setLocaleCode(String localeCode) {
	this.localeCode = localeCode;
    }



    public LanguageManagedBean() {
	localeCode = "en";
    }
    
    public Lang getLang()
    {
	if (isFR())
	    return Lang.FR;
	else
	    return Lang.EN;
    }

    public boolean isFR() {
	return localeCode.equals("fr");
    }

    public boolean isEN() {
	return localeCode.equals("en");
    }
}
