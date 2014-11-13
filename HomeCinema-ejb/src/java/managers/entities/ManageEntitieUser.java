/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import entities.Film;
import entities.Product;
import entities.User;
import entities.UsersFilms;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class ManageEntitieUser {

    public static void addFilm(User u, Film f, EntityManager em) {
        UsersFilms uf = new UsersFilms();
        uf.setFilm(f);
        em.persist(uf);
        u.addFilm(uf);
    }

    public static void addFilms(User u, List<Film> lf, EntityManager em) {
        for (Film f : lf) {
            addFilm(u, f, em);
        }
    }

    public static void addProduct(User u, Product p, EntityManager em) {
        addFilms(u, p.getFilms(), em);
    }

    public static void addProducts(User u, Set<Product> lp, EntityManager em) {
        for (Product p : lp) {
            addProduct(u, p, em);
        }
    }
}
