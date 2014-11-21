/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.SimpleUserDto;
import dtos.UserDto;
import dtos.UserDtoNoPw;
import entities.User;
import enums.UserStates;
import java.util.Date;
import javax.persistence.EntityManager;

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
	u.setAddDate(udto.addDate);
	u.setState(udto.state);
	return u;
    }

    public static User createUser(UserDtoNoPw udto) {
        User u = new User();
        u.setId(udto.id);
        u.setEmail(udto.email);
        u.setNickName(udto.nickName);
        u.setFirstName(udto.firstName);
        u.setLastName(udto.lastName);
        u.setBirthDate(udto.birthDate);
        u.setAddDate(new Date());
        u.setState(UserStates.Pending);
        return u;
    }

    public static SimpleUserDto getSimpleDto(User u) {
	SimpleUserDto udto = new SimpleUserDto();
	udto.id = u.getId();
	udto.email = u.getEmail();
	udto.nickName = u.getNickName();
	udto.state = u.getState();
	return udto;
    }

    public static UserDtoNoPw getUserNoPw(User u) {
	UserDtoNoPw udto = new UserDtoNoPw();
	udto.id = u.getId();
	udto.email = u.getEmail();
	udto.nickName = u.getNickName();
	udto.firstName = u.getFirstName();
	udto.lastName = u.getLastName();
	udto.birthDate = u.getBirthDate();
	udto.addDate = u.getAddDate();
	udto.state = u.getState();
	if (u.getCaddy() != null) {
	    udto.caddieSize = u.getCaddy().getProducts().size();
	} else {
	    udto.caddieSize = 0;
	}
	return udto;
    }

    public static UserDto getUser(User u) {
	UserDto udto = new UserDto();
	udto.id = u.getId();
	udto.email = u.getEmail();
	udto.nickName = u.getNickName();
	udto.password = u.getPassword();
	udto.firstName = u.getFirstName();
	udto.lastName = u.getLastName();
	udto.birthDate = u.getBirthDate();
	udto.addDate = u.getAddDate();
	udto.state = u.getState();
	if (u.getCaddy() != null) {
	    udto.caddieSize = u.getCaddy().getProducts().size();
	} else {
	    udto.caddieSize = 0;
	}
	return udto;
    }

    public static User mergeOrSave(UserDtoNoPw udto, EntityManager em) {
	User u = em.find(User.class, udto.id);
	if (u == null) {
	    u = createUser(udto);
	    em.persist(u);
	} else {
	    if (udto.email != null) {
		u.setEmail(udto.email);
	    }
	    if (udto.nickName != null) {
		u.setNickName(udto.nickName);
	    }
	    if (udto.firstName != null) {
		u.setFirstName(udto.firstName);
	    }
	    if (udto.lastName != null) {
		u.setLastName(udto.lastName);
	    }
	    if (udto.birthDate != null) {
		u.setBirthDate(udto.birthDate);
	    }
	    if (udto.addDate != null) {
		u.setAddDate(udto.addDate);
	    }
	    if (udto.state != null) {
		u.setState(udto.state);
	    }
	    em.merge(u);
	}
	return u;
    }
}
