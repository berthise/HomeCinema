/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import entities.Film;
import entities.Video;
import enums.Lang;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class FilmDtoManager {

    public static FilmDto getDto(Film f,Lang lang) {
	if (f == null) {
	    return null;
	}

	FilmDto fdto = new FilmDto();
	fdto.id = f.getId();
	fdto.title = f.getTitle(lang);
	fdto.overview = f.getOverview(lang);
	fdto.release_date = new Date(f.getReleaseDate().getTime());
	fdto.cover = f.getCoverId();
	fdto.rating = f.getRating();
	fdto.runtime = f.getRuntime();
	if (f.getMain_product() != null) {
	    fdto.main_product_id = f.getMain_product().getId();
	}
	return fdto;
    }

    public static FilmFicheDto getDtoForFiche(Film f,Lang lang) {
	if (f == null) {
	    return null;
	}

	FilmFicheDto fdto = new FilmFicheDto();
	fdto.id = f.getId();
	fdto.title = f.getTitle(lang);
	fdto.overview = f.getOverview(lang);
	fdto.release_date = new Date(f.getReleaseDate().getTime());
	fdto.cover = f.getCoverId();
	fdto.rating = f.getRating();
	fdto.runtime = f.getRuntime();
	fdto.trailler = VideoDtoManager.getDto(f.getTrailler());
	fdto.files = new ArrayList<>();
	for (Video v : f.getVideoFile()) {
	    fdto.files.add(VideoDtoManager.getDto(v));
	}
	if (f.getMain_product() != null) {
	    fdto.main_product_id = f.getMain_product().getId();
	}

	return fdto;
    }

    public static Film makeFilm(FilmDto fdto,Lang lang) {
	Film f = new Film();
	f.setId(fdto.id);
	f.setTitle(lang,fdto.title);
	f.setOverview(lang,fdto.overview);
	f.setReleaseDate(fdto.release_date);
	f.setCoverId(fdto.cover);
	f.setRating(fdto.rating);
	f.setRuntime(fdto.runtime);

	return f;
    }

    public static Film mergeOrSave(FilmDto fdto,Lang lang, EntityManager em) {
	Film f = em.find(Film.class, fdto.id);
	if (f != null) {
	    f.setTitle(lang,fdto.title);
	    f.setOverview(lang,fdto.overview);
	    f.setReleaseDate(fdto.release_date);
	    f.setCoverId(fdto.cover);
	    f.setRating(fdto.rating);
	    f.setRuntime(fdto.runtime);
	    em.merge(f);
	} else {
	    f = makeFilm(fdto,lang);
	    em.persist(f);
	}
	return f;
    }

}
