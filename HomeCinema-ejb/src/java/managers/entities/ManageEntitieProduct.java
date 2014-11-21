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
    public static void linkProductFilm(Film f, Product p)
    {
        f.addProduct(p);
        p.addFilm(f);
    }
    
    public static Product createProduct(ProductDto pdto,EntityManager em)
    {
        Product p = ProductDtoManager.makeProduct(pdto);
        p.setState(ProductStates.Activated);
        em.persist(p);
        return p;
    }
    
        public static Comparator<Product> getComparator(OrderTypes ot) {
	switch (ot) {
	    case SALES:
		new Comparator<Product>() {
		    @Override
		    public int compare(Product t, Product t1) {
			return t.getNbSales().compareTo(t1.getNbSales());
		    }
		};
		break;
	    case NEW:
		new Comparator<Product>() {
		    @Override
		    public int compare(Product t, Product t1) {
			return t.getAddDate().compareTo(t1.getAddDate());

		    }
		};
		break;
	    case ALPH:
		new Comparator<Product>() {
		    @Override
		    public int compare(Product t, Product t1) {
			return t.getName().compareTo(t1.getName());
		    }
		};
		break;
	    default:
		throw new AssertionError(ot.name());

	}
	return null;
    }
}
