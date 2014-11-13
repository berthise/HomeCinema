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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public Long createProductWithFilm(FilmDto fdto, VideoDto trailer, VideoDto vid, Double price) {
        Film f = ManageEntitieFilm.createFilmWithVideo(fdto, trailer, vid, em);
        Product p = new Product(f, price);
        f.setMain_product(p);
        em.merge(f);
        em.persist(p);
        return p.getId();
    }

    public List<ProductDto> getAllProduct() {
        Query q = em.createQuery("From Product p",Product.class);
        List<Product> lp = q.getResultList();
        List<ProductDto> lpdto = new ArrayList<ProductDto>();
        for (Product p : lp) {
            lpdto.add(ProductDtoManager.getDto(p));
        }
        return lpdto;
    }

    @Override
    public Long createProduct(ProductDto pdto) {
        return ManageEntitieProduct.createProduct(pdto, em).getId();
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
        em.merge(p);
        em.merge(f);
    }

    @Override
    public void addFilm(Long pid, FilmDto fdto, boolean main) {
        Product p = em.find(Product.class, pid);
        Film f = ManageEntitieFilm.createFilm(fdto, em);
        ManageEntitieProduct.linkProductFilm(f, p);
        if (main) {
            f.setMain_product(p);
        }
        em.merge(f);
        em.merge(p);
    }
    
    public List<FilmDto> getFilms(Long pid)
    {
        Product p = em.find(Product.class, pid);
        List<FilmDto> lfdto = new ArrayList<>();
        for ( Film f : p.getFilms())
        {
            lfdto.add(FilmDtoManager.getDto(f));
        }
        return lfdto;
    }

}
