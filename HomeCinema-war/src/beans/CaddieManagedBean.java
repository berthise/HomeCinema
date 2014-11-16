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
import javax.servlet.http.HttpServletRequest;

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

	public Integer getNbRows() {
	    // return (this.films.size() > 1) ? this.films.size() + 1 : 1;
	    return this.films.size() + 1; // Si produit + film

	}
    }

    public CaddieDto cdto;

    public CaddieManagedBean() throws NamingException {
	cdto = null;
    }

    public List<Product> getListCaddie(Long idUser) {
	List<Product> toReturn = new ArrayList<>();
	if (cdto == null) {
	    this.cdto = Ejbs.transaction().getCaddieDto(idUser);
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

    public Double getTotalPrice(Long idUser) {
	Double price = 0D;
	if (cdto == null) {
	    this.cdto = Ejbs.transaction().getCaddieDto(idUser);
	}
	if (cdto != null) {
	    for (ProductDto pd : cdto.films) {
		price += pd.price;
	    }
	}
	return price;
    }

    public Integer counter(Long idUser) {
	if (idUser == null)
	    return null;

//    if (cdto == null) {
	this.cdto = Ejbs.transaction().getCaddieDto(idUser);
//    }
	return cdto.films.size();
    }

    public void deleteFromCaddie(Long idUser, Long idProduct) {
	try {
	    Ejbs.transaction().removeProduct(idUser, idProduct);
	    this.cdto = Ejbs.transaction().getCaddieDto(idUser);
	    FacesMessage message = new FacesMessage("Succès de la suppresion !");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    if (((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI().contains("moncompte.xhtml")) {
		// redirect only if on page moncompte (to reload caddie).
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext().redirect("moncompte.xhtml?box=caddie");
	    }
	} catch (IOException ex) {
	    Logger.getLogger(CaddieManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	}

    }

    public void addProductFilmToCaddie(Long iduser, Long idproduct, Long idfilm) throws IOException {
	cdto = Ejbs.transaction().addProduct(iduser, idproduct);
	this.cdto = Ejbs.transaction().getCaddieDto(iduser); // FIXME

	FacesMessage message = new FacesMessage("Succès de l'ajout !");
	FacesContext.getCurrentInstance().addMessage(null, message);
//    FacesContext.getCurrentInstance().getExternalContext().redirect("fiche_film.xhtml?id=" + idfilm);
    }
}
