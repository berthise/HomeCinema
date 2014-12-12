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
public enum VideoFormat {
    MP4("mp4"),
    WEBM("webm"),
    OGG("ogg");

  private String str;
  VideoFormat(String s) { str = s; }

  public String getStr() {
    return str;
  }

    

}
