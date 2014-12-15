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
import enums.Lang;
import enums.OrderTypes;
import enums.ProductStates;
import enums.ProductTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public Long createProductWithFilm(FilmDto fdto, VideoDto trailer, VideoDto vid, Double price, Lang lang) {
	Film f = ManageEntitieFilm.createFilmWithVideo(fdto, trailer, vid, lang, em);
	Product p = new Product(f, price);
	f.setMain_product(p);
	em.merge(f);
	em.persist(p);
	return p.getId();
    }

    @Override
    public List<ProductDto> getAllProduct(Lang lang) {
	Query q = em.createQuery("From Product p", Product.class);
	List<Product> lp = q.getResultList();
	List<ProductDto> lpdto = new ArrayList<ProductDto>();
	for (Product p : lp) {
	    lpdto.add(ProductDtoManager.getDto(p, lang));
	}
	return lpdto;
    }

    @Override
    public Long createProduct(ProductDto pdto, Lang lang) {
	return ManageEntitieProduct.createProduct(pdto, lang, em).getId();
    }

    @Override
    public void addFilms(Long pid, Lang lang, List<FilmDto> lfdto) {
	for (FilmDto fdto : lfdto) {
	    addFilm(pid, fdto, lang, false);
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
    public void addFilm(Long pid, FilmDto fdto, Lang lang, boolean main) {
	Product p = em.find(Product.class, pid);
	Film f = ManageEntitieFilm.createFilm(fdto, lang, em);
	ManageEntitieProduct.linkProductFilm(f, p);
	if (main) {
	    f.setMain_product(p);
	}
	em.merge(f);
	em.merge(p);
    }

    @Override
    public void removeFilm(Long pid, Long fid) {
	Product p = em.find(Product.class, pid);
	Film f = em.find(Film.class, fid);
	ManageEntitieProduct.unlinkProductFilm(f, p);
	if (Objects.equals(f.getMain_product().getId(), pid)) {
	    f.setMain_product(null);
	}
	if (p.getFilms().isEmpty()) {
	    p.setState(ProductStates.Unactivated);
	}
	em.merge(f);
	em.merge(p);
    }

    @Override
    public List<FilmDto> getFilms(Long pid, Lang lang) {
	Product p = em.find(Product.class, pid);
	List<FilmDto> lfdto = new ArrayList<>();
	for (Film f : p.getFilms()) {
	    lfdto.add(FilmDtoManager.getDto(f, lang));
	}
	return lfdto;
    }

    @Override
    public ProductDto getProduct(Long pid, Lang lang) {
	Product p = em.find(Product.class, pid);
	return ProductDtoManager.getDto(p, lang);
    }

    @Override
    public ProductDto mergeOrSave(ProductDto pdto, Lang lang) {
	return ProductDtoManager.getDto(ProductDtoManager.mergeOrSave(pdto, lang, em), lang);
    }

    @Override
    public FilteredListProductsDto getFilteredProducts(Long actor, Long director, List<Long> lgdto, String mode, String str, String year1, String year2, OrderTypes sort, Integer limit, Integer row, ProductTypes main, final Lang lang){
      
	String query = "From PRODUCTS p join FILMS_PRODUCTS  on p.ID_PRODUCT = products_ID_PRODUCT join FILMS f on films_ID_FILM =f.ID_FILM join PRODUIT_NAME pn on ID_PRODUCT=PRODUIT_ID  where p.STATE_=0 ";
	if (row == null) {
	    row = 0;
	}
	if (limit == null) {
	    limit = 100;
	}
	if (actor != null && !actor.equals(0L)) {
	    query += " and f.ID_FILM in (select is_actor_of_ID_FILM from ACTORS  where actors_ID_PERSON=" + actor + ") ";
	}
	if (director != null && !director.equals(0L)) {
	    query += " and f.ID_FILM in (select is_director_of_ID_FILM from DIRECTORS  where directors_ID_PERSON=" + director + ") ";
	}
	if (lgdto != null && !lgdto.isEmpty()) {
	    if (mode.equals("OR")) {
		query += " and f.ID_FILM in ( select Film_ID_FILM  from FILMS_GENRES g where ";
		boolean first = true;
		for (Long g : lgdto) {
		    if (first) {
			first = false;
		    } else {
			query += " or ";
		    }
		    query += " g.genre_ID_GENRE =" + g;
		}

		query += " ) ";
	    } else {
		for (Long g : lgdto) {
		   query += " and f.ID_FILM in ( select Film_ID_FILM  from FILMS_GENRES g where  g.genre_ID_GENRE =" + g+" ) ";
		}
	    }
	}
	if (str != null && !str.equals("")) {
	    query += " and  NAME like '%" + str + "%' ";

	}
	if (year1 != null && !year1.equals("")) {
	    query += " and  f.RELEASE_DATE > '" + year1 + "-01-01' ";
	}
	if (year2 != null && !year2.equals("")) {
	    query += " and  f.RELEASE_DATE < '" + year2 + "-12-31' ";
	}
	if (main.equals(ProductTypes.Main)) {
	    query += " and p.ID_PRODUCT = f.MAIN_PRODUCT ";
	} else if (main.equals(ProductTypes.Pack)) {
	    query += " and p.ID_PRODUCT <> f.MAIN_PRODUCT ";
	}
	try {
	    Query qnb = em.createNativeQuery("select COUNT(distinct p.ID_PRODUCT) " + query);

	    Long nb = (Long) qnb.getSingleResult();
	    switch (sort) {
		case RATING:
		    query += " order by f.RATING  desc ";
		    break;
		case SALES:
		    query += " order by p.NB_SALES desc";
		    break;
		case ALPH:
		    query += "ORDER BY pn.NAME ";
		    break;
		case NEW:
		    query += " order by p.ADD_DATE desc";
		    break;
		case RAND:
		    query += "order by rand()";
		    break;
	    }
	    if (row < 0) {
		row = 0;
	    }

	    //query += " LIMIT " + limit + " OFFSET " + row;
	    Query q = em.createNativeQuery("select distinct p.*  " + query, Product.class);
	    q.setFirstResult(row);
	    q.setMaxResults(limit);
	    List<Product> lpdto = q.getResultList();

	    List<ProductDto> res = new ArrayList<>();
	    for (Product p : lpdto) {
		res.add(ProductDtoManager.getDto(p, lang));
	    }
	    return new FilteredListProductsDto(res, nb.intValue());
	} catch (Exception e) {
	  return getFilteredProductsDerby(actor, director, lgdto, mode, str, year1, year2, sort, limit,row, main, lang);
	
//	    return new FilteredListProductsDto(new ArrayList<ProductDto>(), -1);
	}
    }
   
  public FilteredListProductsDto getFilteredProductsDerby(Long actor, Long director, List<Long> lgdto, String mode, String str, String year1, String year2, OrderTypes sort, Integer limit, Integer row, ProductTypes main, final Lang lang) {
//
    String query = "From PRODUCTS p join FILMS_PRODUCTS on p.ID_PRODUCT = products_ID_PRODUCT join FILMS f on films_ID_FILM =f.ID_FILM join PRODUIT_NAME pn on ID_PRODUCT=PRODUIT_ID where p.STATE_=0 ";
    if (row == null) {
      row = 0;
    }
    if (limit == null) {
      limit = 100;
    }
    if (actor != null && !actor.equals(0L)) {
      query += " and f.ID_FILM in (select is_actor_of_ID_FILM from ACTORS where actors_ID_PERSON=" + actor + ") ";
    }
    if (director != null && !director.equals(0L)) {
      query += " and f.ID_FILM in (select is_director_of_ID_FILM from DIRECTORS where directors_ID_PERSON=" + director + ") ";
    }
    if (lgdto != null && !lgdto.isEmpty()) {
      query += " and f.ID_FILM in ( select Film_ID_FILM from FILMS_GENRES g where ";
      boolean first = true;
      for (Long g : lgdto) {
	if (first) {
	  first = false;
	} else {
	  query += " or ";
	}
	query += " g.genre_ID_GENRE =" + g;
      }
      if (mode.equals("OR")) {
	query += " ) ";
      } else {
	query += " GROUP BY Film_ID_FILM HAVING count( genre_ID_GENRE ) = " + lgdto.size() + " )";
      }
    }
    if (str != null && !str.equals("")) {
      query += " and NAME like '%" + str + "%' ";
    }
    if (year1 != null && !year1.equals("")) {
      query += " and f.RELEASE_DATE > '" + year1 + "-01-01' ";
    }
    if (year2 != null && !year2.equals("")) {
      query += " and f.RELEASE_DATE < '" + year2 + "-12-31' ";
    }
    if (main.equals(ProductTypes.Main)) {
      query += " and p.ID_PRODUCT = f.MAIN_PRODUCT ";
    } else if (main.equals(ProductTypes.Pack)) {
      query += " and p.ID_PRODUCT <> f.MAIN_PRODUCT ";
    }
    try {
      Query qnb = em.createNativeQuery("select COUNT(distinct p.ID_PRODUCT) " + query);
      Long nb = new Long(qnb.getSingleResult().toString());
      switch (sort) {
// case RATING:
// query += " order by f.RATING desc ";
// break;
	case SALES:
	  query += " order by p.NB_SALES desc";
	  break;
// case ALPH:
// query += "ORDER BY pn.NAME ";
// break;
	case NEW:
	  query += " order by p.ADD_DATE desc";
	  break;
	case RAND:
	  row = (int) (Math.random() * (float) (nb.intValue() - limit));
	  break;
      }
      if (row < 0) {
	row = 0;
      }

      Query q = em.createNativeQuery("SELECT DISTINCT p.* " + query, Product.class);
      q.setMaxResults(limit);
      q.setFirstResult(row);
      List<Product> lpdto = q.getResultList();
      if (sort == OrderTypes.ALPH) {
	Collections.sort(lpdto, new Comparator<Product>() {
	  @Override
	  public int compare(Product p1, Product p2) {
	    return p1.getName(lang).compareTo(p2.getName(lang));
	  }
	});
      } else if (sort == OrderTypes.RATING) {
	Collections.sort(lpdto, new Comparator<Product>() {
	  @Override
	  public int compare(Product p1, Product p2) {
	    return p1.getAverageRate().compareTo(p2.getAverageRate());
	  }
	});
      }
      	    List<ProductDto> res = new ArrayList<>();
	    for (Product p : lpdto) {
		res.add(ProductDtoManager.getDto(p, lang));
	    }
	    return new FilteredListProductsDto(res, nb.intValue());
	} catch (Exception e) {
	  return getFilteredProductsDerby(actor, director, lgdto, mode, str, year1, year2, sort, limit,row, main, lang);
	
//	    return new FilteredListProductsDto(new ArrayList<ProductDto>(), -1);
	}
    }

    public Long getNbProduct() {
	String sql = "SELECT COUNT(p) FROM Product p";
	Query q = em.createQuery(sql);
	return (long) q.getSingleResult();
    }

    @Override
    public List<GenreDto> getAllGenres(Lang lang) {
	Query q = em.createQuery("From Genre g", Genre.class);
	List<Genre> lg = q.getResultList();
	List<GenreDto> lgdto = new ArrayList<>();
	for (Genre g : lg) {
	    lgdto.add(GenreDtoManager.getDto(g, lang));
	}
	return lgdto;
    }

    @Override
    public void activate(Long pid) {
	Product p = em.find(Product.class, pid);
	if (!p.getFilms().isEmpty()) {
	    p.setState(ProductStates.Activated);
	    em.merge(p);
	}
    }

    @Override
    public void deactivate(Long pid) {
	Product p = em.find(Product.class, pid);
	p.setState(ProductStates.Unactivated);
	em.merge(p);
    }
}
