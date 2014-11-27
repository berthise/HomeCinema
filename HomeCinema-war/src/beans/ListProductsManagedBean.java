/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.SearchProductsManagedBean.SearchParams;
import ejbs.Ejbs;
import dtos.FilmDto;
import dtos.FilteredListProductsDto;
import dtos.GenreDto;
import dtos.ProductDto;
import enums.OrderTypes;
import enums.ProductTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    private int page;
    private int lastPage;
    private final int N_PER_PAGE = 12;

    private List<GenreDto> allGenres;

    private List<ProductDto> staticNewProduct;
    private List<ProductDto> staticTopProduct;

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
	this.page = 1;

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
	page = lastPage = 1;
	allGenres = null;
	staticNewProduct = null;
	staticTopProduct = null;
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

    public boolean isPack(Long id) {
	return Ejbs.product().getFilms(id).size() > 1;
    }

    public String getEmptyTd(List<List<ProductDto>> list) {
	int nb_lines = list.size();
	if (nb_lines > 1) {
	    return "";
	}
	int size = list.get(list.size() - 1).size();
	if (size == 1) {
	    return "<td class=\"invisible-td\"></td><td class=\"invisible-td\"></td>";
	} else if (size == 2) {
	    return "<td class=\"invisible-td\"></td>";
	} else {
	    return "";
	}
    }

    private void updateLastPage(FilteredListProductsDto flpdto) {
	this.lastPage = Math.max(1, flpdto.size / N_PER_PAGE + ((flpdto.size % N_PER_PAGE != 0) ? 1 : 0));
    }

    private List<List<ProductDto>> getAllFilms() {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.ALPH, N_PER_PAGE, (page - 1) * N_PER_PAGE, ProductTypes.Main);
	updateLastPage(flpdto);
	return splitListFilmDto(flpdto.list);
    }

    private List<List<ProductDto>> getAllProducts() {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.ALPH, N_PER_PAGE, (page - 1) * N_PER_PAGE, ProductTypes.Pack);
	updateLastPage(flpdto);
	return splitListFilmDto(flpdto.list);
    }

    private List<List<ProductDto>> getNewFilms() {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.NEW, 9, null, ProductTypes.All);
	updateLastPage(flpdto);
	return splitListFilmDto(flpdto.list);
    }

    private List<List<ProductDto>> getTopFilms() {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.RATING, 9, null, ProductTypes.All);
	updateLastPage(flpdto);
	return splitListFilmDto(flpdto.list);
    }

    private List<List<ProductDto>> getSearchProducts(SearchParams params) {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(params.actorId, params.directorId, params.genres, params.title, params.date, OrderTypes.NO, N_PER_PAGE, (page - 1) * N_PER_PAGE, ProductTypes.All);
	updateLastPage(flpdto);
	return splitListFilmDto(flpdto.list);
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

    public List<ProductDto> getStaticNewProduct() {
	if (staticNewProduct == null) {
	    staticNewProduct = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.NEW, 9, null, ProductTypes.All).list;
	}
	return staticNewProduct;
    }

    public List<ProductDto> getStaticTopProduct() {
	if (staticTopProduct == null) {
	    staticTopProduct = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.RATING, 9, null, ProductTypes.All).list;
	}
	return staticTopProduct;
    }

    public List<FilmDto> getSelectionFilms(int n) {
	List<FilmDto> list = Ejbs.films().findAllFilms();
	List<FilmDto> toReturn = new ArrayList<>();
	if (list.size() > n) {
	    Random rng = new Random();
	    Set<Integer> generated = new LinkedHashSet<>();
	    while (generated.size() < n) {
		Integer next = rng.nextInt(list.size());
		generated.add(next);
	    }
	    for (Integer i : generated) {
		toReturn.add(list.get(i));
	    }
	}
	return toReturn;
    }

    public List<GenreDto> getAllGenres() {
	if (allGenres == null) {
	    allGenres = Ejbs.product().getAllGenres();
	}
	return allGenres;
    }

    public int getPage() {
	return page;
    }

    public void setPage(int page) {
	this.page = page;
    }

    public void setPageNext() {
	this.page++;
    }

    public void setPagePrevious() {
	this.page--;
    }

    public boolean isLastPage() {
	if (tabFilms.equals("top") || tabFilms.equals("new")) {
	    return true;
	}
	return this.page == this.lastPage;
    }

}
