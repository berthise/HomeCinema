/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.VideoDto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.FilmDtoManager;
import ejbs.ManageFilmRemote;
import entities.Film;
import entities.Product;
import entities.Video;
import java.util.ArrayList;
import java.util.List;
import managers.dtos.VideoDtoManager;

/**
 *
 * @author titou
 */
@Stateless
public class ManageFilm implements ManageFilmRemote {
    
    @PersistenceContext
    public EntityManager em;
    
    @Override
    public void createFilmProduct(FilmDto fdto, VideoDto trailer, VideoDto vid,Integer price) {
        Film f = FilmDtoManager.makeFilm(fdto);
        Video t = VideoDtoManager.makeVideo(trailer);
        Video v = VideoDtoManager.makeVideo(vid);
        f.addVideoFile(v);
        f.setTrailler(t);
        Product p = new Product(f, price);
        f.setMain_product(p);
        em.persist(v);
        em.persist(t);
        em.persist(f);
        em.persist(p);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void createFilm(FilmDto fdto) {
         Film f = FilmDtoManager.makeFilm(fdto);
         em.persist(f);
    }


}
