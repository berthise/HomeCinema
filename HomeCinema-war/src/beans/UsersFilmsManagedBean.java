/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.UsersFilmsDto;
import ejbs.Ejbs;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class UsersFilmsManagedBean {

    UsersFilmsDto ufdto;

    public UsersFilmsManagedBean() {
	ufdto = new UsersFilmsDto();
    }

    public void updateCurrentTime() {
	if (ufdto.currentPosition != null) {
	    try {
		FacesContext fc = FacesContext.getCurrentInstance();
		Long user = Long.parseLong(getParam(fc, "user"));
		Long film = Long.parseLong(getParam(fc, "film"));
		ufdto.film = film;
		Ejbs.usersFilms().updateCurrentTime(user, ufdto);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public void getCurrentTime(Long user, Long film) {
	ufdto = Ejbs.usersFilms().getCurrentTime(user, film);
    }

    private String getParam(FacesContext fc, String param) {
	Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
	return (params.get(param)).trim();
    }

    public UsersFilmsDto getUfdto() {
	return ufdto;
    }

    public void setUfdto(UsersFilmsDto ufdto) {
	this.ufdto = ufdto;
    }
}
