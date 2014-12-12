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
import utils.Cookies;


/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class LanguageManagedBean {

    private String localeCode;
    private static final String localeCookieName = "locale";
    private static final Integer localeCookieExpire = 60*60*24*365; // seconde

    private final Map<String, Object> countries;

    public Map<String, Object> getCountriesInMap() {
	return countries;
    }

    public String getLocaleCode() {
	return localeCode;
    }

    public void setLocaleCode(String localeCode) {
	this.localeCode = localeCode;
    }

    public void countryLocaleCodeChanged(String newLocaleValue) {
	setLocaleCode(newLocaleValue);

	for (Map.Entry<String, Object> entry : countries.entrySet()) {
	    if (entry.getValue().toString().equals(newLocaleValue)) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
	    }
	}
	
	Cookies.setCookie(LanguageManagedBean.localeCookieName, localeCode, LanguageManagedBean.localeCookieExpire);
    
	utils.Lang.reset();
    }

    public LanguageManagedBean() {
	localeCode = "fr";
	Cookie c = Cookies.getCookie(LanguageManagedBean.localeCookieName);
	if (c != null) {
	     localeCode = c.getValue();

	     if ( !localeCode.equals("fr") && !localeCode.equals("en"))
		 localeCode = "fr";
	}
	countries = new LinkedHashMap<>();
	countries.put("fr", Locale.FRENCH);
	countries.put("en", Locale.ENGLISH);
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
