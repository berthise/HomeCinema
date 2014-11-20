/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.SimpleUserDto;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;

import ejbs.ManageUserRemote;
import ejbs.ManageVideoRemote;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class VideoManagedBean {

    public String getUrl() {
	return Url;
    }

    public void setUrl(String Url) {
	this.Url = Url;
    }

    public Integer getResolution() {
	return resolution;
    }

    public void setResolution(Integer resolution) {
	this.resolution = resolution;
    }

    public Long getFilm() {
	return film;
    }

    public void setFilm(Long film) {
	this.film = film;
    }

    private ManageFilmRemote filmManager;
    private ManageVideoRemote videoManager;

    private Long film;
    private String title;
    private List<VideoDto> videos;
    private VideoDto trailer =null;
    private String Url ="" ;
    private Integer resolution = 240;
    

    public VideoManagedBean() throws NamingException {
	filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
	videoManager = (ManageVideoRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageVideo!ejbs.ManageVideoRemote");
    }

    public void makeVideo() {
	if (film != null) {
	    FilmFicheDto f = filmManager.getDtoFromId(film);
	    this.title = f.title;
	    this.videos = f.files;
	    this.trailer=f.trailler;
	} else {
	    this.title = "tout les films";
	    this.videos = videoManager.getAllVideo();
	}
    }

    public String getTitle() {
	return title;
    }

    public List<VideoDto> getVideos() {
	return this.videos;
    }

    public void setVideos(List<VideoDto> array) {
	this.videos = array;
    }

    public Integer getTotal() {
	return this.videos.size();
    }

    public void save()
    {
	
    }
    
}
