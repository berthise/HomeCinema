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



    public static Double getRating(Product p) {
	Double s = 0D;
	for (Film f : p.getFilms()) {
	    s += f.getRating();
	}
	return s / p.getFilms().size();
    }

    public static void unlinkProductFilm(Film f, Product p) {
	f.removeProduct(p);
	p.removeFilm(f);
    }


}
