/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author toure
 */
@ManagedBean
@SessionScoped
public class SearchProductsManagedBean {

    public class SearchParams {

	public String title;
	public String date;
	public String director;
	public String actor;
	public Long directorId;
	public Long actorId;

	public List<Long> genres = new ArrayList<>();

	// TODO: supprimer cette fonction useless
	public void print() {
	    for (Long l : genres) {
		System.out.println(l);
	    }
	    System.out.println("------");
	}
    }

    private Map<Long, Boolean> checked;

    public Map<Long, Boolean> getChecked() {
	return checked;
    }

    public void setChecked(Map<Long, Boolean> checked) {
	this.checked = checked;
    }

    private SearchParams params;

    public SearchProductsManagedBean() {
	params = new SearchParams();
	checked = new HashMap<>();
    }

    public SearchParams getSearchParams() {
	return this.params;
    }

    public void setTitle(String t) {
	params.title = t;
    }

    public String getTitle() {
	if (this.params.title != null && this.params.title.length() == 0) {
	    return null;
	}
	return this.params.title;
    }

    public String getDate() {
	if (this.params.date != null && this.params.date.length() == 0) {
	    return null;
	}
	return this.params.date;
    }

    public void setDate(String date) {
	this.params.date = date;
    }

    public String getDirector() {
	if (this.params.director != null && this.params.director.length() == 0) {
	    return null;
	}
	return this.params.director;
    }

    public void setDirector(String director) {
	this.params.director = director;
    }

    public void deleteDirector() {
	this.params.director = null;
	this.params.directorId = null;
    }

    public String getActor() {
	if (this.params.actor != null && this.params.actor.length() == 0) {
	    return null;
	}
	return this.params.actor;
    }

    public void setActor(String actor) {
	this.params.actor = actor;
    }

    public void deleteActor() {
	this.params.actor = null;
	this.params.actorId = null;
    }

    public List<Long> getGenres() {
	if (this.params.genres != null) {
	    return null;
	}
	return this.params.genres;
    }

    public void setGenres(List<Long> genres) {
	this.params.genres = genres;
    }

    public void majGenres() {
	List<Long> newList = new ArrayList<>();
	for (Long i : checked.keySet()) {
	    if (checked.get(i)) {
		newList.add(i);
	    }
	}
	this.params.genres = newList;
	//this.params.print();
    }

    public Long getDirectorId() {
	return this.params.directorId;
    }

    public void setDirectorId(Long directorId) {
	this.params.directorId = directorId;
	this.params.director = directorId + "<MettreLeVraiNom>";
    }

    public Long getActorId() {
	return this.params.actorId;
    }

    public void setActorId(Long actorId) {
	this.params.actorId = actorId;
	this.params.director = actorId + "<MettreLeVraiNom>";
    }

    public void setGenreAlone(Long id) {
	this.params.genres.add(id);
	checked.put(id, Boolean.TRUE);
    }

    public Long getGenreAlone() {
	return null;
    }

    public void setClean(Long id) {
	params = new SearchParams();
	checked = new HashMap<>();
    }

    public Long getClean() {
	return null;
    }
}
