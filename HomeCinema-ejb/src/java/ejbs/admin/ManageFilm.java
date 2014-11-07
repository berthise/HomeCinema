/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.GenreDto;
import dtos.VideoDto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.FilmDtoManager;
import ejbs.ManageFilmRemote;
import entities.Film;
import entities.Genre;
import entities.Video;
import exception.DuplicateKey;
import java.util.List;
import javax.persistence.Query;
import javax.ejb.EJB;
import managers.entities.ManageEntitieFilm;
import managers.entities.ManageEntitieGenre;
import managers.entities.ManageEntitieVideo;

/**
 *
 * @author titou
 */
@Stateless
public class ManageFilm implements ManageFilmRemote {

    @PersistenceContext
    public EntityManager em;

    @Override
    public Long createFilm(FilmDto fdto) {
        return ManageEntitieFilm.createFilm(fdto, em).getId();
    }

    @Override
    public FilmFicheDto getDtoFromId(Long id) {
        Film f = em.find(Film.class, id);
        return FilmDtoManager.getDtoForFiche(f);
    }

    @Override
    public void setTrailer(Long fid, VideoDto trailer) {
        Film f = em.find(Film.class, fid);
        f.setTrailler(ManageEntitieVideo.createVideo(trailer, em));
    }

    @Override
    public void addVideos(Long fid, List<VideoDto> lvdto) {
        for (VideoDto vdto : lvdto) {
            addVideo(fid, vdto);
        }
    }

    @Override
    public void addVideo(Long fid, VideoDto vdto) {
        Film f = em.find(Film.class, fid);
        f.addVideoFile(ManageEntitieVideo.createVideo(vdto, em));
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
    }

    @Override
    public void setExistingTrailer(Long fid, Long trailer) {
        Film f = em.find(Film.class, fid);
        Video v = em.find(Video.class, trailer);
        f.setTrailler(v);
    }

    public void addGenres(Long fid, List<GenreDto> lgdto) {
        for (GenreDto gdto : lgdto) {
            addGenre(fid, gdto);
        }
    }

    public void addGenre(Long fid, GenreDto gdto) {
        Genre g = ManageEntitieGenre.getGenre(gdto, em);
        Film f = em.find(Film.class, fid);
        f.addGenre(g);
    }
}
