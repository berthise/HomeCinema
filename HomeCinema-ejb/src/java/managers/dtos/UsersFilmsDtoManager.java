/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.UsersFilmsDto;
import entities.UsersFilms;

/**
 *
 * @author toure
 */
public class UsersFilmsDtoManager {
    public static UsersFilmsDto getDto(UsersFilms uf) {
        UsersFilmsDto ufdto = new UsersFilmsDto();
        ufdto.id = uf.getId();
        ufdto.currentPosition = uf.getCurrentPosition();
        ufdto.film = uf.getFilm().getId();
        ufdto.state = uf.getState();
        return ufdto;
    }
}
