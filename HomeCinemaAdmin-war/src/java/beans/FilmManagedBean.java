/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import dtos.UserDtoNoPw;
import ejbs.ManageFilmRemote;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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

    public void setDtoFromId() throws IOException {
        if (fdto.id != null) {

            FilmDto f = filmManager.getFilmFromId(fdto.id);
            fdto = f;
        }

    }

    public Long getId() {
        return fdto.id;
    }

    public void setId(Long id) {
        fdto.id = id;
    }

    public Double getRating() {
        return fdto.rating;
    }

    public void setRating(Double id) {
        fdto.rating = id;
    }

    public Integer getRuntime() {
        return fdto.runtime;
    }

    public void setRuntime(Integer id) {
        fdto.runtime = id;
    }

    public String getTitle() {
        return fdto.title;
    }

    public void setTitle(String s) {
        fdto.title = s;
    }

    public String getOverview() {
        return fdto.overview;
    }

    public void setOverview(String s) {
        fdto.overview = s;
    }

    public Date getRelease() {
        return fdto.release_date;
    }

    public void SetDate(Date d) {
        fdto.setRelease_date(d);
    }

    public String getReleaseDateString() {
        if (fdto.release_date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(fdto.release_date);
        }
    }

    public void setReleaseDateString(String s) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fdto.release_date = df.parse(s);
        } catch (ParseException ex) {
            fdto.release_date = new Date();
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() {
        filmManager.mergeOrSave(fdto);
        FacesMessage message = new FacesMessage("Succès de la modification !");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
