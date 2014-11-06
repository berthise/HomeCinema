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
            FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
        }
        FilmDto f = filmManager.getDtoFromId(fdto.id);
        if (f == null) {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
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
    
    public String getLinkToFiche (){
        return "<a title=\"Voir la fiche du film\" href=\"fiche_film.xhtml?id="+fdto.id+"\"/>"+fdto.title+"</a>";
    }
    
    public String getLinkToVisionneuse (){
        return "<a class=\"btn btn-success col-md-3 b21\" href=\"visionneuse.xhtml?id="+fdto.id+"\">Voir en Streaming</a>";
    }
    
    public String getLinkToDownload (){
        // récupérer l'url de la vidéo
        String url = "Inconnu";
        return "<a class=\"btn btn-primary col-md-3 b22\" href=\""+url+"\" download=\""+fdto.title.replaceAll(" ", "_")+".mp4"+"\">Téléchargement</a>";
    }
    
    public String getTrailer (){
        // récupérer l'url du trailer
        String url = "http://techslides.com/demos/sample-videos/small.webm";
        return "<source src=\""+url+"\" type=\"video/webm\" />";
    }
    
    public String getVideo (){
        // récupérer l'url du trailer
        String url = "http://techslides.com/demos/sample-videos/small.webm";
        return "<source src=\""+url+"\" type=\"video/webm\" />";
    }

    public String getRating() {
        String toReturn = "";
        int pe = (int) Math.floor(fdto.rating);
        int i;
        for (i = 0; i < pe; i++) {
            toReturn += "<img src=\"img/star-full-icon.png\"/>\n";
        }
        if (fdto.rating - pe > 0.5) {
            toReturn += "<img src=\"img/star-half-full-icon.png\"/>\n";
        }
        for (; i < 10; i++) {
            toReturn += "<img src=\"img/star-empty-icon.png\"/>\n";
        }
        return toReturn + "<p>(" + fdto.rating + "/10)</p>\n";
    }

    public String getRuntime() {
        int h = fdto.runtime / 60;
        int min = fdto.runtime % 60;
        return h + "h " + min + "min";
    }

    public String getDirector() {
        // récupérer le director dans la BDD
        String d = "Inconnu";
        return "<a href=\"#\" class=\"list-name\">" + d + "</a>";
    }

    public String getCasting() {
        // récupérer le vrai casting dans la BDD
        String[] d = {"Inconnu", "Inconnu", "Inconnu", "Inconnu", "Inconnu", "Inconnu", "Inconnu", "Inconnu"};
        String toReturn = "";
        for (String s : d) {
            toReturn += "<a href=\"#\" class=\"list-name\">" + s + "</a> , ";
        }
        return toReturn.substring(0, toReturn.length() - 3);
    }

    public String getGenres() {
        // récupérer la vraie liste de genres dans la BDD
        String[] d = {"Inconnu", "Inconnu", "Inconnu", "Inconnu"};
        String toReturn = "";
        for (String s : d) {
            toReturn += "<a href=\"#\" class=\"list-genre\">" + s + "</a> , ";
        }
        return toReturn.substring(0, toReturn.length() - 3);
    }
}
