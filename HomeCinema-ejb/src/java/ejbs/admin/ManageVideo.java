/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.admin;

import dtos.VideoDto;
import ejbs.ManageVideoRemote;
import entities.Video;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import managers.dtos.VideoDtoManager;

/**
 *
 * @author titou
 */
@Stateless
public class ManageVideo implements ManageVideoRemote {

    
    @PersistenceContext
    public EntityManager em;

    @Override
    public Long createVideo(VideoDto vdto)
    {
        Video v = VideoDtoManager.makeVideo(vdto);
        em.persist(v);
        return v.getId();
    }

}