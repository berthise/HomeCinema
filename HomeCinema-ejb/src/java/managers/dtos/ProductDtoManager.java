/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.FilmDto;
import dtos.ProductDto;
import entities.Film;
import entities.Product;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class ProductDtoManager {

    public static ProductDto getDto(Product p) {
        if (p == null) {
            return null;
        }

        ProductDto pdto = new ProductDto();
        pdto.id = p.getId();
        pdto.addDate = p.getAddDate();
        pdto.name = p.getName();
        pdto.nbSales = p.getNbSales();
        pdto.price = p.getPrice();
        pdto.state = p.getState();

        return pdto;
    }

    public static Product makeProduct(ProductDto pdto) {
        Product p = new Product();
        p.setId(pdto.id);
        p.setName(pdto.name);
        p.setPrice(pdto.price);
        p.setState(pdto.state);

        return p;
    }

    public static Product mergeOrSave(ProductDto pdto, EntityManager em) {
        Product p = em.find(Product.class, pdto.id);
        if (p == null) {
            p = makeProduct(pdto);
            em.persist(p);
        } else {
            p.setName(pdto.name);
            p.setPrice(pdto.price);
            p.setState(pdto.state);
            em.merge(p);
        }
        return p;
    }
}
