/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
	public List<Long> genres = new ArrayList<>();

	public void print() {
	    for (Long l : genres) {
		System.out.println(l);
	    }
	    System.out.println("------");
	}
    }

    private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();

    public Map<Long, Boolean> getChecked() {
	return checked;
    }

    public void setChecked(Map<Long, Boolean> checked) {
	this.checked = checked;
    }

    private SearchParams params;

    public SearchProductsManagedBean() {
	params = new SearchParams();
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

    public String getActor() {
	if (this.params.actor != null && this.params.actor.length() == 0) {
	    return null;
	}
	return this.params.actor;
    }

    public void setActor(String actor) {
	this.params.actor = actor;
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
	this.params.print();
    }
}
