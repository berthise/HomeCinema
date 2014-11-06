/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.VideoDto;
import entities.Video;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class VideoDtoManager {

    public static VideoDto getDto(Video v) {
        VideoDto vdto = new VideoDto();
        vdto.id = v.getId();
        vdto.resolution = v.getResolution();
        vdto.url = v.getUrl();
        return vdto;
    }

    public static Video makeVideo(VideoDto vdto) {
        Video v = new Video();
        v.setId(vdto.id);
        v.setResolution(vdto.resolution);
        v.setUrl(vdto.url);
        return v;
    }

    public static Video mergeOrSave(VideoDto vdto, EntityManager em) {
        Video v = em.find(Video.class, vdto.id);
        if (v == null) {
            v = makeVideo(vdto);
            em.persist(v);
        } else {
            v.setResolution(vdto.resolution);
            v.setUrl(vdto.url);
        }
        return v;
    }
}
