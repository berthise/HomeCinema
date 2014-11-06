/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dtos.ProductDto;
import dtos.UserDto;

/**
 *
 * @author seb
 */
public class UserModel implements Model_if {

  UserDto uDto = null;

  public UserModel() {
    uDto = new UserDto();

  }

  public UserModel(UserDto userDto) {
    this.uDto = userDto;

  }

  public Long getId() {
    return uDto.id;
  }

  public void setId(Long id) {
    uDto.id = id;
  }

  public String getFirstname() {
    return uDto.firstname;
  }

  public void setFirstname(String firstname) {
    uDto.firstname = firstname;
  }

  public String getLastname() {
    return uDto.lastname;
  }

  public void setLastname(String lastname) {
    uDto.lastname = lastname;
  }

  public String getEmail() {
    return uDto.email;
  }

  public void setEmail(String email) {
    uDto.email = email;
  }

  @Override
  public void displayConsole() {
    System.out.println("User   Id: " + this.getId());
    System.out.println("       Firstname: " + this.getFirstname());
    System.out.println("       Lastname: " + this.getLastname());
    System.out.println("       Email: " + this.getEmail());

  }

  public void initDto() {
    this.uDto = new UserDto();
    uDto.id = new Long(0);
    uDto.firstname = "";
    uDto.lastname = "";
    uDto.email = "";
  }

  public UserDto getuDto() {
    return uDto;
  }

  public void setuDto(UserDto uDto) {
    this.uDto = uDto;
  }
  
  

  public Boolean notNull() {
    return this.uDto != null;
  }
}
