/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.FilmDto;
import dtos.ProductDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author narcisse
 */
@ManagedBean
@ViewScoped
public class ListProductsManagedBean {

    private String initBox;

    public void setInitBox(String b) {
	String[] ref = {"all", "top", "new"};
	List<String> list = Arrays.asList(ref);
	if (!list.contains(b) || b == null) {
	    b = ref[0];
	}
	initBox = b;
    }

    public void changeInitBox(String b) {
	this.initBox = b;
    }

    public String getInitBox() {
	return initBox;
    }

    public ListProductsManagedBean() {
	this.initBox = "all";
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
}
