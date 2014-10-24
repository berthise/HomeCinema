/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import ejbs.ManageFilmRemote;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@SessionScoped
public class FilmManagedBean {

    
    private ManageFilmRemote filmManager;

    public FilmDto fdto;

    public FilmManagedBean() throws NamingException {
        filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
        this.fdto = new FilmDto();
    }

    public String getTitle() {
        return fdto.title;
    }

    public void setTitle(String s) {
        fdto.title = s;
    }

    public void addFilm() {
        filmManager.createFilm(fdto);
    }
}