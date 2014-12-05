/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.UserDtoNoPw;
import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import enums.ProductStates;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@SessionScoped
public class ProductManagedBean {

    private ManageProductRemote productManager;

    public ProductDto pdto;
    private ManageFilmRemote filmManager;

    public ProductManagedBean() throws NamingException {
	productManager = (ManageProductRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageProduct!ejbs.ManageProductRemote");
	this.pdto = new ProductDto();
	filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
    }

    public void setDtoFromId() throws IOException {
	if (pdto != null && pdto.id != null && pdto.id != 0) {

	    ProductDto f = productManager.getProduct(pdto.id);
	    pdto = f;
	    this.filmsProduct = this.productManager.getFilms(pdto.id);
	    List<FilmDto> lfdto = filmManager.getAllFilm();
	    if (lfdto == null) {
		lfdto = new ArrayList<>();
	    }
	    lfdto.removeAll(filmsProduct);
	    this.films = lfdto;
	} else {
	    pdto = new ProductDto();
	    pdto.state = ProductStates.Unactivated;
	    this.filmsProduct = new ArrayList<>();
	    this.films = filmManager.getAllFilm();
	}

    }

    public Long getId() {
	return pdto.id;
    }

    public void setId(Long id) {
	pdto.id = id;
    }

    public Double getPrice() {
	return pdto.price;
    }

    public void setPrice(Double id) {
	pdto.price = id;
    }

    public String getName() {
	return pdto.name;
    }

    public void setName(String s) {
	pdto.name = s;
    }

    public void save() {
	this.pdto = productManager.mergeOrSave(pdto);
	FacesMessage message = new FacesMessage("Succès de la modification !");
	FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private List<FilmDto> films;

    public List<FilmDto> getFilms() {
	return this.films;
    }

    public void setFilms(List<FilmDto> array) {
	this.films = array;
    }

    public Integer getTotalFilm() {
	return this.films.size();
    }

    private List<FilmDto> filmsProduct;

    public List<FilmDto> getFilmsProduct() {
	return this.filmsProduct;
    }

    public void setFilmsProduct(List<FilmDto> array) {
	this.filmsProduct = array;
    }

    public Integer getTotalProduct() {
	return this.filmsProduct.size();
    }

    public ProductStates getState() {
	return this.pdto.state;
    }

    public boolean isActivate() {
	return this.pdto.state == ProductStates.Activated;
    }

    public void activate() {
	this.productManager.activate(this.pdto.id);
    }

    public void deactivate() {
	this.productManager.deactivate(this.pdto.id);
    }

    public void addFilm(Long fid) {
	this.productManager.addExistingFilm(this.getId(), fid, false);
    }
}
