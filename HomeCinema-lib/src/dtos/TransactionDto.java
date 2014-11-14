/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.TransactionStates;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author titou
 */
public class TransactionDto implements Serializable {

  public Long id;
  public java.util.Date addDate;
  public TransactionStates state;
  public Long bankTransNum;
  public Long user;
  public Double totalPrice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public TransactionStates getState() {
    return state;
  }

  public void setState(TransactionStates state) {
    this.state = state;
  }

  public Long getBankTransNum() {
    return bankTransNum;
  }

  public void setBankTransNum(Long bankTransNum) {
    this.bankTransNum = bankTransNum;
  }

  public Long getUser() {
    return user;
  }

  public void setUser(Long user) {
    this.user = user;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
