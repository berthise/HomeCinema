/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.FilmDto;
import entities.Film;
import java.util.Date;

/**
 *
 * @author titou
 */
public  class FilmDtoManager {
    
    public static FilmDto getDto(Film f)
    {
        if (f == null)
            return null;
        
        FilmDto fdto = new FilmDto();
        fdto.id = f.getId();
        fdto.title=f.getTitle();
        fdto.overview=f.getOverview();
        fdto.release_date=new Date(f.getReleaseDate().getTime());
        fdto.cover=f.getCoverId();
        fdto.rating=f.getRating();
        fdto.runtime=f.getRuntime();
        fdto.trailler=VideoDtoManager.getDto(f.getTrailler());
        return fdto;
    }
    
    public static Film makeFilm(FilmDto fdto)
    {
        Film f = new Film();
        f.setId(fdto.id);
        f.setTitle(fdto.title);
        f.setOverview(fdto.overview);
        f.setReleaseDate(fdto.release_date);
        f.setCoverId(fdto.cover);
        f.setRating(fdto.rating);
        f.setRuntime(fdto.runtime);
        f.setTrailler(VideoDtoManager.makeVideo(fdto.trailler));
        return f;
    }
        
}
