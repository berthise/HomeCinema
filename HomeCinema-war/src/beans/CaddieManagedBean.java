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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;
import static utils.Beans.findBean;
import static utils.Beans.getRequestPage;
import utils.Lang;
import utils.Message;
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
	}
    }

    public CaddieDto cdto;

    public CaddieManagedBean() throws NamingException {
	cdto = null;
    }

    public List<Product> getListCaddie() {
	SessionManagedBean session = findBean("sessionManagedBean");
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

    public String getTotalPrice() {
	SessionManagedBean session = findBean("sessionManagedBean");
	Double price = 0D;
	if (cdto == null) {
	    this.cdto = Ejbs.transaction().getCaddieDto(session.getId());
	}
	if (cdto != null) {
	    for (ProductDto pd : cdto.films) {
		price += pd.price;
	    }
	}
	return String.format("%.2f", price);
    }

    public Integer counter() {
	SessionManagedBean session = findBean("sessionManagedBean");
	return session.getCaddySize();
    }

    public void deleteFromCaddie(Long idProduct) {
	SessionManagedBean session = findBean("sessionManagedBean");
	Ejbs.transaction().removeProduct(session.getId(), idProduct);
	this.cdto = Ejbs.transaction().getCaddieDto(session.getId());
	session.caddySizeMinus();
	Message.Info("Succès de la suppresion !");
	if (getRequestPage().contains(Pages.MON_COMPTE)) {
	    // redirect only if on page moncompte (to reload caddie).
	    Redirect.redirectTo(Pages.MON_COMPTE + "#caddie");
	}

    }

    public void addProductFilmToCaddie(Long idproduct, Long idfilm) throws IOException {
	SessionManagedBean session = findBean("sessionManagedBean");
	this.cdto = Ejbs.transaction().addProduct(session.getId(), idproduct);
	session.caddySizePlus();
	Message.Info(Lang.getString("caddie-bean-info"));
	Redirect.redirectTo(Pages.FICHE_FILM + "?id=" + idfilm);
    }

    private int isInMyFilms(Long ids) {
	SessionManagedBean session = findBean("sessionManagedBean");
	int toReturn = 0;
	for (FilmDto l : Ejbs.user().getFilms(session.getId())) {
	    if (l.id.equals(ids)) {
		toReturn++;
	    }
	}
	return toReturn;
    }

    private int isInMyCaddie(Long ids) {
	SessionManagedBean session = findBean("sessionManagedBean");
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

    public void addProductToCaddie(Long idproduct, String _switch) throws IOException {
	SessionManagedBean session = findBean("sessionManagedBean");
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
	Message.Info(Lang.getString("caddie-bean-info"));
	Redirect.redirectTo(Pages.FICHE_PRODUCT + "?id=" + idproduct);
    }

}
