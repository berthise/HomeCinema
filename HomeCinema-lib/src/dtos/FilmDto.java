/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author titou
 */
public class FilmDto implements Serializable {

  public Long id;
  public String title;
  public String overview;
  public String cover;
  public Date release_date;
  public String country;
  public Integer runtime;
  public Double rating;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public Date getRelease_date() {
    return release_date;
  }

  public void setRelease_date(Date release_date) {
    this.release_date = release_date;
  }
  
    public String getReleaseDatetring(String formater) {
    SimpleDateFormat df = new SimpleDateFormat(formater);
    return df.format(this.release_date);
  }

  public void setReleaseDateString(String s, String formater) {
    SimpleDateFormat df = new SimpleDateFormat(formater);
    try {
      this.release_date = df.parse(s);
    } catch (ParseException ex) {
      this.release_date = new Date();
      Logger.getLogger(FilmDto.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Integer getRuntime() {
    return runtime;
  }

  public void setRuntime(Integer runtime) {
    this.runtime = runtime;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }
}
