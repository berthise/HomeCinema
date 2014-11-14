/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author narcisse
 */
public class ListFilmDto {
    public ArrayList<FilmDto> listFilmDto = new ArrayList<>();
    public ArrayList<FilmDto> getAllFilms( List<Object> listfilms){
        int i=0;
    /* while (i < listfilms.size()) {
            FilmDto fdto = new FilmDto();
            Film f = listfilms.get(i);
            fdto.title = f.getTitle() + listfilms.size();
            fdto.cover = f.getCoverId();
            listFilmDto.add(fdto);
            i++;
        }*/
     return listFilmDto;
    }
}
