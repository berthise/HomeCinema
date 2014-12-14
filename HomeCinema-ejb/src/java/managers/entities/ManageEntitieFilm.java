/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.FilmDto;
import dtos.VideoDto;
import entities.Film;
import entities.Genre;
import entities.Person;
import enums.Lang;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import managers.dtos.FilmDtoManager;

/**
 *
 * @author titou
 */
public class ManageEntitieFilm {

    public static Film createFilmWithVideo(FilmDto fdto, VideoDto trailer, VideoDto vid,Lang lang, EntityManager em) {
	Film f = FilmDtoManager.makeFilm(fdto,lang);
	f.addVideoFile(ManageEntitieVideo.createVideo(vid, em));
	f.setTrailler(ManageEntitieVideo.createVideo(trailer, em));
	em.persist(f);
	return f;
    }

    public static Film createFilm(FilmDto fdto,Lang lang, EntityManager em) {
	Film f = FilmDtoManager.makeFilm(fdto,lang);
	em.persist(f);
	return f;
    }

    public static boolean hasActor(Film f, Long actor, EntityManager em) {
	Person a = em.find(Person.class, actor);
	return f.getActors().contains(a);
    }

    public static boolean hasDirector(Film f, Long actor, EntityManager em) {
	Person a = em.find(Person.class, actor);
	return f.getDirectors().contains(a);
    }

    public static boolean hasGenre(Film f, List<Long> lg, EntityManager em) {
	boolean ok = false;
	for (Long g : lg) {
	    Genre a = em.find(Genre.class, lg);
	    ok = (ok || f.getGenre().contains(a));
	}
	return ok;
    }
/*
    //TODO optimiser
    public static boolean filterFilm(Film f, Long actor, Long director, List<Long> lgdto, String str, String year, EntityManager em) {

	
	if (actor == null || actor == 0 || ManageEntitieFilm.hasActor(f, actor, em)) {
	    if (director == null || director == 0 || ManageEntitieFilm.hasDirector(f, director, em)) {
		if (lgdto == null || lgdto.isEmpty() || ManageEntitieFilm.hasGenre(f, lgdto, em)) {
		    if (str == null || "".equals(str) || f.getTitle().matches("(?i:.*"+str+".*)")) {
			if (year == null || "".equals(year) || new SimpleDateFormat("yyyy").format(f.getReleaseDate()).equals(year)) {
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }*/
}
