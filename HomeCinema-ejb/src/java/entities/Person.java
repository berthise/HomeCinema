/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author seb
 */
@Entity
public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Size(max = 255)
  @Column(name = "FIRST_NAME")
  private String firstName;
  
  @Size(max = 255)
  @Column(name = "LAST_NAME")
  private String lastName;
  
  @ManyToMany(mappedBy = "actors")
  @Column(name = "IS_ACTOR_OF")
  private List<Film> is_actor_of;
  
  @ManyToMany(mappedBy = "directors")
  @Column(name = "IS_DIRECTOR_OF")
  private List<Film> is_director_of;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Film> getIs_actor_of() {
    return is_actor_of;
  }

  public void setIs_actor_of(List<Film> is_actor_of) {
    this.is_actor_of = is_actor_of;
  }

  public List<Film> getIs_director_of() {
    return is_director_of;
  }

  public void setIs_director_of(List<Film> is_director_of) {
    this.is_director_of = is_director_of;
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
    return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", is_actor_of=" + is_actor_of + ", is_director_of=" + is_director_of + '}';
  }


}
