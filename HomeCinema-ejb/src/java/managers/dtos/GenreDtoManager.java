/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.GenreDto;
import entities.Genre;

/**
 *
 * @author titou
 */
public class GenreDtoManager {
    public static Genre makeGenre(GenreDto gdto)
    {
        Genre g = new Genre();
        g.setName(gdto.name);
        g.setId(gdto.id);
        return g;
    }
    public static GenreDto getDto(Genre g)
    {
        GenreDto gdto = new GenreDto();
        gdto.id=g.getId();
        gdto.name=g.getName();
        return gdto;
    }
}
