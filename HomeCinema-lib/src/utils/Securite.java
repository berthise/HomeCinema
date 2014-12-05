/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seb
 */
public class Securite {
  
  private static MessageDigest md;
  
  private static MessageDigest getMD() throws NoSuchAlgorithmException {
    if ( md == null) {
      md = MessageDigest.getInstance("SHA-256");
    }
    return md;
  }
  
  public static String crypte( String s ) {
    try {
      byte[] b = Securite.getMD().digest(s.getBytes());
      return new String(b);
    } catch (NoSuchAlgorithmException ex) {
      Logger.getLogger(Securite.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
  public static Boolean equale( String s, String e ) {
    try {
      return Securite.getMD().isEqual(s.getBytes(), e.getBytes());
    } catch (NoSuchAlgorithmException ex) {
      Logger.getLogger(Securite.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }
  
}
