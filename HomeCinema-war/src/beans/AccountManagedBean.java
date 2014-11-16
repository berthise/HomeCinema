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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AccountManagedBean {


    public CaddieDto cdto;

    public String initBox;

    public Long idUser;

    public Long getIdUser() {
	return idUser;
    }

    public void setIdUser(Long idUser) {
	this.idUser = idUser;
    }

    public String getInitBox() {
	return initBox;
    }

    public void setInitBox(String b) {
	String[] ref = {"films", "caddie", "historique", "infos"};
	List<String> list = Arrays.asList(ref);
	if (!list.contains(b) || b == null) {
	    b = ref[0];
	}
	initBox = b;
    }

    public AccountManagedBean() {
	this.cdto = new CaddieDto();
	this.initBox = "films";
    }

    public String getHtmlForLink(String link, String title) {
	return "<p id=\"link-" + link + "\" " + ((link.equals(initBox)) ? "class=\"activated\"" : "") + "><a onclick=\"display('" + link + "'); return false;\" href=\"\">" + title + "</a></p>";
    }

    public String getClassDisplay(String link) {
	if (!link.equals(initBox)) {
	    return "box-right display-none";
	} else {
	    return "box-right";
	}
    }



    public List<List<String>> getListFilms() {
        List<List<String>> toReturn = new ArrayList<>();
        List<FilmDto> list = Ejbs.user().getFilms(idUser);

	if (list.isEmpty()) {
	    List<String> toAdd = new ArrayList<>();
	    toAdd.add("EMPTY");
	    toReturn.add(toAdd);
	    return toReturn;
	}

	SimpleDateFormat formater = new SimpleDateFormat("yyyy");

        for (FilmDto f : Ejbs.user().getFilms(idUser)) {
            List<String> toAdd = new ArrayList<>();
            toAdd.add("SIMPLE");
            toAdd.add(f.cover);
            toAdd.add(f.title);
            toAdd.add(formater.format(f.release_date));
            toAdd.add(f.id.toString());
            toReturn.add(toAdd);
        }

	return toReturn;
    }

    private int isInMyFilms(List<Long> ids, Long iduser) {
	int toReturn = 0;
	for (Long i : ids) {
	    for (FilmDto l : Ejbs.user().getFilms(iduser)) {
		if (l.id.equals(i)) {
		    toReturn++;
		}
	    }
	}
	return toReturn;
    }

    private int isInMyCaddie(List<Long> ids, Long iduser) {
	int toReturn = 0;
	for (Long i : ids) {
	    for (ProductDto l : Ejbs.transaction().getCaddieDto(iduser).films) {
		for (FilmDto f : Ejbs.product().getFilms(l.id)) {
		    if (f.id.equals(i)) {
			toReturn++;
		    }
		}
	    }
	}
	return toReturn;
    }

    public String getCodeButtonsFilm(Long idfilm, Long iduser) {	
	int caddie = isInMyCaddie(Arrays.asList(idfilm), iduser);
	int films = isInMyFilms(Arrays.asList(idfilm), iduser);
	
	if (caddie == 0 && films == 0)
	    return "FREE";
	else if (caddie == 1)
	    return "CADDIE";
	else
	    return "OWNED";
    }
    
    public String getCodeButtonsProduct(Long idproduct, Long iduser) {	
	List<Long> l = new ArrayList<>();
	for (FilmDto f : Ejbs.product().getFilms(idproduct))
	    l.add(f.id);
	
	int n = Ejbs.product().getFilms(idproduct).size();
	
	int caddie = isInMyCaddie(l, iduser);
	int films = isInMyFilms(l, iduser);
	
	if (caddie == 0 && films == 0)
	    return "FREE";
	else if (caddie == 0 && films < n)
	    return "PART_FILM";
	else if (caddie < n && films < n)
	    return "PART";
	else if (caddie == n)
	    return "CADDIE";
	else
	    return "OWNED";
    }

    public void addProductFilmToCaddie(Long iduser, Long idproduct, Long idfilm) throws IOException {
	this.cdto = Ejbs.transaction().addProduct(iduser, idproduct);
	FacesContext.getCurrentInstance().getExternalContext().redirect("fiche_film.xhtml?id=" + idfilm);
    }
    
    public void addProductToCaddie(Long iduser, Long idproduct) throws IOException {
	if (getCodeButtonsProduct(idproduct, iduser).compareTo("FREE") == 0)
	    this.cdto = Ejbs.transaction().addProduct(iduser, idproduct);
	else{
	    for (FilmDto f : Ejbs.product().getFilms(idproduct))
		if (isInMyFilms(Arrays.asList(f.id), iduser) == 0)
		    this.cdto = Ejbs.transaction().addProduct(iduser, f.main_product_id);
	}
	FacesContext.getCurrentInstance().getExternalContext().redirect("fiche_product.xhtml?id=" + idproduct);
    }

    public void checkIsMyFilm(Long idfilm, Long iduser) throws IOException {
	if (isInMyFilms(Arrays.asList(idfilm), iduser) == 0) {
	    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}
    }
}
