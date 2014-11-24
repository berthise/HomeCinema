/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class TabsManagedBean {

    private final List<String> listTabsFilms;
    private String tabFilms;

    private String tabAccount;
    private final List<String> listTabsAccount;

    public TabsManagedBean() {
	String[] ref1 = {"all", "top", "new", "pack"};
	listTabsFilms = Arrays.asList(ref1);
	tabFilms = "all";

	String[] ref2 = {"films", "caddie", "historique", "infos"};
	listTabsAccount = Arrays.asList(ref2);
	tabAccount = "films";
    }

    public String getTabFilms() {
	return tabFilms;
    }

    public void setTabFilms(String tabFilms) throws IOException {
	if (!(tabFilms == null || !listTabsFilms.contains(tabFilms)))
	    this.tabFilms = tabFilms;	    
	else
	    this.tabFilms = listTabsFilms.get(0);
	if (!FacesContext.getCurrentInstance().isPostback())
		FacesContext.getCurrentInstance().getExternalContext().redirect("films.xhtml");
    }

    public String getTabAccount() {
	return tabAccount;
    }

    public void setTabAccount(String tabAccount) {
	this.tabAccount = tabAccount;
    }

}
