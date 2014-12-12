/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.PersonDto;
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
	public String date1;
	public String date2;
	public String director;
	public String actor;
	public Long directorId;
	public Long actorId;
	public String genresMode = "OR";

	public List<Long> genres = new ArrayList<>();
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
    
    private void reset(){
	params = new SearchParams();
	checked = new HashMap<>();
    }

    String actorReset;
    
    public void setTitleReset(String t) {
	reset();
	params.title = t;
    }

    public String getTitleReset() {
	return null;
    }

    public Long getGenreReset() {
	return null;
    }

    public void setGenreReset(Long genreReset) {
	reset();
	setGenreAlone(genreReset);
    }

    public Long getActorReset() {
	return null;
    }

    public void setActorReset(Long actorReset) {
	reset();
	setActorId(actorReset);
    }

    public Long getDirectorReset() {
	return null;
    }

    public void setDirectorReset(Long directorReset) {
	reset();
	setDirectorId(directorReset);
    }

    public String getDate1() {
	if (this.params.date1 != null && this.params.date1.length() == 0) {
	    return null;
	}
	return this.params.date1;
    }

    public void setDate1(String date1) {
	this.params.date1 = date1;
    }
    
    public String getDate2() {
	if (this.params.date2 != null && this.params.date2.length() == 0) {
	    return null;
	}
	return this.params.date2;
    }

    public void setDate2(String date2) {
	this.params.date2 = date2;
    }

    public String getDirector() {
	if (this.params.director != null && this.params.director.length() == 0) {
	    return null;
	}
	return this.params.director;
    }

    public void setDirector(String director) {
	this.params.director = director;
	if (director != null && director.length() > 0) {
	    PersonDto p = Ejbs.person().getPerson(director);
	    if (p != null) {
		this.params.directorId = p.id;
	    } else {
		this.params.directorId = -1L;
	    }
	} else {
	    this.params.directorId = null;
	}
    }

    public String getActor() {
	if (this.params.actor != null && this.params.actor.length() == 0) {
	    return null;
	}
	return this.params.actor;
    }

    public void setActor(String actor) {
	this.params.actor = actor;
	if (actor != null && actor.length() > 0) {
	    PersonDto p = Ejbs.person().getPerson(actor);
	    if (p != null) {
		this.params.actorId = p.id;
	    } else {
		this.params.actorId = -1L;
	    }
	} else {
	    this.params.actorId = null;
	}
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
    }

    public Long getDirectorId() {
	return this.params.directorId;
    }

    public void setDirectorId(Long directorId) {
	this.params.directorId = directorId;
	this.params.director = Ejbs.person().getPerson(directorId).name;
    }

    public Long getActorId() {
	return this.params.actorId;
    }

    public void setActorId(Long actorId) {
	this.params.actorId = actorId;
	this.params.actor = Ejbs.person().getPerson(actorId).name;
    }

    private void setGenreAlone(Long id) {
	this.params.genres.add(id);
	checked.put(id, Boolean.TRUE);
    }

    public Long getGenreAlone() {
	return null;
    }

    public String getGenresMode() {
	return this.params.genresMode;
    }

    public void setGenresMode(String genresMode) {
	this.params.genresMode = genresMode;
    }

}
