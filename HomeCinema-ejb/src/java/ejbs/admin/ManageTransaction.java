/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.CaddieDto;
import dtos.FilmDto;
import dtos.ProductDto;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import ejbs.ManageTransactionRemote;
import entities.Caddy;
import entities.Film;
import entities.Product;
import entities.Transaction;
import entities.User;
import entities.Video;
import enums.TransactionStates;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.CaddieDtoManager;
import managers.dtos.FilmDtoManager;
import managers.dtos.ProductDtoManager;
import managers.dtos.VideoDtoManager;
import managers.entities.ManageEntitieFilm;
import managers.entities.ManageEntitieProduct;
import utils.UtilCaddie;

/**
 *
 * @author titou
 */
@Stateless
public class ManageTransaction implements ManageTransactionRemote {

    @PersistenceContext
    public EntityManager em;

    @Override
    public CaddieDto getCaddieDto(Long id_user) {
        User u = em.find(User.class, id_user);
       // return CaddieDtoManager.getDto(u.getCaddy());
        return new CaddieDto();
    }
    
    public CaddieDto addProduct(Long user, Long id)
    {
        User u = em.find(User.class, user);
        //u.addCaddie(em.find(Product.class,id);
        //return CaddieDtoManager.getDto(u.getCaddy());
        return new CaddieDto();
    }
    
    public void Validate(Long user)
    {
        User u = em.find(User.class, user);
        Transaction t = new Transaction();
        t.setAddDate(new Date());
        //t.setProducts(u.getCaddy());
        t.setTotalPrice(UtilCaddie.totalprice(u.getCaddy()));
        t.setUser(u);
        t.setState(TransactionStates.Prepared);
        em.persist(t);
        u.setCaddy(new Caddy());
    }
}
