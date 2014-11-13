/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.GenreDto;
import dtos.PersonDto;
import dtos.VideoDto;
import exception.DuplicateKey;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManageFilmRemote {

    public Long createFilm(FilmDto fdto);

    public List<FilmDto> getAllFilm();

    public FilmFicheDto getDtoFromId(Long id);

    public void setTrailer(Long fid, VideoDto trailer);

    public void setExistingTrailer(Long fid, Long trailer);

    public void addVideos(Long fid, List<VideoDto> lvdto);

    public void addVideo(Long fid, VideoDto vdto);

    public void addExistingVideos(Long fid, List<Long> lid);

    public void addExistingVideo(Long fid, Long vid);

    public void addGenres(Long fid, Set<GenreDto> lgdto);

    public void addGenre(Long fid, GenreDto gdto);

    public void addActors(Long fid, List<PersonDto> lgdto);

    public void addActor(Long fid, PersonDto gdto);

    public void addDirectors(Long fid, List<PersonDto> lgdto);

    public void addDirector(Long fid, PersonDto gdto);
}
