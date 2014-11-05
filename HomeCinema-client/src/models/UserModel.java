/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dtos.UserDto;

/**
 *
 * @author seb
 */
public class UserModel {

  UserDto userDto = null;

  public UserModel() {
    userDto = new UserDto();

  }
  
  public UserModel(UserDto userDto) {
    this.userDto = userDto;

  }

  public String getFirstname() {
    return userDto.firstname;
  }

  public void setFirstname(String firstname) {
    userDto.firstname = firstname;
  }

  public String getLastname() {
    return userDto.lastname;
  }

  public void setLastname(String lastname) {
    userDto.lastname = lastname;
  }

  public String getEmail() {
    return userDto.email;
  }

  public void setEmail(String email) {
    userDto.email = email;
  }

}
