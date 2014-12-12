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
import java.util.List;
import java.util.Objects;
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
    public FilteredListProductsDto getFilteredProducts(Long actor, Long director, List<Long> lgdto, String mode, String str, String year1, String year2, OrderTypes sort, Integer limit, Integer row, ProductTypes main, Lang lang){
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
	    if (mode.equals("OR")) {
		query += " ) ";
	    } else {
		query += " GROUP BY Film_ID_FILM HAVING count( genre_ID_GENRE ) = " + lgdto.size() + " )";
	    }
	}
	if (str != null && !str.equals("")) {
	    query += " and NAME like '%" + str + "%' ";

	}
	if (year1!=null && !year1.equals(""))
	{
	    query += " and  f.RELEASE_DATE > '"+year1+"-01-01' " ;
	}
		if (year2!=null && !year2.equals(""))
	{
	    query += " and  f.RELEASE_DATE < '"+year2+"-12-31' " ;
	}
	if (main.equals(ProductTypes.Main)) {
	    query += " and p.ID_PRODUCT in  ( select products_ID_PRODUCT from FILMS_PRODUCTS  group by products_ID_PRODUCT having count(*)=1 ) ";
	} else if (main.equals(ProductTypes.Pack)) {
	    query += " and p.ID_PRODUCT in  ( select products_ID_PRODUCT from FILMS_PRODUCTS  group by products_ID_PRODUCT having count(*)>1 ) ";
	}
	try {
	Query qnb = em.createNativeQuery("select COUNT(distinct p.ID_PRODUCT) " + query);

	Long nb = (Long) qnb.getSingleResult();
	switch (sort) {
	 case RATING:
	 query = " from PRODUCTS p natural join  (select p.ID_PRODUCT,avg(f.RATING) as rating "+query+ "group by p.ID_PRODUCT ) r order by rating  desc ";
	 break;
	 case SALES:
	 query += "order by p.NB_SALES desc";
	 break;
	 case ALPH:
	 query += "order by pn.NAME ";
	 break;
	 case NEW:
	 query += "order by p.ADD_DATE desc";
	 break;
	 case RAND:
	 row = (int) (Math.random() * (getNbProduct() - limit));
	 break;
	 }
	if (row < 0) {
	    row = 0;
	}
	
	query += " LIMIT " + limit + " OFFSET " + row;
	Query q = em.createNativeQuery("select distinct p.*  " + query, Product.class);
	List<Product> lpdto = q.getResultList();
	
	List<ProductDto> res = new ArrayList<>();
	for (Product p : lpdto) {
	    res.add(ProductDtoManager.getDto(p, lang));
	}
	return new FilteredListProductsDto(res, nb.intValue());
	}catch (Exception e)
	{
	    return new FilteredListProductsDto(new ArrayList<ProductDto>(), -1);
	}
    }

    /*@Override
     public FilteredListProductsDto getFilteredProducts(Long actor, Long director, List<Long> lgdto, String str, String year, OrderTypes sort, Integer limit, Integer row, ProductTypes main,Lang lang) {
     boolean or = true;//join p.name pn      join f.title t
     String query = "From Product p join p.films f left join f.genre g left join f.actors a left join f.directors d where p.state=:active ";
     if (row == null) {
     row = 0;
     }
     if (limit == null) {
     limit = 100;
     }
     if (actor != null && !actor.equals(0L)) {
     query += " and a.id=" + actor;
     }
     if (director != null && !director.equals(0L)) {
     query += "and d.id=" + director;
     }
     if (lgdto != null && !lgdto.isEmpty()) {
     query += " and (";
     boolean first = true;
     for (Long g : lgdto) {
     if (first) {
     first = false;
     } else {
     if (or) {
     query += " or ";
     } else {
     query += " and ";
     }
     }
     query += " g.id=" + g;
     }
     query += " ) ";
     }
     if (str != null && !str.equals("")) {
     query += " and (  p.id IN (select p.id from Produit p join p.name pn where VALUE(pn) LIKE '%"+str+"%'  )) ";//or VALUE(pn) LIKE '%"+str+"%'
     }
     if (main.equals(ProductTypes.Main)) {
     query += " and size(p.films )=1 ";
     } else if (main.equals(ProductTypes.Pack)) {

     query += " and size(p.films )>1 ";
     } 
     Query qnb = em.createQuery("select COUNT(distinct p) "+ query);
     qnb.setParameter("active", ProductStates.Activated);
     Long nb = (Long) qnb.getSingleResult();
     switch (sort) {
     case RATING:
     query += "group by p order by  AVG(f.rating) desc ";
     break;
     case SALES:
     query += "order by p.nbSales desc";
     break;
     case ALPH:
     //query += "order by VALUE(pn) ";
     break;
     case NEW:
     query += "order by p.addDate desc";
     break;
     case RAND:
     row = (int) (Math.random() * (getNbProduct() - limit));
     break;
     }
     if (row < 0) {
     row = 0;
     }
     Query q = em.createQuery("select distinct p  "+query, Product.class);
     q.setParameter("active", ProductStates.Activated);
     q.setFirstResult(row);
     q.setMaxResults(limit);
     List<Product> lpdto = q.getResultList();
     List<ProductDto> res = new ArrayList<>();
     for (Product p : lpdto) {
     res.add(ProductDtoManager.getDto(p,lang));
     }
     return new FilteredListProductsDto(res, nb.intValue());
     }
     */
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
