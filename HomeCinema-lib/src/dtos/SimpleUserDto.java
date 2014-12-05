/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.UserStates;
import java.io.Serializable;

/**
 *
 * @author toure
 */
public class SimpleUserDto implements Serializable {

  public Long id;
  public String nickName;
  public String email;
  public UserStates state;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserStates getState() {
    return state;
  }

  public void setState(UserStates state) {
    this.state = state;
  }

}
