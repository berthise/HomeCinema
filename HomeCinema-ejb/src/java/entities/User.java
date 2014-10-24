/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.UserStates;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author titou
 */
@Entity
@Table(name = "USER_")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Size(max = 255)
  @Column(name = "NICK_NAME")
  private String nickName;

  @Size(max = 255)
  @Column(name = "FIRST_NAME")
  private String firstName;

  @Size(max = 255)
  @Column(name = "LAST_NAME")
  private String lastName;

  @Size(max = 255)
  @Column(name = "PASSWORD")
  private String password;

  @Size(max = 255)
  @Column(name = "EMAIL")
  private String email;

  @Temporal(TemporalType.DATE)
  @Column(name = "BIRTH_DATE")
  private java.util.Date birthDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "ADD_DATE")
  private java.util.Date addDate;

  @Column(name = "STATE_")
  private UserStates state;

  @OneToMany
  @JoinColumn(name = "CADDY")
  private List<Product> caddy;

  @OneToMany
  @JoinColumn(name = "FILMS")
  private List<UsersFilms> films;

  @OneToMany(mappedBy = "user")
  @JoinColumn(name = "TRANSACTIONS")
  private List<Transaction> transactions;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public UserStates getState() {
    return state;
  }

  public void setState(UserStates state) {
    this.state = state;
  }

  public List<Product> getCaddy() {
    return caddy;
  }

  public void setCaddy(List<Product> caddy) {
    this.caddy = caddy;
  }

  public List<UsersFilms> getFilms() {
    return films;
  }

  public void setFilms(List<UsersFilms> films) {
    this.films = films;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", nickName=" + nickName + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", birthDate=" + birthDate + ", addDate=" + addDate + ", state=" + state + ", caddy=" + caddy + ", films=" + films + ", transactions=" + transactions + '}';
  }

}
