/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.UsersFilmsStates;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author seb
 */
@Entity
@Table(name = "USERS_FILMS")
public class UsersFilms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "FILM")
    @OneToOne
    private Film film;

    @Column(name = "STATE_")
    private UsersFilmsStates state;

    @Column(name = "CURRENT_POSITION")
    private Integer currentPsitiono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public UsersFilmsStates getState() {
        return state;
    }

    public void setState(UsersFilmsStates state) {
        this.state = state;
    }

    public Integer getCurrentPsitiono() {
        return currentPsitiono;
    }

    public void setCurrentPsitiono(Integer currentPsitiono) {
        this.currentPsitiono = currentPsitiono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersFilms)) {
            return false;
        }
        UsersFilms other = (UsersFilms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsersFilms{" + "id=" + id + ", film=" + film + ", state=" + state + ", currentPsitiono=" + currentPsitiono + '}';
    }
}
