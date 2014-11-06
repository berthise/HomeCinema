/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import dtos.VideoDto;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManageVideoRemote {
 
    public void createVideo(VideoDto vdto);

}
