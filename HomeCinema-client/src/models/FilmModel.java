/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.UserDto;
import dtos.VideoDto;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author seb
 */
public class FilmModel implements Model_if {

  FilmDto fDto = null;
  VideoModel video = null;
  VideoModel trailer = null;

  public FilmModel() {
    fDto = new FilmDto();
    video = null;
    trailer = null;
  }

  public FilmModel(FilmDto filmDto) {
    this.fDto = filmDto;
    video = null;
    trailer = null;
  }

  public void AddVideo(VideoModel v) {
    video = v;
  }

  public void AddTrailer(VideoModel v) {
    trailer = v;
  }

  public VideoModel getVideo() {
    return video;
  }

  public VideoModel getTrailer() {
    return trailer;
  }

  public Long getId() {
    return fDto.id;
  }

  public void setId(Long id) {
    fDto.id = id;
  }

  public String getTitle() {
    return fDto.title;
  }

  public void setTitle(String title) {
    fDto.title = title;
  }

  public String getOverview() {
    return fDto.overview;
  }

  public void setOverview(String overview) {
    fDto.overview = overview;
  }

  public String getCover() {
    return fDto.cover;
  }

  public void setCover(String cover) {
    fDto.cover = cover;
  }

  public Date getRelease_date() {
    return fDto.release_date;
  }

  public void setRelease_date(Date release_date) {
    fDto.release_date = release_date;
  }

  public String getCountry() {
    return fDto.country;
  }

  public void setCountry(String country) {
    fDto.country = country;
  }

  public Integer getRuntime() {
    return fDto.runtime;
  }

  public void setRuntime(Integer runtime) {
    fDto.runtime = runtime;
  }

  public Double getRating() {
    return fDto.rating;
  }

  public void setRating(Double rating) {
    fDto.rating = rating;
  }

  @Override
  public void displayConsole() {
    System.out.println("Film    Id: " + this.getId());
    System.out.println("        Title: " + this.getTitle());
    System.out.println("        Overview: " + this.getOverview());
    System.out.println("        Cover: " + this.getCover());
    System.out.println("        Release_date: " + this.getRelease_date().toString());
    System.out.println("        Runtime: " + this.getRuntime());
    System.out.println("        Rating: " + this.getRating());
    System.out.println("        Video: ");
    if (video != null) {
      video.displayConsole();
    } else {
      System.out.println("         Null");
    }
    System.out.println("        Trailer: ");
    if (trailer != null) {
      trailer.displayConsole();
    } else {
      System.out.println("         Null");
    }
  }

  public void initDto() {
    this.fDto = new FilmDto();
    fDto.id = new Long(0);
    fDto.country = "";
    fDto.cover = "";
    fDto.overview = "";
    fDto.rating = 0.0;
    fDto.release_date = new Date();
    fDto.runtime = 0;
    fDto.title = "";
  }

  public FilmDto getfDto() {
    return fDto;
  }

  public void setfDto(FilmDto fDto) {
    this.fDto = fDto;
  }

  
  public Boolean notNull() {
    return this.fDto != null;
  }
}
