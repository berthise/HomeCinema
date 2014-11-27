/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.UsersFilmsDto;
import javax.ejb.Remote;

/**
 *
 * @author toure
 */
@Remote
public interface ManageUsersFilmsRemote {

    public void updateCurrentTime(UsersFilmsDto usfdto);

    public UsersFilmsDto getCurrentTime(Long user, Long film);
}
