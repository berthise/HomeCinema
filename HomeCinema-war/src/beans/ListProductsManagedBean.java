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

    public List<ProductDto> getTopFilms() {
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

	if (toReturn.size() > 10)
	    return toReturn.subList(0, 10);
	else	
	    return toReturn;
    }
}
