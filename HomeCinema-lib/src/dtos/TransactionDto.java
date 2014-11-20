/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

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
public class TransactionDto implements Serializable {

    public List<ProductDto> getProducts() {
	return products;
    }

    public void setProducts(List<ProductDto> products) {
	this.products = products;
    }

    public Long id;
    public java.util.Date addDate;
    public TransactionStates state;
    public Long bankTransNum;
    public Long user;
    public Double totalPrice;

    public List<ProductDto> products;

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

    public String getAddDateString(String formater) {
	SimpleDateFormat df = new SimpleDateFormat(formater);
	return df.format(this.addDate);
    }

    public void setAddDateString(String s, String formater) {
	SimpleDateFormat df = new SimpleDateFormat(formater);
	try {
	    this.addDate = df.parse(s);
	} catch (ParseException ex) {
	    this.addDate = new Date();
	    Logger.getLogger(FilmDto.class.getName()).log(Level.SEVERE, null, ex);
	}
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
