/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import ejbs.ManageFilmRemote;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class FilmManagedBean {

    private ManageFilmRemote filmManager;

    public FilmDto fdto;

    public FilmManagedBean() throws NamingException {
        filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
        this.fdto = new FilmDto();
    }

    public void setDtoFromId() throws IOException {
        if (fdto.id == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath());
        }
        
        FilmDto f = filmManager.getDtoFromId(fdto.id);
        if (f == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/404.xhtml");
        }
        
        fdto = f;
    }

    public void setId(Long i) {
        fdto.id = i;
    }

    public Long getId() {
        return fdto.id;
    }

    public String getTitle() {
        return fdto.title;
    }

    public void setTitle(String s) {
        fdto.title = s;
    }

    public void setCover(String s) {
        fdto.cover = s;
    }

    public String getCover() {
        return fdto.cover;
    }

    public void setDate(Date d) {
        fdto.release_date = d;
    }

    public String getDate() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        return formater.format(fdto.release_date);
    }

    public String getYear() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy");
        return formater.format(fdto.release_date);
    }

    public String getOverview() {
        return fdto.overview;
    }

    public void setOverview(String s) {
        fdto.overview = s;
    }
    
    public String getRating(){
        String toReturn = "";
        for (int i=0;i<fdto.rating;i++)
            toReturn += "<img src=\"http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/128/star-full-icon.png\"/>\n";
        for (int i=0;i<10-fdto.rating;i++)
            toReturn += "<img src=\"http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/128/star-empty-icon.png\"/>\n";
        return toReturn + "<p>(" + fdto.rating + "/10)</p>\n";
    }
    
    public String getRuntime(){
        int h = fdto.runtime / 60;
        int min = fdto.runtime % 60;        
        return h + "h " + min + "min";
    }
    
    public String getDirector(){
        return "Inconnu";
    }
    
    public String getCasting(){
        return "Inconnu";
    }
    
    public String getGenres(){
        return "Inconnu";
    }
}
