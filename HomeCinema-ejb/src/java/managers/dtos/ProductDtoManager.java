/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.ProductDto;
import entities.Product;
import enums.Lang;
import enums.OrderTypes;
import enums.ProductStates;
import java.util.Comparator;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class ProductDtoManager {

    public static ProductDto getDto(Product p,Lang lang) {
	if (p == null) {
	    return null;
	}

	ProductDto pdto = new ProductDto();
	pdto.id = p.getId();
	pdto.addDate = p.getAddDate();
	pdto.name = p.getName(lang);
	pdto.nbSales = p.getNbSales();
	pdto.price = p.getPrice();
	pdto.state = p.getState();
	if (p.getFilms()==null || p.getFilms().isEmpty())
	    pdto.cover=null;
	else
	    pdto.cover=p.getFilms().get(0).getCoverId();
	return pdto;
    }

    public static Product makeProduct(ProductDto pdto,Lang lang) {
	Product p = new Product();
	p.setId(pdto.id);
	p.setName(pdto.name,lang);
	p.setPrice(pdto.price);
	p.setState(pdto.state);

	return p;
    }

    public static Product mergeOrSave(ProductDto pdto,Lang lang , EntityManager em) {

	Product p = null;
	if (pdto.id != null) {
	    p = em.find(Product.class, pdto.id);
	}
	if (p == null) {
	    p = makeProduct(pdto,lang);
	    em.persist(p);
	} else {
	    p.setName(pdto.name,lang);
	    p.setAddDate(new Date());
	    p.setState(pdto.getState());
	    p.setPrice(pdto.price);
	    p.setState(pdto.state);
	    em.merge(p);
	}
	return p;
    }


    
    
}
