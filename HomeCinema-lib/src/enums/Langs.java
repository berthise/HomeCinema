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
public enum Langs {
  EN("en"),
  FR("fr");
  
    private String str;
  Langs(String s) { str = s; }

  public String getStr() {
    return str;
  }
}
