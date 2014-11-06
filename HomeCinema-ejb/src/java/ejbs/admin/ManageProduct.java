/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
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
    public void createProductWithFilm(FilmDto fdto, VideoDto trailer, VideoDto vid, Integer price) {
        Film f = ManageEntitieFilm.createFilmWithVideo(fdto, trailer, vid,em);
        Product p = new Product(f, price);
        f.setMain_product(p);
        em.persist(p);
    }
    
    public void addFilmsToProduct(Long pid,List<FilmDto> lfdto)
    {
        for (FilmDto fdto : lfdto)
        {
            addFilmToProduct(pid,fdto);
        }
    }
    
    public void addFilmToProduct(Long pid, FilmDto fdto)
    {
        Product p = em.find(Product.class, pid);
        Film f= ManageEntitieFilm.createFilm(fdto, em);
        ManageEntitieProduct.linkProductFilm(f, p);
    }

    
}
