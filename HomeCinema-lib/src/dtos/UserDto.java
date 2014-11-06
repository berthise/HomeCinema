/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import enums.UserStates;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author toure
 */
public class UserDto implements Serializable {
    public Long id;
    public String nickName;
    public String firstName;
    public String lastName;
    public String password;
    public String email;
    public Date birthDate;
    public Date addDate;
    public UserStates state;
}
