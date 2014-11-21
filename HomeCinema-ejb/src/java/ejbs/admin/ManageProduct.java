/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.VideoDto;
import ejbs.ManageProductRemote;
import entities.Film;
import entities.Product;
import enums.OrderTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managers.dtos.FilmDtoManager;
import managers.dtos.ProductDtoManager;
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

    @Override
    public List<ProductDto> getAllProduct() {
	Query q = em.createQuery("From Product p", Product.class);
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

    @Override
    public List<FilmDto> getFilms(Long pid) {
	Product p = em.find(Product.class, pid);
	List<FilmDto> lfdto = new ArrayList<>();
	for (Film f : p.getFilms()) {
	    lfdto.add(FilmDtoManager.getDto(f));
	}
	return lfdto;
    }

    @Override
    public ProductDto getProduct(Long pid) {
	Product p = em.find(Product.class, pid);
	return ProductDtoManager.getDto(p);
    }

    @Override
    public ProductDto mergeOrSave(ProductDto pdto) {
	return ProductDtoManager.getDto(ProductDtoManager.mergeOrSave(pdto, em));
    }

    @Override
    public List<ProductDto> findProducts(Long actor, Long director, List<Long> lgdto, String str, String year) {
	Query q = em.createQuery("From Product p", Product.class);
	List<Product> lp = q.getResultList();
	List<ProductDto> res = new ArrayList<>();

	for (Product p : lp) {
	    boolean add = false;
	    for (Film f : p.getFilms()) {
		if (ManageEntitieFilm.filterFilm(f, actor, director, lgdto, str, year, em)) {
		    add = true;
		}
	    }
	    if (add) {
		res.add(ProductDtoManager.getDto(p));
	    }
	}
	return res;
    }

    @Override
    public List<ProductDto> getFilteredProducts(Long actor, Long director, List<Long> lgdto, String str, String year, OrderTypes sort, Integer limit, Integer row) {
	List<ProductDto> lpdto = this.findProducts(actor, director, lgdto, str, year);
	if (sort.equals(OrderTypes.RAND)) {
	    Collections.shuffle(lpdto);
	}
	if (!sort.equals(OrderTypes.NO)) {
	    Collections.sort(lpdto, ProductDtoManager.getComparator(sort));
	}
	if (row == null || row < 0) {
	    row = 0;
	}
	if (limit == null || limit == 0) {
	    limit = lpdto.size() - row;
	}
	if (limit > lpdto.size()) {
	    limit = lpdto.size() + 1;
	}
	if (limit <= row) {
	    return new ArrayList<>();
	}
	return lpdto.subList(row, row + limit);
    }
}
