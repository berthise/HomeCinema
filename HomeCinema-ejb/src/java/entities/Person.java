/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author seb
 */
@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_PERSON")
  private Long id;

  @Size(max = 255)
  @Column(name = "NAME")
  private String name;
  
@JoinTable(name="ACTORS")
  @ManyToMany(mappedBy = "actors",fetch=FetchType.LAZY)
  @JoinColumn(name = "IS_ACTOR_OF")
  private Set<Film> is_actor_of;

  @JoinTable(name="DIRECTORS")
  @ManyToMany(mappedBy = "directors" ,fetch=FetchType.LAZY)
  @JoinColumn(name = "IS_DIRECTOR_OF")
  private Set<Film> is_director_of;

  
  public Person()
  {
      this.is_actor_of=new HashSet<>();
      this.is_director_of=new HashSet<>();
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Set<Film> getIs_actor_of() {
    return is_actor_of;
  }

  public void setIs_actor_of(Set<Film> is_actor_of) {
    this.is_actor_of = is_actor_of;
  }

  public void addIs_actor_of(Film f)
  {
      this.is_actor_of.add(f);
  }
  
  public Set<Film> getIs_director_of() {
    return is_director_of;
  }

  public void setIs_director_of(Set<Film> is_director_of) {
    this.is_director_of = is_director_of;
  }
  
    public void addIs_director_of(Film f)
  {
      this.is_director_of.add(f);
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
    if (!(object instanceof Person)) {
      return false;
    }
    Person other = (Person) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", Name=" + name+ ", is_actor_of=" + is_actor_of + ", is_director_of=" + is_director_of + '}';
  }


}
