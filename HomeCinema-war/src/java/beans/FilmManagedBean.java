/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import ejbs.ManageFilmRemote;
import java.text.SimpleDateFormat;

import java.util.Date;
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
    
    public void setDtoFromId(){
        fdto = filmManager.getDtoFromId(fdto.id);
    }
    
    public void setId(Long i){
        fdto.id=i;
    }
    
    public Long getId(){
        return fdto.id;
    }
    
    public String getTitle() {
        return fdto.title;
    }

    public void setTitle(String s) {
        fdto.title = s;
    }
    
    public void setCover(String s) {
        fdto.cover=s;
    }
    
    public String getCover() {
        return fdto.cover;
    }
    
    public void setDate(Date d) {
        fdto.release_date=d;
    }
    
    public Date getDate() {
        return fdto.release_date;
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
}