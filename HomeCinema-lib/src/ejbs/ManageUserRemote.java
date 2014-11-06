/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.UserDto;
import javax.ejb.Remote;

/**
 *
 * @author toure
 */
@Remote
public interface ManageUserRemote {

    public void signUp(UserDto user);
    
}
