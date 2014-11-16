/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import dtos.ProductDto;
import ejbs.Ejbs;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class ProductManagedBean {

    public ProductDto pdto;

    public ProductManagedBean() {
	this.pdto = new ProductDto();
    }

    public void setDtoFromId() throws IOException {
	if (pdto.id == null) {
	    FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
	}
	ProductDto p = Ejbs.product().getProduct(pdto.id);
	if (p == null) {
	    FacesContext.getCurrentInstance().getExternalContext().dispatch("404.xhtml");
	}
	else if (Ejbs.product().getFilms(p.id).size() == 1){
	    String url = "fiche_film.xhtml?id="+Ejbs.product().getFilms(p.id).get(0).id;
	    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}
	pdto = p;
    }

    public void setId(Long i) {
	pdto.id = i;
    }

    public Long getId() {
	return pdto.id;
    }

    public String getName() {
	return pdto.name;

    }

    public void setName(String s) {
	pdto.name = s;
    }

    public void setDate(Date d) {
	pdto.addDate = d;
    }

    public String getDate() {
	SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	return formater.format(pdto.addDate);
    }

    public String getPrice() {
	return pdto.price.toString();
    }

    public void setPrice(String p) {
	pdto.price = Double.parseDouble(p);
    }

    public List<FilmDto> getListFilms() {
	return Ejbs.product().getFilms(pdto.id);
    }

    public String getRating() {
	double rating = 0;
	List<FilmDto> l = getListFilms();
	for (FilmDto f : l) {
	    rating += f.rating;
	}
	rating /= (double) l.size();

	String toReturn = "";
	int pe = (int) Math.floor(rating);
	int i;
	for (i = 0; i < pe; i++) {
	    toReturn += "<img src=\"img/star-full-icon.png\"/>\n";
	}
	int m = 0;
	if (rating - pe > 0.5) {
	    toReturn += "<img src=\"img/star-half-full-icon.png\"/>\n";
	    m++;
	}
	for (; i < 10 - m; i++) {
	    toReturn += "<img src=\"img/star-empty-icon.png\"/>\n";
	}
	String format = new DecimalFormat("#.#").format(rating).replaceAll(",", ".");
	return toReturn + "<p>(" + format + "/10)</p>\n";
    }

    public Integer getNbSells(Long idproduct) {
	ProductDto p = Ejbs.product().getProduct(idproduct);
	return p.nbSales;
    }

    public Double getPrice(Long idproduct) {
	ProductDto p = Ejbs.product().getProduct(idproduct);
	return p.price;
    }

    public String getShortOverview(FilmDto fdto, int limit) {
	if (fdto.overview.length() < limit) {
	    return fdto.overview;
	}
	String[] parts = fdto.overview.split(" ");
	String toReturn = parts[0];
	int i = 1;
	while ((toReturn + parts[i]).length() < limit) {
	    toReturn += " "+parts[i++];
	}
	return toReturn + " ...";
    }
    
    public String getReduction (){
	double price = 0;
	List<FilmDto> l = getListFilms();
	for (FilmDto f : l) {
	    price += Ejbs.product().getProduct(f.main_product_id).price;
	}	
	double reduc = pdto.price/price * 100;	
	return (100 - (int) reduc) + "";	
    }
}
