/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author seb
 */
@Entity
public class UserActivation implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public UserActivation() {
    
  }

  public UserActivation(String code, User user) {
    this.code = code;
    this.user = user;
  }

  
  @Id
  @Size(max = 255)
  @Column(name = "CODE")
  private String code;

  @OneToOne
  @JoinColumn(name = "USER_")
  private User user;


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  

  @Override
  public int hashCode() {
    int hash = 7;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final UserActivation other = (UserActivation) obj;
    if (!Objects.equals(this.code, other.code)) {
      return false;
    }
    if (!Objects.equals(this.user, other.user)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "UserActivation{" + "code=" + code + ", user=" + user  + '}';
  }

  
  
}