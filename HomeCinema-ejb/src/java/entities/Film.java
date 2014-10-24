/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author titou
 */
@Entity
public class Film implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @ManyToMany
  @Column(name = "PRODUCTS")
  private List<Product> products;

  @Column(name = "MAIN_PRODUCT")
  @OneToOne
  private Product main_product;

  @Size(max = 255)
  @Column(name = "TITLE")
  private String title;

  @Column(name = "OVERVIEW")
  private String overview;

  @Size(max = 255)
  @Column(name = "COVER_ID")
  private String coverId;

  @Temporal(TemporalType.DATE)
  @Column(name = "RELEASE_DATE")
  private java.util.Date releaseDate;

  @Size(max = 255)
  @Column(name = "COUNTRIES")
  private List<Country> countries;

  @Column(name = "RATING")
  private Integer rating;

  @OneToMany
  @Column(name = "VIDEO_FILES")
  private List<VideoFile> videoFile;

  @OneToOne
  @Column(name = "TRAILLER")
  private VideoFile trailler;

  @ManyToMany
  @Column(name = "ACTORS")
  private List<Person> actors;

  @ManyToMany
  @Column(name = "DIRECTORS")
  private List<Person> directors;

  @Column(name = "GENRE")
  @OneToMany
  private List<Genre> genre;

  public Film() {

  }

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

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Product getMain_product() {
    return main_product;
  }

  public void setMain_product(Product main_product) {
    this.main_product = main_product;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getCoverId() {
    return coverId;
  }

  public void setCoverId(String coverId) {
    this.coverId = coverId;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public List<Country> getCountries() {
    return countries;
  }

  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public List<VideoFile> getVideoFile() {
    return videoFile;
  }

  public void setVideoFile(List<VideoFile> videoFile) {
    this.videoFile = videoFile;
  }

  public VideoFile getTrailler() {
    return trailler;
  }

  public void setTrailler(VideoFile trailler) {
    this.trailler = trailler;
  }

  public List<Person> getActors() {
    return actors;
  }

  public void setActors(List<Person> actors) {
    this.actors = actors;
  }

  public List<Person> getDirectors() {
    return directors;
  }

  public void setDirectors(List<Person> directors) {
    this.directors = directors;
  }

  public List<Genre> getGenre() {
    return genre;
  }

  public void setGenre(List<Genre> genre) {
    this.genre = genre;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Film)) {
      return false;
    }
    Film other = (Film) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Film{" + "id=" + id + ", products=" + products + ", main_product=" + main_product + ", title=" + title + ", overview=" + overview + ", coverId=" + coverId + ", releaseDate=" + releaseDate + ", countries=" + countries + ", rating=" + rating + ", videoFile=" + videoFile + ", trailler=" + trailler + ", actors=" + actors + ", directors=" + directors + ", genre=" + genre + '}';
  }

}
