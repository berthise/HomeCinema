/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.UserStates;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toure
 */
public class UserDtoNoPw extends SimpleUserDto implements Serializable {

  public String firstName;
  public String lastName;

  public Date birthDate;
  public Date addDate;

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

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getBirthDateString(String formater) {
    SimpleDateFormat df = new SimpleDateFormat(formater);
    return df.format(this.birthDate);
  }

  public void setBirthDateString(String s, String formater) {
    SimpleDateFormat df = new SimpleDateFormat(formater);
    try {
      this.birthDate = df.parse(s);
    } catch (ParseException ex) {
      this.birthDate = new Date();
      Logger.getLogger(UserDtoNoPw.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

}
