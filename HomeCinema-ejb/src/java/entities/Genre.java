/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Lang;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author seb
 */
@Entity
@Table(name = "GENRES")
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_GENRE")
    private Long id;

    @Size(max = 255)
    
    @ElementCollection(fetch=EAGER)
    @Column(name = "NAME")
     @MapKeyColumn(name="locale")
  @CollectionTable(name = "GENRE_NAME", joinColumns = @JoinColumn(name = "genre_id"))
    private Map<Lang, String> name;

    public Genre() {
	name = new HashMap<>();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName(Lang lang) {
	if (this.name.containsKey(lang)) {
	    return name.get(lang);
	} else {
	    return name.get(Lang.EN);
	}
    }

    public void setName(String name, Lang lang) {
	this.name.put(lang, name);
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
	if (!(object instanceof Genre)) {
	    return false;
	}
	Genre other = (Genre) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Genre{" + "id=" + id + ", name=" + name + '}';
    }

}
