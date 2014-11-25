/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
	if (this.params.title != null && this.params.title.length() == 0)
	    return null;
	return this.params.title;
    }

    public String getDate() {
	if (this.params.date != null && this.params.date.length() == 0)
	    return null;
	return this.params.date;
    }

    public void setDate(String date) {
	this.params.date = date;
    }

    public String getDirector() {
	if (this.params.director != null && this.params.director.length() == 0)
	    return null;
	return this.params.director;
    }

    public void setDirector(String director) {
	this.params.director = director;
    }

}
