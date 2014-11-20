/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author seb
 */
  public enum CardTypes {
    CB("Carte Bancaire"),
    VISA("Visa");
    
    String label;
    CardTypes(String l) {
      label = l;
    }
    
    public String getLabel() {
      return label;
    }
  }