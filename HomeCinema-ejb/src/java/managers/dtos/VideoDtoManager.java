/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.VideoDto;
import entities.Video;

/**
 *
 * @author titou
 */
public class VideoDtoManager {
    public static VideoDto getDto (Video v)
    {
        VideoDto vdto = new VideoDto();
        vdto.resolution=v.getResolution();
        vdto.url=v.getUrl();
        return vdto;
    }
    
    public static Video makeVideo (VideoDto vdto)
    {
        Video v = new Video();
        v.setResolution(vdto.resolution);
        v.setUrl(vdto.url);
        return v;
    }
}
