/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.FilmDto;
import dtos.VideoDto;
import entities.Film;
import entities.Video;
import javax.persistence.EntityManager;
import managers.dtos.FilmDtoManager;

/**
 *
 * @author titou
 */
public class ManageEntitieFilm {
    
    
        public static Film createFilmWithVideo(FilmDto fdto, VideoDto trailer, VideoDto vid,EntityManager em) {
        Film f = FilmDtoManager.makeFilm(fdto);
        f.addVideoFile(ManageEntitieVideo.createVideo(vid,em));
        f.setTrailler(ManageEntitieVideo.createVideo(trailer,em));
        em.persist(f);
        return f;
    }
}
