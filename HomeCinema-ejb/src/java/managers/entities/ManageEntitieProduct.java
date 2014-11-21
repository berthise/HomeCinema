/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.ProductDto;
import entities.Film;
import entities.Product;
import enums.OrderTypes;
import enums.ProductStates;
import java.util.Comparator;
import javax.persistence.EntityManager;
import managers.dtos.ProductDtoManager;

/**
 *
 * @author titou
 */
public class ManageEntitieProduct {

    public static void linkProductFilm(Film f, Product p) {
	f.addProduct(p);
	p.addFilm(f);
    }

    public static Product createProduct(ProductDto pdto, EntityManager em) {
	Product p = ProductDtoManager.makeProduct(pdto);
	p.setState(ProductStates.Activated);
	em.persist(p);
	return p;
    }

    public static Comparator<Product> getComparator(OrderTypes ot) {
	switch (ot) {
	    case SALES:
		return new comp_sales();
	    case NEW:
		return new comp_new();
	    case ALPH:
		return new comp_alph();
	    case RATING:
		return new comp_rating();
	    default:
		throw new AssertionError(ot.name());

	}
    }

    public static Double getRating(Product p) {
	Double s = 0D;
	for (Film f : p.getFilms()) {
	    s += f.getRating();
	}
	return s / p.getFilms().size();
    }
}

class comp_sales implements Comparator<Product> {

    @Override
    public int compare(Product t, Product t1) {
	return t.getNbSales().compareTo(t1.getNbSales());
    }

}

class comp_new implements Comparator<Product> {

    @Override
    public int compare(Product t, Product t1) {
	return t.getAddDate().compareTo(t1.getAddDate());
    }

}

class comp_alph implements Comparator<Product> {

    @Override
    public int compare(Product t, Product t1) {
	return t.getName().compareTo(t1.getName());
    }

}

class comp_rating implements Comparator<Product> {

    @Override
    public int compare(Product t, Product t1) {
	return ManageEntitieProduct.getRating(t).compareTo(ManageEntitieProduct.getRating(t1));
    }

}
