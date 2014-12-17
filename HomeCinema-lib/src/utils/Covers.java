/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author seb
 */
public class Covers {
  
  private final static String URL_W396_COVER = "http://image.tmdb.org/t/p/w396/";
  private final static String URL_W300_COVER = "http://image.tmdb.org/t/p/w300/";
  private final static String URL_W92_COVER = "http://image.tmdb.org/t/p/w92/";
  
  public enum CoversSizes {
    SMALL,
    MEDIUM,
    MEDIUM2,
    BIG;
  }
  
  public static String getURL(String cover, CoversSizes size) {
    switch (size) {
      case SMALL:
	return URL_W92_COVER + cover;
      case MEDIUM: 
	return URL_W300_COVER + cover;
      case MEDIUM2: 
	return URL_W396_COVER + cover;
      default:
	return URL_W300_COVER + cover;
    }
  }


  
}
