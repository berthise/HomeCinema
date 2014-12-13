/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Lang;
import enums.ProductStates;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import managers.entities.ManageEntitieProduct;

/**
 *
 * @author titou
 */
@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_PRODUCT")
  private Long id;

  @JoinColumn(name = "FILMS")
  @ManyToMany(mappedBy = "products")
  private List<Film> films;

  @Column(name = "PRICE")
  private Double price;

  @Temporal(TemporalType.DATE)
  @Column(name = "ADD_DATE")
  private java.util.Date addDate;

          @ElementCollection(fetch=EAGER)
     @MapKeyColumn(name="locale")
  @CollectionTable(name = "PRODUIT_NAME", joinColumns = @JoinColumn(name = "produit_id"))
  private Map<Lang,String> name;

  @Column(name = "STATE_")
  private ProductStates state;

  @Column(name = "NB_SALES")
  private Integer nbSales;

  
  public Product(Film f ,Double p)
  {
      this();
      setPrice(p);
      setName(f.getTitle(Lang.EN),Lang.EN);
      ManageEntitieProduct.linkProductFilm(f, this);
      setState(ProductStates.Activated);
  }

  public Product()
  {
      setNbSales(0);
      setAddDate(new Date());
      setFilms(new ArrayList());
      this.name= new HashMap<>();
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Film> getFilms() {
    return films;
  }

  public void setFilms(List<Film> films) {
    this.films = films;
  }

  public void addFilm(Film f){
      this.films.add(f);
  }
          
  
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public String getName(Lang lang) {
      if (name.containsKey(lang))
    return name.get(lang);
      else
	  return name.get(Lang.EN);
  }

  public void setName(String name,Lang lang) {
    this.name.put(lang, name);
  }

  public ProductStates getState() {
    return state;
  }

  public void setState(ProductStates state) {
    this.state = state;
  }

  public Integer getNbSales() {
    return nbSales;
  }

  public void setNbSales(Integer nbSales) {
    this.nbSales = nbSales;
  }
  
  public Double getAverageRate() {
    if ( this.films == null) return 0D;
    Double rating = 0D;
    for (Film f: this.films) {
      rating += f.getRating();
    }
    rating = rating/this.films.size();
    return rating;
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
    if (!(object instanceof Product)) {
      return false;
    }
    Product other = (Product) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", films= , price=" + price + ", addDate=" + addDate + ", name=" + name + ", state=" + state + ", nbSales=" + nbSales + '}';
  }

    public void removeFilm(Film f) {
	this.films.remove(f);
    }
  
  

}
