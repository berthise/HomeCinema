/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import ejbs.ManageFilmLocal;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author titou
 */
@ManagedBean
@SessionScoped
public class FilmManagedBean {

    @EJB
    private ManageFilmLocal filmManager;

    public FilmDto fdto;

    public FilmManagedBean() {
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