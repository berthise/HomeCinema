/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.UsersFilmsStates;
import java.io.Serializable;

/**
 *
 * @author toure
 */
public class UsersFilmsDto implements Serializable {
    public Long id;
    public Integer currentPosition;
    public UsersFilmsStates state;
    public Long film;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public UsersFilmsStates getState() {
        return state;
    }

    public void setState(UsersFilmsStates state) {
        this.state = state;
    }

    public Long getFilm() {
        return film;
    }

    public void setFilm(Long film) {
        this.film = film;
    }

}
