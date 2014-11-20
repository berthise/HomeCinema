/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class LanguageManagedBean {

    private String localeCode = "fr";

    private static Map<String, Object> countries;

    static {
	countries = new LinkedHashMap<String, Object>();
	countries.put("fr", Locale.FRENCH); //label, value
	countries.put("en", Locale.ENGLISH);
    }

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
	switch (newLocaleValue) {
	    case "fr":
		this.lang = Lang.FR;
		break;
	    case "en":
		this.lang = Lang.EN;
		break;
	}
	setLocaleCode(newLocaleValue);
	
	for (Map.Entry<String, Object> entry : countries.entrySet()) {
	    if (entry.getValue().toString().equals(newLocaleValue)) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
	    }
	}
    }

    private enum Lang {

	FR, EN
    };

    private Lang lang;

    public LanguageManagedBean() {
	lang = Lang.FR;
    }

    public boolean isFR() {
	return lang == Lang.FR;
    }

    public boolean isEN() {
	return lang == Lang.EN;
    }
}
