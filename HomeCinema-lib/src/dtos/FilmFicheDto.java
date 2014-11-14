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
  public List<VideoDto> files;
  public Long main_product_id;

  public VideoDto getTrailler() {
    return trailler;
  }

  public void setTrailler(VideoDto trailler) {
    this.trailler = trailler;
  }

  public List<VideoDto> getFiles() {
    return files;
  }

  public void setFiles(List<VideoDto> files) {
    this.files = files;
  }

  public Long getMain_product_id() {
    return main_product_id;
  }

  public void setMain_product_id(Long main_product_id) {
    this.main_product_id = main_product_id;
  }

}
