/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.CaddieDto;
import dtos.FilmDto;
import dtos.ProductDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import static utils.Beans.getRequestPage;
import utils.Pages;
import utils.Redirect;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class CaddieManagedBean {

  public class Product {

    private ProductDto product;
    private List<FilmDto> films;

    public ProductDto getProduct() {
      return product;
    }

    public void setProduct(ProductDto product) {
      this.product = product;
    }

    public List<FilmDto> getFilms() {
      return films;
    }

    public void setFilms(List<FilmDto> films) {
      this.films = films;
    }

    public FilmDto getFilm() {
      return (this.films.size() > 1) ? null : this.films.get(0);
    }

    public Integer getNbRows() {
      return (this.films.size() > 1) ? this.films.size() + 1 : 1;
      //return this.films.size() + 1; // Si produit + film
    }
  }

  public CaddieDto cdto;
  private SessionManagedBean session;

  public void setSession(SessionManagedBean session) {
    this.session = session;
  }

  public CaddieManagedBean() throws NamingException {
    cdto = null;
  }

  public List<Product> getListCaddie() {
    List<Product> toReturn = new ArrayList<>();
    if (cdto == null) {
      this.cdto = Ejbs.transaction().getCaddieDto(session.getId());
    }

    for (ProductDto pd : cdto.films) {
      Product p = new Product();
      p.setProduct(pd);
      List<FilmDto> list_films = Ejbs.product().getFilms(pd.id);
      p.setFilms(list_films);
      toReturn.add(p);

    }
    return toReturn;
  }

  public Double getTotalPrice() {
    Double price = 0D;
    if (cdto == null) {
      this.cdto = Ejbs.transaction().getCaddieDto(session.getId());
    }
    if (cdto != null) {
      for (ProductDto pd : cdto.films) {
	price += pd.price;
      }
    }
    return price;
  }

  public Integer counter() {
    return session.getCaddySize();
  }

  public void deleteFromCaddie(Long idProduct) {
    Ejbs.transaction().removeProduct(session.getId(), idProduct);
    this.cdto = Ejbs.transaction().getCaddieDto(session.getId());
    session.caddySizeMinus();
    FacesMessage message = new FacesMessage("Succès de la suppresion !");
    FacesContext.getCurrentInstance().addMessage(null, message);
    if (getRequestPage().contains(Pages.MON_COMPTE)) {
      // redirect only if on page moncompte (to reload caddie).
      Redirect.redirectTo(Pages.MON_COMPTE + "?box=caddie");
    }

  }

  public void addProductFilmToCaddie(SessionManagedBean session, Long idproduct, Long idfilm) throws IOException {
    if (session != null) {
      this.session = session;
    }
    this.cdto = Ejbs.transaction().addProduct(this.session.getId(), idproduct);
    this.session.caddySizePlus();
    FacesMessage message = new FacesMessage("Succès de l'ajout !");
    FacesContext.getCurrentInstance().addMessage(null, message);
    Redirect.redirectTo(Pages.FICHE_FILM + "?id=" + idfilm);
  }

  private int isInMyFilms(Long ids) {
    int toReturn = 0;
    for (FilmDto l : Ejbs.user().getFilms(session.getId())) {
      if (l.id.equals(ids)) {
	toReturn++;
      }
    }
    return toReturn;
  }

  private int isInMyCaddie(Long ids) {
    int toReturn = 0;
    for (ProductDto l : Ejbs.transaction().getCaddieDto(session.getId()).films) {
      for (FilmDto f : Ejbs.product().getFilms(l.id)) {
	if (f.id.equals(ids)) {
	  toReturn++;
	}
      }
    }
    return toReturn;
  }

  public void addProductToCaddie(SessionManagedBean session, Long idproduct, String _switch) throws IOException {
    if (session != null) {
      this.session = session;
    }
    switch (_switch) {
      case "FREE":
	this.cdto = Ejbs.transaction().addProduct(session.getId(), idproduct);
	session.caddySizePlus();
	break;
      case "PART_CADDIE":
	this.cdto = Ejbs.transaction().addProduct(session.getId(), idproduct);
	for (FilmDto f : Ejbs.product().getFilms(idproduct)) {
	  Ejbs.transaction().removeProduct(session.getId(), f.main_product_id);
	}
	break;
      default:
	for (FilmDto f : Ejbs.product().getFilms(idproduct)) {
	  if (isInMyFilms(f.id) == 0 && isInMyCaddie(f.id) == 0) {
	    this.cdto = Ejbs.transaction().addProduct(session.getId(), f.main_product_id);
	    session.caddySizePlus();
	  }
	}
	break;
    }
    FacesMessage message = new FacesMessage("Succès de l'ajout !");
    FacesContext.getCurrentInstance().addMessage(null, message);
    Redirect.redirectTo(Pages.FICHE_PRODUCT + "?id=" + idproduct);
  }

}
