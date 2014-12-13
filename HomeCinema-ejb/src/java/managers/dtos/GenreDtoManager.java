/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.GenreDto;
import entities.Genre;
import enums.Lang;

/**
 *
 * @author titou
 */
public class GenreDtoManager {
    public static Genre makeGenre(GenreDto gdto,Lang lang)
    {
        Genre g = new Genre();
        g.setName(gdto.name,lang);
        g.setId(gdto.id);
        return g;
    }
    public static GenreDto getDto(Genre g,Lang lang)
    {
        GenreDto gdto = new GenreDto();
        gdto.id=g.getId();
        gdto.name=g.getName(lang);
        return gdto;
    }
}
