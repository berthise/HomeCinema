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
import entities.Film;
import entities.Product;
import entities.User;
import entities.Video;
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
        return CaddieDtoManager.getDto(u.getCaddy());
    }
}
