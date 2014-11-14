/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.TransactionStates;
import java.io.Serializable;
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
    
}
