/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.GenreDto;
import dtos.PersonDto;
import dtos.ProductDto;
import dtos.VideoDto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.FilmDtoManager;
import ejbs.ManageFilmRemote;
import entities.Film;
import entities.Genre;
import entities.Person;
import entities.Product;
import entities.Video;
import enums.Lang;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import managers.dtos.GenreDtoManager;
import managers.dtos.PersonDtoManager;
import managers.dtos.ProductDtoManager;
import managers.entities.ManageEntitieFilm;
import managers.entities.ManageEntitieGenre;
import managers.entities.ManageEntitiePerson;
import managers.entities.ManageEntitieVideo;

/**
 *
 * @author titou
 */
@Stateless
public class ManageFilm implements ManageFilmRemote {

    @PersistenceContext
    public EntityManager em;
    private Object ManageEntitiesFilm;

    @Override
    public Long createFilm(FilmDto fdto,Lang lang) {
	return ManageEntitieFilm.createFilm(fdto,lang, em).getId();
    }

    @Override
    public List<FilmDto> getAllFilm(Lang lang) {
	Query q = em.createQuery("From Film f", Film.class);
	//q.setMaxResults(100);
	
	List<Film> lf = q.getResultList();
	System.out.println(lf.size());
	List<FilmDto> lfdto = new ArrayList<>();
	for (Film f : lf) {
	    lfdto.add(FilmDtoManager.getDto(f,lang));
	}
	return lfdto;
    }

    @Override
    public FilmFicheDto getDtoFromId(Long id,Lang lang ) {
	Film f = em.find(Film.class, id);
	return FilmDtoManager.getDtoForFiche(f,lang);
    }

    @Override
    public FilmDto getFilmFromId(Long id,Lang lang) {
	Film f = em.find(Film.class, id);
	return FilmDtoManager.getDto(f,lang);
    }

    @Override
    public void setTrailer(Long fid, VideoDto trailer) {
	Film f = em.find(Film.class, fid);
	f.setTrailler(ManageEntitieVideo.createVideo(trailer, em));
	em.merge(f);
    }

    @Override
    public void addVideos(Long fid, List<VideoDto> lvdto) {
      	Film f = em.find(Film.class, fid);
	for (VideoDto vdto : lvdto) {
	   f.addVideoFile(ManageEntitieVideo.createVideo(vdto, em));
	}
	em.merge(f);
    }

    @Override
    public void addVideo(Long fid, VideoDto vdto) {
	Film f = em.find(Film.class, fid);
	f.addVideoFile(ManageEntitieVideo.createVideo(vdto, em));
	em.merge(f);
    }

    @Override
    public void addExistingVideos(Long fid, List<Long> lvid) {
	for (Long vid : lvid) {
	    addExistingVideo(fid, vid);
	}
    }

    @Override
    public void addExistingVideo(Long fid, Long vid) {
	Film f = em.find(Film.class, fid);
	Video v = em.find(Video.class, vid);
	f.addVideoFile(v);
	em.merge(f);
    }

    @Override
    public void setExistingTrailer(Long fid, Long trailer) {
	Film f = em.find(Film.class, fid);
	Video v = em.find(Video.class, trailer);
	f.setTrailler(v);
	em.merge(f);
    }

    @Override
    public void addGenres(Long fid,Set<GenreDto> lgdto, Lang lang) {
	for (GenreDto gdto : lgdto) {
	    addGenre(fid, gdto,lang);
	}
    }
    


    @Override
    public void addGenre(Long fid, GenreDto gdto,Lang lang) {
	Genre g = ManageEntitieGenre.getGenre(gdto,lang , em);
	Film f = em.find(Film.class, fid);
	f.addGenre(g);
	em.merge(f);
    }

    @Override
    public void addActors(Long fid, List<PersonDto> lgdto) {
	for (PersonDto gdto : lgdto) {
	    addActor(fid, gdto);
	}
    }

    @Override
    public void addActor(Long fid, PersonDto gdto) {
	Person g = ManageEntitiePerson.getPerson(gdto, em);
	Film f = em.find(Film.class, fid);
	g.addIs_actor_of(f);
	f.addActor(g);
	em.merge(f);
    }

    @Override
    public void addDirectors(Long fid, List<PersonDto> lgdto) {
	for (PersonDto gdto : lgdto) {
	    addDirector(fid, gdto);
	}
    }

    @Override
    public void addDirector(Long fid, PersonDto gdto) {
	Person g = ManageEntitiePerson.getPerson(gdto, em);
	Film f = em.find(Film.class, fid);
	g.addIs_director_of(f);
	f.addDirector(g);
	em.merge(f);
    }

    @Override
    public List<PersonDto> getDirector(Long fid) {
	List<PersonDto> lpdto = new ArrayList<>();
	Film f = em.find(Film.class, fid);
	for (Person p : f.getDirectors()) {
	    lpdto.add(PersonDtoManager.getDto(p));
	}
	return lpdto;
    }

    @Override
    public List<PersonDto> getCasting(Long fid) {
	List<PersonDto> lpdto = new ArrayList<>();
	Film f = em.find(Film.class, fid);
	for (Person p : f.getActors()) {
	    lpdto.add(PersonDtoManager.getDto(p));
	}
	return lpdto;
    }

    @Override
    public void removeVideo(Long fid, Long vid) {
	Film f = em.find(Film.class, fid);
	Video v = em.find(Video.class, vid);
	f.removeVideo(v);
	em.merge(f);
	em.remove(v);
    }

    @Override
    public Set<GenreDto> getGenre(Long fid,Lang lang) {
	Set<GenreDto> lgdto = new HashSet<>();
	Film f = em.find(Film.class, fid);
	for (Genre g : f.getGenre()) {
	    lgdto.add(GenreDtoManager.getDto(g,lang));
	}
	return lgdto;
    }

    @Override
    public void mergeOrSave(FilmDto fdto,Lang lang) {
	FilmDtoManager.mergeOrSave(fdto,lang, em);
    }

    @Override
    public Set<ProductDto> getProducts(Long fid,Lang lang) {
	Set<ProductDto> lgdto = new HashSet<>();
	Film f = em.find(Film.class, fid);
	for (Product p : f.getProducts()) {
	    lgdto.add(ProductDtoManager.getDto(p,lang));
	}
	return lgdto;
    }

    @Override
    public ProductDto getMainProduct(long fid,Lang lang) {
	Film f = em.find(Film.class, fid);
	return ProductDtoManager.getDto(f.getMain_product(),lang);
    }

    @Override
    public void setMain(Long fid, Long pid) {
	Film f = em.find(Film.class, fid);
	Product p = em.find(Product.class, pid);
	f.setMain_product(p);
	em.merge(f);
    }

    /*
    @Override
    public List<FilmDto> findFilms(Long actor, Long director, List<Long> lgdto, String str, String year) {
	Query q = em.createQuery("From Film f", Film.class);
	List<Film> lf = q.getResultList();
	List<FilmDto> res = new ArrayList<>();

	for (Film f : lf) {
	    if (ManageEntitieFilm.filterFilm(f, actor, director, lgdto, str, year,em))
		res.add(FilmDtoManager.getDto(f));
	}
	return res;
    }*/

}
