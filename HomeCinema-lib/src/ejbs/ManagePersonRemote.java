/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.PersonDto;

/**
 *
 * @author titou
 */
public interface ManagePersonRemote {
 public PersonDto getPerson(String n);   
}
