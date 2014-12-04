/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.FilteredListProductsDto;
import dtos.GenreDto;
import dtos.ProductDto;
import dtos.VideoDto;
import ejbs.ManageProductRemote;
import entities.Film;
import entities.Genre;
import entities.Product;
import enums.OrderTypes;
import enums.ProductTypes;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managers.dtos.FilmDtoManager;
import managers.dtos.GenreDtoManager;
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
	//q.setMaxResults(100);
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
    public FilteredListProductsDto getFilteredProducts(Long actor, Long director, List<Long> lgdto, String str, String year, OrderTypes sort, Integer limit, Integer row, ProductTypes main) {
	String query = " From Product p join p.films f join f.genre g join f.actors a join f.directors d ";
	if (row == null) {
	    row = 0;
	}
	if (limit == null) {
	    limit = 100;
	}
	boolean where = false;
	if (actor != null && !actor.equals(0L)) {
	    query += " where  a.id=" + actor;
	    where = true;
	}
	if (director != null && !director.equals(0L)) {
	    if (where) {
		query += " and ";
	    } else {
		query += " where ";
		where = true;
	    }
	    query += " d.id=" + director;
	}
	if (lgdto != null && !lgdto.isEmpty()) {
	    if (where) {
		query += " and (";

	    } else {
		query += " where (";
		where = true;
	    }
	    boolean first = true;
	    for (Long g : lgdto) {
		if (first) {
		    first = false;
		} else {
		    query += " or ";
		}
		query += " g.id=" + g;
	    }
	    query += " ) ";
	}
	if (str != null && !str.equals("")) {
	    if (where) {
		query += " and (";
		where = true;
	    } else {
		query += " where (";
	    }
	    query += "  f.title like '%" + str + "%' or p.name like '%" + str + "%' ) ";
	}
	if (main.equals(ProductTypes.Main))
	{
	    	    if (where) {
		query += " and (";
		where = true;
	    } else {
		query += " where (";
	    }
		    query+="size(p.films )=1 ";
	}
	else if (main.equals(ProductTypes.Pack))
	{
	    	    	    if (where) {
		query += " and (";
		where = true;
	    } else {
		query += " where (";
	    }
		    query+="size(p.films )>1 ";
	}
	Query qnb = em.createQuery("select COUNT(distinct p) " + query);
	Long nb = (Long) qnb.getSingleResult();
	switch (sort) {
	    case RATING:
		query += "group by p order by  AVG(f.rating) desc ";
		break;
	    case SALES:
		query += "order by p.nbSales desc";
		break;
	    case ALPH:
		query += "order by p.name ";
		break;
	    case NEW:
		query += "order by p.addDate desc";
		break;
	    case RAND:
		row = (int) (Math.random() * (getNbProduct() - limit));
		break;
	}

	Query q = em.createQuery("select distinct p " + query, Product.class);
	q.setFirstResult(row);
	q.setMaxResults(limit);
	List<Product> lpdto = q.getResultList();
	List<ProductDto> res = new ArrayList<>();
	for (Product p : lpdto) {
	    res.add(ProductDtoManager.getDto(p));
	}
	return new FilteredListProductsDto(res, nb.intValue());
    }

    public Long getNbProduct() {
	String sql = "SELECT COUNT(p) FROM Product p";
	Query q = em.createQuery(sql);
	return (long) q.getSingleResult();
    }

    @Override
    public List<GenreDto> getAllGenres() {
	Query q = em.createQuery("From Genre g", Genre.class);
	List<Genre> lg = q.getResultList();
	List<GenreDto> lgdto = new ArrayList<GenreDto>();
	for (Genre g : lg) {
	    lgdto.add(GenreDtoManager.getDto(g));
	}
	return lgdto;
    }
}
