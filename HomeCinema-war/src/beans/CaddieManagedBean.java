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
import exception.DeactivatedProductException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class CaddieManagedBean {

    LanguageManagedBean lang = findBean("languageManagedBean");

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
	    this.cdto = Ejbs.transaction().getCaddieDto(session.getId(), lang.getLang());
	}

	for (ProductDto pd : cdto.films) {
	    Product p = new Product();
	    p.setProduct(pd);
	    List<FilmDto> list_films = Ejbs.product().getFilms(pd.id, lang.getLang());
	    p.setFilms(list_films);
	    toReturn.add(p);

	}
	return toReturn;
    }

    public String getTotalPrice() {
	SessionManagedBean session = findBean("sessionManagedBean");
	Double price = 0D;
	if (cdto == null) {
	    this.cdto = Ejbs.transaction().getCaddieDto(session.getId(), lang.getLang());
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
	Ejbs.transaction().removeProduct(session.getId(), idProduct, lang.getLang());
	this.cdto = Ejbs.transaction().getCaddieDto(session.getId(), lang.getLang());
	session.caddySizeMinus();
	session.relaodCaddyIds();

	Message.Info("Succès de la suppresion !");
	if (getRequestPage().contains(Pages.MON_COMPTE)) {
	    // redirect only if on page moncompte (to reload caddie).
	    Redirect.redirectTo(Pages.MON_COMPTE + "#caddie");
	}

    }

    public void addProductFilmToCaddie(Long idproduct, Long idfilm) throws IOException {
	SessionManagedBean session = findBean("sessionManagedBean");
	try {
	    this.cdto = Ejbs.transaction().addProduct(session.getId(), idproduct, lang.getLang());
	} catch (DeactivatedProductException ex) {
	    Message.Error(Lang.getString("caddie-bean-product-deactivated"));

	}
	session.caddySizePlus();
	session.relaodCaddyIds();
	Message.Info(Lang.getString("caddie-bean-info"));
	Redirect.redirectTo(Pages.FICHE_FILM + "?id=" + idfilm);
    }

    public void addProductToCaddie(Long idproduct, String _switch) throws IOException {
	SessionManagedBean session = findBean("sessionManagedBean");
	switch (_switch) {
	    case "FREE": {
		try {
		    this.cdto = Ejbs.transaction().addProduct(session.getId(), idproduct, lang.getLang());
		} catch (DeactivatedProductException ex) {
		    Message.Error(Lang.getString("caddie-bean-product-deactivated"));
		}
	    }
	    session.caddySizePlus();
	    session.relaodCaddyIds();

	    break;
	    case "PART_CADDIE": {
		try {
		    this.cdto = Ejbs.transaction().addProduct(session.getId(), idproduct, lang.getLang());
		} catch (DeactivatedProductException ex) {
		    Message.Error(Lang.getString("caddie-bean-product-deactivated"));
		}
	    }
	    for (FilmDto f : Ejbs.product().getFilms(idproduct, lang.getLang())) {
		Ejbs.transaction().removeProduct(session.getId(), f.main_product_id, lang.getLang());
	    }
	    session.relaodCaddyIds();

	    break;
	    default:
		for (FilmDto f : Ejbs.product().getFilms(idproduct, lang.getLang())) {
		    if (!session.isInMyFilms(f.main_product_id) && !session.isInMyCaddie(f.main_product_id)) {
			try {
			    this.cdto = Ejbs.transaction().addProduct(session.getId(), f.main_product_id, lang.getLang());
			} catch (DeactivatedProductException ex) {
			    Message.Error(Lang.getString("caddie-bean-product-deactivated"));
			}
			session.caddySizePlus();
			session.relaodCaddyIds();

		    }
		}
		break;
	}
	Message.Info(Lang.getString("caddie-bean-info"));
	Redirect.redirectTo(Pages.FICHE_PRODUCT + "?id=" + idproduct);
    }

}
