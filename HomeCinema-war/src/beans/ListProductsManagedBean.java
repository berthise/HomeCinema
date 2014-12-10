/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.SearchProductsManagedBean.SearchParams;
import ejbs.Ejbs;
import dtos.FilteredListProductsDto;
import dtos.GenreDto;
import dtos.ProductDto;
import enums.OrderTypes;
import enums.ProductTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
	staticNewProduct = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.NEW, 9, null, ProductTypes.All).list;
	staticTopProduct = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.RATING, 9, null, ProductTypes.All).list;
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

    private List<ProductDto> getAllFilms() {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.ALPH, N_PER_PAGE, (page - 1) * N_PER_PAGE, ProductTypes.Main);
	updateLastPage(flpdto);
	return flpdto.list;
    }

    private List<ProductDto> getAllProducts() {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.ALPH, N_PER_PAGE, (page - 1) * N_PER_PAGE, ProductTypes.Pack);
	updateLastPage(flpdto);
	return flpdto.list;
    }

    private List<ProductDto> getSearchProducts(SearchParams params) {
	FilteredListProductsDto flpdto = Ejbs.product().getFilteredProducts(params.actorId, params.directorId, params.genres, params.title, params.date, OrderTypes.NO, N_PER_PAGE, (page - 1) * N_PER_PAGE, ProductTypes.All);
	updateLastPage(flpdto);
	return flpdto.list;
    }

    public List<ProductDto> getFilms(SearchParams params) {
	switch (tabFilms) {
	    case "new":
		lastPage = 1;
		return staticNewProduct;
	    case "top":
		lastPage = 1;
		return staticTopProduct;
	    case "pack":
		return getAllProducts();
	    case "search":
		return getSearchProducts(params);
	    case "all":
	    default:
		return getAllFilms();
	}
    }

    public List<ProductDto> getFooterNewProduct() {
	return staticNewProduct;
    }

    public List<ProductDto> getFooterTopProduct() {
	return staticTopProduct;
    }

    public List<ProductDto> getSelectionFilms(int n) {
	return Ejbs.product().getFilteredProducts(null, null, null, null, null, OrderTypes.RAND, n, 0, ProductTypes.Main).list;
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
