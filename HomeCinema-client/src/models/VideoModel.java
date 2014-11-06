/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dtos.ProductDto;
import dtos.UserDto;
import dtos.VideoDto;

/**
 *
 * @author seb
 */
public class VideoModel implements Model_if {

  private VideoDto vDto;

  public VideoModel() {
    vDto = new VideoDto();

  }

  public VideoModel(VideoDto videoDto) {
    this.vDto = videoDto;

  }

  public Long getId() {
    return vDto.id;
  }

  public void setId(Long id) {
    vDto.id = id;
  }

  public String getUrl() {
    return vDto.url;
  }

  public void setUrl(String url) {
    vDto.url = url;
  }

  public Integer getResolution() {
    return vDto.resolution;
  }

  public void setResolution(Integer resolution) {
    vDto.resolution = resolution;
  }

  @Override
  public void displayConsole() {
    System.out.println("Video   Id: " + this.getId());
    System.out.println("        Url: " + this.getUrl());
    System.out.println("        Resolution: " + this.getResolution());
  }

  public void initDto() {
    this.vDto = new VideoDto();
    vDto.id = new Long(0);
    vDto.resolution = 240;
    vDto.url = "";
  }

  public VideoDto getvDto() {
    return vDto;
  }

  public void setvDto(VideoDto vDto) {
    this.vDto = vDto;
  }

  public Boolean notNull() {
    return this.vDto != null;
  }

}
