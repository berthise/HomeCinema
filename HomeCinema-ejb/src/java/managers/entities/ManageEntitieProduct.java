/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.ProductDto;
import entities.Film;
import entities.Product;
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
        em.persist(p);
        return p;
    }
}
