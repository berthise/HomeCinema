/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.CaddieDto;
import dtos.PaymentDto;
import ejbs.ManageTransactionRemote;
import entities.Caddy;
import entities.Film;
import entities.Product;
import entities.Transaction;
import entities.User;
import enums.Lang;
import enums.ProductStates;
import enums.TransactionStates;
import exception.DeactivatedProductException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.CaddieDtoManager;
import managers.entities.ManageEntitieUser;
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
    public CaddieDto getCaddieDto(Long id_user,Lang lang) {
        User u = em.find(User.class, id_user);
        return CaddieDtoManager.getDto(u.getCaddy(), lang);
    }
        @Override
    public Set<Long> getCaddieProductIds(Long id_user) {
          Set<Long> lfid = new HashSet<>();
        User u = em.find(User.class, id_user);
	if ( u.getCaddy() == null) return lfid;
        for ( Product p: u.getCaddy().getProducts()) {
	    lfid.add(p.getId());
	}
	return lfid;
    }
    
    @Override
    public CaddieDto addProduct(Long user, Long id,Lang lang) throws DeactivatedProductException
    {
        User u = em.find(User.class, user);
        if (u.getCaddy()==null)
        {
            u.setCaddy(new Caddy());
            em.persist(u.getCaddy());
            em.merge(u);
        }
	Product p = em.find(Product.class,id);
	if ( p.getState() != ProductStates.Activated ) {
	  throw new DeactivatedProductException();
	}
        u.getCaddy().addCaddy(p);
        em.merge(u.getCaddy());
        return CaddieDtoManager.getDto(u.getCaddy(),lang);
    }

    @Override
   public CaddieDto removeProduct(Long user ,Long id,Lang lang)
   {
               User u = em.find(User.class, user);
        if (u.getCaddy()==null)
        {
            u.setCaddy(new Caddy());
            em.persist(u.getCaddy());
            em.merge(u);
        }
        Product p = em.find(Product.class, id);
        u.getCaddy().removeCaddy(p);
        em.merge(u.getCaddy());
        return CaddieDtoManager.getDto(u.getCaddy(),lang);
   }
    
    @Override
    public Long validate(Long user, PaymentDto pdto)
    {
        User u = em.find(User.class, user);
        Transaction t = new Transaction();
        t.setAddDate(new Date());
        t.setProducts(u.getCaddy().getProducts());
        t.setTotalPrice(UtilCaddie.totalprice(u.getCaddy()));
        t.setUser(u);
        t.setState(TransactionStates.Prepared);
        em.persist(t);
        em.remove(u.getCaddy());
        u.setCaddy(null);
        u.addTransaction(t);
        em.merge(u);
        return t.getId();
    }
    
    @Override
    public void validatePayement(Long id,Long btn)
    {
        Transaction t = em.find(Transaction.class, id);
        ManageEntitieUser.addProducts(t.getUser(),t.getProducts(),em);
        t.setBankTransNum(btn);
        t.setState(TransactionStates.Done);
        em.merge(t.getUser());
        em.merge(t);
    }

}
