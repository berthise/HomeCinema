/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import dtos.SimpleUserDto;
import dtos.TransactionDto;
import dtos.UserDto;
import dtos.UserDtoNoPw;
import java.util.List;
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
    
    public Set<SimpleUserDto> getAllUser(boolean rem);
    
    public UserDtoNoPw getUser(Long id);
    
    public void removeUser (Long id);
    
    public void mergeOrSave (UserDtoNoPw udto);
    
    public List<FilmDto> getFilms(Long id);
    
      public  Set<TransactionDto> getTransaction (Long user);
}
