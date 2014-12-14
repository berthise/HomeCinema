/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import dtos.VideoDto;
import entities.Video;
import javax.persistence.EntityManager;
import managers.dtos.VideoDtoManager;

/**
 *
 * @author titou
 */
public class ManageEntitieVideo {
    
        public static Video createVideo(VideoDto vdto,EntityManager em) {
        Video v = VideoDtoManager.makeVideo(vdto);
        em.persist(v);
        return v;
    }
}
