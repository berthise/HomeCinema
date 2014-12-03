/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.PersonDto;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManagePersonRemote {

    public PersonDto getPerson(String n);
    public PersonDto getPerson(Long id);
    
}
