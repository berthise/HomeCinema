/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.ProductDto;
import dtos.VideoDto;
import ejbs.ManageVideoRemote;
import entities.Product;
import entities.Video;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managers.dtos.ProductDtoManager;
import managers.dtos.VideoDtoManager;
import managers.entities.ManageEntitieVideo;

/**
 *
 * @author titou
 */
@Stateless
public class ManageVideo implements ManageVideoRemote {

    @PersistenceContext
    public EntityManager em;

    public List<VideoDto> getAllVideo() {
        Query q = em.createQuery("From Video v",Video.class);
        List<Video> lv = q.getResultList();
        List<VideoDto> lvdto = new ArrayList<VideoDto>();
        for (Video v : lv) {
            lvdto.add(VideoDtoManager.getDto(v));
        }
        return lvdto;
    }

    @Override
    public Long createVideo(VideoDto vdto) {
        return ManageEntitieVideo.createVideo(vdto, em).getId();
    }

}
