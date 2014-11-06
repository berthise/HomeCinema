/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.VideoDto;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManageFilmRemote {

    public Long createFilm(FilmDto fdto);

    public FilmFicheDto getDtoFromId(Long id);

    public void setTrailer(Long fid, VideoDto trailer);

    public void setExistingTrailer(Long fid, Long trailer);
    
    public void addVideosToFilm(Long fid, List<VideoDto> lvdto);

    public void addVideoToFilm(Long fid, VideoDto vdto);

    public void addExistingVideosToFilm(Long fid, List<Long> lid);

    public void addExistingVideoToFilm(Long fid, Long vid);
}
