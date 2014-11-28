/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.UsersFilmsDto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class UsersFilmsManagedBean {
    
    UsersFilmsDto ufdto;
    
    public UsersFilmsManagedBean() {
        ufdto = new UsersFilmsDto();
    }
    
    public String updateCurrentTime(Long user, Long film) {
        ufdto.user = user;
        ufdto.film = film;
        return ""+ufdto.user+ufdto.film+ufdto.currentPosition;
    }

    public UsersFilmsDto getUfdto() {
        return ufdto;
    }

    public void setUfdto(UsersFilmsDto ufdto) {
        this.ufdto = ufdto;
    }    
}
