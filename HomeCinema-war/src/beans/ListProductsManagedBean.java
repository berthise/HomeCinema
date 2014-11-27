/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.SearchProductsManagedBean.SearchParams;
import ejbs.Ejbs;
import dtos.FilmDto;
import dtos.GenreDto;
import dtos.ProductDto;
import enums.OrderTypes;
import enums.ProductTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utils.Pages;

/**
 *
 * @author narcisse
 */
@ManagedBean
@SessionScoped
public class ListProductsManagedBean {

    private final List<String> listTabsFilms;
    private String tabFilms;

    private final int OPEN = 0;
    private final int OPENING = 1;
    private final int CLOSING = 2;
    private final int CLOSE = 3;
    private Integer searchOpened;

    public Integer getSearchOpened() {
	if (searchOpened == OPENING) {
	    searchOpened = OPEN;
	    return CLOSE;
	} else if (searchOpened == CLOSING) {
	    searchOpened = CLOSE;
	    return OPEN;
	} else {
	    return searchOpened;
	}
    }

    public void setSearchOpened(Integer searchOpened) {
	this.searchOpened = searchOpened;
    }

    public String getTabFilms() {
	return tabFilms;
    }

    public void setTabFilms(String tabFilms) throws IOException {
	if (!(tabFilms == null || !listTabsFilms.contains(tabFilms))) {
	    if (tabFilms.equals("searchLOCALE") && searchOpened == CLOSE) {
		searchOpened = OPENING;
	    } else if (!tabFilms.equals("searchLOCALE") && tabFilms.endsWith("LOCALE") && searchOpened == OPEN) {
		searchOpened = CLOSING;
	    } else if (tabFilms.equals("search") || (tabFilms.equals("searchLOCALE") && searchOpened == OPEN)) {
		searchOpened = OPEN;
	    } else {
		searchOpened = CLOSE;
	    }

	    if (tabFilms.endsWith("LOCALE")) {
		this.tabFilms = tabFilms.split("LOCALE")[0];
	    } else {
		this.tabFilms = tabFilms;
	    }
	} else {
	    this.tabFilms = listTabsFilms.get(0);
	    searchOpened = CLOSE;
	}
	if (!FacesContext.getCurrentInstance().isPostback()) {
	    FacesContext.getCurrentInstance().getExternalContext().redirect(Pages.FILMS);
	}
    }

    public ListProductsManagedBean() {
	String[] ref1 = {"all", "allLOCALE", "top", "topLOCALE", "new", "newLOCALE", "pack", "packLOCALE", "search", "searchLOCALE"};
	listTabsFilms = Arrays.asList(ref1);
	tabFilms = "all";
	searchOpened = CLOSE;
    }

    private List<List<ProductDto>> splitListFilmDto(List<ProductDto> l) {
	List<List<ProductDto>> toReturn = new ArrayList<>();
	int i = 0;
	while (i + 3 < l.size()) {
	    toReturn.add(l.subList(i, i + 3));
	    i += 3;
	}
	if (i < l.size()) {
	    toReturn.add(l.subList(i, l.size()));
	}
	return toReturn;
    }
    
    public String getEmptyTd(List<List<ProductDto>> list){
	int size = list.get(list.size()-1).size();
	if (size == 1)
	    return "<td><td/><td></td>";
	else if (size == 2)
	    return "<td><td/>";
	else
	    return "";
    }

    private List<List<ProductDto>> getAllFilms() {
	List<ProductDto> list = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.NO, null, null, ProductTypes.Main);
	return splitListFilmDto(list);
    }

    private List<List<ProductDto>> getAllProducts() {
	List<ProductDto> list1 = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.NO, null, null, ProductTypes.Pack);
	return splitListFilmDto(list1);
    }

    private List<List<ProductDto>> getNewFilms() {
	List<ProductDto> list = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.NEW, 10, null,ProductTypes.All);
	return splitListFilmDto(list);
    }

    private List<List<ProductDto>> getTopFilms() {
	List<ProductDto> list = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.RATING, 10, null, ProductTypes.All);
	return splitListFilmDto(list);
    }

    private List<List<ProductDto>> getSearchProducts(SearchParams params) {
	Long actor = null;
	//actor = Ejbs.person().getActor(params.actor);
	Long director = null;
	//director = Ejbs.person().getDirector(params.director);
	List<ProductDto> list = Ejbs.product().getFilteredProducts(actor, director, params.genres, params.title, params.date, OrderTypes.NO, null, null,  ProductTypes.All);
	return splitListFilmDto(list);
    }

    public List<List<ProductDto>> getFilms(SearchParams params) {
	switch (tabFilms) {
	    case "new":
		return getNewFilms();
	    case "top":
		return getTopFilms();
	    case "pack":
		return getAllProducts();
	    case "search":
		return getSearchProducts(params);
	    case "all":
	    default:
		return getAllFilms();
	}
    }

    public List<ProductDto> getTopProduct() {
	List<ProductDto> toReturn = Ejbs.product().getAllProduct();

	Collections.sort(toReturn, new Comparator<ProductDto>() {
	    @Override
	    public int compare(ProductDto t, ProductDto t1) {
		if (t.nbSales < t1.nbSales) {
		    return 1;
		} else if (t.nbSales > t1.nbSales) {
		    return -1;
		} else {
		    return 0;
		}
	    }
	});

	if (toReturn.size() > 10) {
	    return toReturn.subList(0, 10);
	} else {
	    return toReturn;
	}
    }

    public List<ProductDto> getNewProduct() {
	List<ProductDto> toReturn = Ejbs.product().getAllProduct();

	Collections.sort(toReturn, new Comparator<ProductDto>() {
	    @Override
	    public int compare(ProductDto t, ProductDto t1) {
		return t.addDate.compareTo(t1.addDate);
	    }
	});

	if (toReturn.size() > 10) {
	    return toReturn.subList(0, 10);
	} else {
	    return toReturn;
	}
    }

    public List<FilmDto> getSelectionFilms(int n) {
	List<FilmDto> list = Ejbs.films().findAllFilms();
	List<FilmDto> toReturn = new ArrayList<>();
	Random rng = new Random();
	Set<Integer> generated = new LinkedHashSet<>();
	while (generated.size() < n) {
	    Integer next = rng.nextInt(list.size());
	    generated.add(next);
	}
	for (Integer i : generated) {
	    toReturn.add(list.get(i));
	}
	return toReturn;
    }
    
    public List<GenreDto> getAllGenres (){
	return Ejbs.product().getAllGenres();
    }
}
