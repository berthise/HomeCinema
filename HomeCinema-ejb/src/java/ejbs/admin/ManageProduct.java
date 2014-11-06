/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import entities.Film;
import entities.Product;
import entities.Video;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.FilmDtoManager;
import managers.dtos.ProductDtoManager;
import managers.dtos.VideoDtoManager;
import managers.entities.ManageEntitieFilm;
import managers.entities.ManageEntitieProduct;

/**
 *
 * @author titou
 */
@Stateless
public class ManageProduct implements ManageProductRemote {

    @PersistenceContext
    public EntityManager em;

    @Override
    public Long createProductWithFilm(FilmDto fdto, VideoDto trailer, VideoDto vid, Integer price) {
        Film f = ManageEntitieFilm.createFilmWithVideo(fdto, trailer, vid, em);
        Product p = new Product(f, price);
        f.setMain_product(p);
        em.persist(p);
        return p.getId();
    }

    public Long createProduct(ProductDto pdto)
    {
        Product p = ProductDtoManager.makeProduct(pdto);
        em.persist(p);
        return p.getId();
    }
    @Override
    public void addFilms(Long pid, List<FilmDto> lfdto) {
        for (FilmDto fdto : lfdto) {
            addFilm(pid, fdto, false);
        }
    }

    @Override
    public void addExistingFilms(Long pid, List<Long> lfid) {
        for (Long fid : lfid) {
            addExistingFilm(pid, fid, false);
        }
    }

    @Override
    public void addExistingFilm(Long pid, Long fid, boolean main) {
        Product p = em.find(Product.class, pid);
        Film f = em.find(Film.class, fid);
        ManageEntitieProduct.linkProductFilm(f, p);
        if (main) {
            f.setMain_product(p);
        }
    }

    @Override
    public void addFilm(Long pid, FilmDto fdto, boolean main) {
        Product p = em.find(Product.class, pid);
        Film f = ManageEntitieFilm.createFilm(fdto, em);
        ManageEntitieProduct.linkProductFilm(f, p); 
        if (main) {
            f.setMain_product(p);
        }
    }

}
