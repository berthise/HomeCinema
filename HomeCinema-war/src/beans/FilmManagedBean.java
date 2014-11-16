/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.FilmFicheDto;
import dtos.GenreDto;
import dtos.PersonDto;
import ejbs.ManageFilmRemote;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class FilmManagedBean {

    public FilmFicheDto fdto;

    public FilmManagedBean() throws NamingException {
        this.fdto = new FilmFicheDto();
    }

    public void setDtoFromId() throws IOException {
        if (fdto.id == null) {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
        }
        FilmFicheDto f = Ejbs.film().getDtoFromId(fdto.id);
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

    public Long getMainProductId() {
	return fdto.main_product_id;
    }

    public void setMainProductId(Long i) {
	fdto.main_product_id = i;
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
    
    public String getYear(FilmDto fdto) {
	SimpleDateFormat formater = new SimpleDateFormat("yyyy");
	return formater.format(fdto.release_date);
    }

    public String getOverview() {
	return fdto.overview;
    }

    public void setOverview(String s) {
	fdto.overview = s;
    }

    public String getLinkToFiche() {
	return "<a title=\"Voir la fiche du film\" href=\"fiche_film.xhtml?id=" + fdto.id + "\"/>" + fdto.title + "</a>";
    }

    public String getLinkToVisionneuse() {
	return "<a class=\"btn btn-success col-md-3 b21\" href=\"visionneuse.xhtml?id=" + fdto.id + "\">Voir en Streaming</a>";
    }

    public String getLinkToDownload() {
	String url = fdto.files.get(0).url;
	return "<a class=\"btn btn-primary col-md-3 b22\" href=\"" + url + "\" download=\"" + fdto.title.replaceAll(" ", "_") + ".mp4" + "\">Téléchargement</a>";
    }

    public String getVideo() {
	String url = fdto.files.get(0).url;
	return "<source src=\"" + url + "\" type=\"video/mp4\" />";
    }

    public String getTrailer() {
	String url = fdto.trailler.url;
	return "<source src=\"" + url + "\" type=\"video/mp4\" />";
    }

    public String getRating() {
	String toReturn = "";
	int pe = (int) Math.floor(fdto.rating);
	int i;
	for (i = 0; i < pe; i++) {
	    toReturn += "<img src=\"img/star-full-icon.png\"/>\n";
	}
	int m = 0;
	if (fdto.rating - pe > 0.5) {
	    toReturn += "<img src=\"img/star-half-full-icon.png\"/>\n";
	    m++;
	}
	for (; i < 10 - m; i++) {
	    toReturn += "<img src=\"img/star-empty-icon.png\"/>\n";
	}
	return toReturn + "<p>(" + fdto.rating + "/10)</p>\n";
    }

    public String getRuntime() {
	int h = fdto.runtime / 60;
	int min = fdto.runtime % 60;
	return h + "h " + min + "min";
    }
    
    public String getRuntime(FilmDto fdto) {
	int h = fdto.runtime / 60;
	int min = fdto.runtime % 60;
	return h + "h " + min + "min";
    }

    public String getDirector() {
	List<PersonDto> list = filmManager.getDirector(fdto.id);
	String toReturn = "";
	for (PersonDto s : list) {
	    toReturn += "<a href=\"#\" class=\"list-genres-crew\">" + s.name + "</a> , ";
	}
	if (toReturn.length() > 0)
	    return toReturn.substring(0, toReturn.length() - 3);
	else
	    return "<p class=\"list-genres-crew\">Inconnu</p>";
    }

    public String getCasting() {
	List<PersonDto> list = filmManager.getCasting(fdto.id);
	String toReturn = "";
	for (PersonDto s : list) {
	    toReturn += "<a href=\"#\" class=\"list-genres-crew\">" + s.name + "</a> , ";
	}
	if (toReturn.length() > 0)
	    return toReturn.substring(0, toReturn.length() - 3);
	else
	    return "<p class=\"list-genres-crew\">Inconnu</p>";
    }

    public String getGenres() {
	Set<GenreDto> set = filmManager.getGenre(fdto.id);
	String toReturn = "";
	for (GenreDto s : set) {
	    toReturn += "<a href=\"#\" class=\"list-genres-crew\">" + s.name + "</a> , ";
	}
	if (toReturn.length() > 0)
	    return toReturn.substring(0, toReturn.length() - 3);
	else
	    return "<p class=\"list-genres-crew\">Inconnu</p>";
    }
}
