/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.UserDto;
import entities.User;

/**
 *
 * @author toure
 */
public class UserDtoManager {

    public static User createUser(UserDto udto) {
        User u = new User();
        u.setId(udto.id);
        u.setEmail(udto.email);
        u.setNickName(udto.nickName);
        u.setPassword(udto.password);
        u.setFirstName(udto.firstName);
        u.setLastName(udto.lastName);
        u.setBirthDate(udto.birthDate);
        return u;
    }
}
