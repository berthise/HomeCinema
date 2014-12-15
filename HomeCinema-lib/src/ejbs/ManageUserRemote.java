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
import enums.Lang;
import exception.ActivatedCodeException;
import exception.RetrieveCodeException;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import exception.UncorrectPasswordException;
import exception.UnknownAccountException;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author toure
 */
@Remote
public interface ManageUserRemote {

    public UserDto signUp(UserDto user) throws SignupEmailException, SignupNickNameException;
    
    public void save(UserDtoNoPw user);
    
    public UserDto login(String email, String password)  throws UncorrectPasswordException;
    
    public Set<SimpleUserDto> getAllUser();
    
    public UserDtoNoPw getUser(Long id);
    
    public void removeUser (Long id);
    
    public void mergeOrSave (UserDtoNoPw udto);
    

    
    //public  List<TransactionDto> getTransaction (Long user);

    public boolean changePassword(Long id, String oldPassword, String newPassword);
    
    public boolean changeEmail(Long id, String email, String newPassword);

    public void activate(Long user, String code) throws ActivatedCodeException;
    
    public void activate(Long user);

    public void deactivate(Long user);
    
    public String retrievePassword(String email) throws UnknownAccountException;
    
    public void changePasswordRetrieve(String code, String newPassword) throws RetrieveCodeException;

    public List<FilmDto> getFilms(Long id, Lang lang);
    
    public Set<Long> getMyProductId(Long id);
    
    public Integer countFilms(Long id);

    public List<TransactionDto> getTransaction(Long user, Lang lang);

    public int getNbFilms(Long id);

    public List<FilmDto> getFilmsPage(Long id, Lang lang, int row, int limit);
}
