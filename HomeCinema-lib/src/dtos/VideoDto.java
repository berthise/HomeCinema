/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.Langs;
import enums.VideoFormat;
import java.io.Serializable;

/**
 *
 * @author titou
 */
public class VideoDto implements Serializable {

  public Long id;
  public String url;
  public Integer resolution;
  public Langs audio;
  public VideoFormat format;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getResolution() {
    return resolution;
  }

  public void setResolution(Integer resolution) {
    this.resolution = resolution;
  }

  public Langs getAudio() {
    return audio;
  }

  public void setAudio(Langs audio) {
    this.audio = audio;
  }

  public VideoFormat getFormat() {
    return format;
  }

  public void setFormat(VideoFormat format) {
    this.format = format;
  }
  
  public String getType() {
    return format.getStr();
  }

  @Override
  public String toString() {
    return "VideoDto{" + "id=" + id + ", url=" + url + ", resolution=" + resolution + ", audio=" + audio + ", format=" + format + '}';
  }
  
  
  
  
}
