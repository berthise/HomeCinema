/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import enums.Langs;
import enums.VideoFormat;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author titou
 */
@Entity
@Table(name = "VIDEOS")
public class Video implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_VIDEO")
  private Long id;

  @Column(name = "RESOLUTION")
  private Integer resolution;
  
  @Column(name = "LANG")
  private Langs audio;
  
  @Column(name = "FORMAT")
  private VideoFormat format;

  @Size(max = 255)
  @Column(name = "URL")
  private String url;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getResolution() {
    return resolution;
  }

  public void setResolution(Integer resolution) {
    this.resolution = resolution;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
  

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Video)) {
      return false;
    }
    Video other = (Video) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Video{" + "id=" + id + ", resolution=" + resolution + ", audio=" + audio + ", format=" + format + ", url=" + url + '}';
  }


}
