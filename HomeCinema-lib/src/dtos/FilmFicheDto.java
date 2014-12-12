/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.List;

/**
 *
 * @author titou
 */
public class FilmFicheDto extends FilmDto {

  public VideoDto trailler;
  public List<VideoDto> videos;

  public VideoDto getTrailler() {
    return trailler;
  }

  public void setTrailler(VideoDto trailler) {
    this.trailler = trailler;
  }

  public List<VideoDto> getFiles() {
    return videos;
  }

  public void setFiles(List<VideoDto> files) {
    this.videos = files;
  }

}
