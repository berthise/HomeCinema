/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.SimpleUserDto;
import dtos.UserDto;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author toure
 */
@Remote
public interface ManageUserRemote {

    public void signUp(UserDto user);
    
    public UserDto login(String email, String password);
    
    public Set<SimpleUserDto> getAllUser();
    
    public UserDto getUser(Long id);
    
    public void removeUser (Long id);
}
