/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.CardTypes;
import enums.TransactionStates;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author titou
 */
public class PaymentDto implements Serializable {

  public String numCarte;
  public String cryptogramme;
  public Integer month;
  public Integer year;
  public CardTypes type;

  public String getNumCarte() {
    return numCarte;
  }

  public void setNumCarte(String numCarte) {
    this.numCarte = numCarte;
  }

  public String getCryptogramme() {
    return cryptogramme;
  }

  public void setCryptogramme(String cryptogramme) {
    this.cryptogramme = cryptogramme;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public CardTypes getType() {
    return type;
  }

  public void setType(CardTypes type) {
    this.type = type;
  }

 
}
