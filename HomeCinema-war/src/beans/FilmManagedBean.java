/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.GenreDto;
import dtos.PersonDto;
import dtos.ProductDto;
import dtos.VideoDto;
import ejbs.Ejbs;
import enums.Langs;
import enums.VideoFormat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import static utils.Beans.findBean;
import utils.Lang;
import utils.Pages;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class FilmManagedBean {

    public FilmFicheDto fdto;
//    public ProductDto pdto;

    LanguageManagedBean lang = findBean("languageManagedBean");
    
    public FilmManagedBean() throws NamingException {
	this.fdto = new FilmFicheDto();
    }

    public void setDtoFromId() throws IOException {
	if (fdto.id == null || fdto.id == 0) {
	    FacesContext.getCurrentInstance().getExternalContext().dispatch(Pages.NOT_FOUND);
	}
	FilmFicheDto f = Ejbs.film().getDtoFromId(fdto.id,lang.getLang());
	if (f == null) {
	    FacesContext.getCurrentInstance().getExternalContext().dispatch(Pages.NOT_FOUND);
//	} else {
//	  pdto = Ejbs.product().getProduct(f.id, lang.getLang());
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
	return "<a title=\"" + Lang.getString("fiche-film-see") + "\" href=\"" + Pages.FICHE_FILM + "?id=" + fdto.id + "\"/>" + fdto.title + "</a>";
    }

    public String getLinkToDownload() {
	return this.fdto.title.replaceAll(" ", "_") + ".mp4";
    }

    public String getUrlDownload() {
	return this.fdto.videos.get(0).url;
    }

    public List<VideoDto> getVideos() {
      return fdto.videos;
    }
    
    
    public List<Langs> getVideoLangs() {
      ArrayList<Langs> langs = new ArrayList<>();
      for ( VideoDto v: fdto.videos) {
	if (!langs.contains(v.audio))
	langs.add(v.audio);
      }
      Collections.sort(langs);

      return langs;
    }
    
    public List<Integer> getVideoQualities(Langs lang) {
      ArrayList<Integer> qualities = new ArrayList<>();
      for ( VideoDto v: fdto.videos ) {
	if ( !qualities.contains(v.resolution) && (v.audio == lang || lang == null))
	  qualities.add(v.resolution);
      }
      Collections.sort(qualities);
      return qualities;
    }
    
    public List<VideoFormat> getVideoFormat(Langs lang, Integer quality) {
      ArrayList<VideoFormat> formats = new ArrayList<>();
      for ( VideoDto v: fdto.videos ) {
	if ( !formats.contains(v.format) 
		&& v.audio == lang &&
		(quality == null || v.resolution.equals(quality) ))
	  formats.add(v.format);
      }
      Collections.sort(formats);
      return formats;
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
	List<PersonDto> list = Ejbs.film().getDirector(fdto.id);
	String toReturn = "";
	for (PersonDto s : list) {
	    toReturn += "<a href=\"" + Pages.FILMS + "?tab=search&director=" + s.id + "\" class=\"label label-default\">" + s.name + "</a> ";
	}
	if (toReturn.length() > 0) {
	    return toReturn;
	} else {
	    return "<span class=\"label label-default\">" + Lang.getString("inconnu") + "</span>";
	}
    }

    public String getCasting() {
	List<PersonDto> list = Ejbs.film().getCasting(fdto.id);
	String toReturn = "";
	for (PersonDto s : list) {
	    toReturn += "<a href=\"" + Pages.FILMS + "?tab=search&actor=" + s.id + "\" class=\"label label-default\">" + s.name + "</a> ";
	}
	if (toReturn.length() > 0) {
	    return toReturn;
	} else {
	    return "<span class=\"label label-default\">" + Lang.getString("inconnu") + "</span>";
	}
    }

    public String getGenres() {
	Set<GenreDto> set = Ejbs.film().getGenre(fdto.id,lang.getLang());
	String toReturn = "";
	for (GenreDto s : set) {
	    toReturn += "<a href=\"" + Pages.FILMS + "?tab=search&genre=" + s.id + "\" class=\"label label-default\">" + s.name + "</a> ";
	}
	if (toReturn.length() > 0) {
	    return toReturn;
	} else {
	    return "<span class=\"label label-default\">" + Lang.getString("inconnu") + "</span>";
	}
    }

}
