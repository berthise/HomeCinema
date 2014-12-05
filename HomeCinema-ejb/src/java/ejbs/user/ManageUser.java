/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import dtos.FilmDto;
import dtos.SimpleUserDto;
import dtos.TransactionDto;
import dtos.UserDto;
import dtos.UserDtoNoPw;
import ejbs.ManageUserRemote;
import entities.Transaction;
import entities.User;
import entities.UserActivation;
import entities.UsersFilms;
import enums.UserStates;
import exception.ActivatedCodeException;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import managers.dtos.FilmDtoManager;
import managers.dtos.TransactionDtoManager;
import managers.dtos.UserDtoManager;
import utils.Tools;

/**
 *
 * @author toure
 */
@Stateless
public class ManageUser implements ManageUserRemote {

  @PersistenceContext
  EntityManager em;

  @Override
  public UserDto signUp(UserDto udto) throws SignupEmailException, SignupNickNameException {
    udto.addDate = new Date();
    udto.state = UserStates.Pending;
    try {
      User u = UserDtoManager.createUser(udto);

      em.persist(u);
      udto.activationCode = Tools.generateString(new Random(), 32);
      udto.id = u.getId();
      UserActivation ua = new UserActivation(udto.getActivationCode(), u);
      em.persist(ua);
      em.flush();
      return udto;
    } catch (PersistenceException ex) {
      throw new SignupNickNameException();
    }
  }

  @Override
  public void activate(Long user, String code) throws ActivatedCodeException {
    UserActivation ua = em.find(UserActivation.class, code);
    if (ua == null) {
      throw new ActivatedCodeException();
    }
    User u = ua.getUser();
    if (user.equals(u.getId())) {
      u.setState(UserStates.Activated);
      em.merge(u);
      em.remove(ua);
      em.flush();
    }
  }

  @Override
  public void activate(Long user) {
    try {
      User u = em.find(User.class, user);
      TypedQuery<UserActivation> query = em.createQuery("FROM UserActivation ua join ua.user u WHERE u.id = " + u.getId(), UserActivation.class);
      UserActivation ua = query.getSingleResult();
      if (ua != null) {
	em.remove(ua);
      }
      u.setState(UserStates.Activated);
      em.merge(u);
      em.flush();
    } catch (PersistenceException ex) {
      Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);

    }
  }

  @Override
  public void deactivate(Long user) {
    User u = em.find(User.class, user);
    u.setState(UserStates.Deactivated);
    em.merge(u);
  }

  @Override
  public void save(UserDtoNoPw udto) {
    UserDtoManager.mergeOrSave(udto, em);
  }

  @Override
  public List<TransactionDto> getTransaction(Long user) {
    User u = em.find(User.class, user);
    List<TransactionDto> res = new ArrayList<>();
    for (Transaction t : u.getTransactions()) {
      res.add(TransactionDtoManager.getDto(t));
    }
    return res;
  }

  //TODO userDto => userDtoNoPw
  @Override
  public UserDto login(String email, String password) {
    Long id = 1L;
    TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE (u.email = :email OR u.nickName = :email) AND u.password = :password AND u.state=:active", User.class);
    query.setParameter("email", email);
    query.setParameter("password", password);
    query.setParameter("active", UserStates.Activated);
    User user = query.getSingleResult();
    return UserDtoManager.getUser(user);
  }

  @Override
  public Set<SimpleUserDto> getAllUser() {
    Query q;

    q = em.createQuery("select u From User u ", User.class);
    List<User> lu = q.getResultList();
    Set<SimpleUserDto> ludto = new HashSet<>();
    for (User u : lu) {
      ludto.add(UserDtoManager.getSimpleDto(u));
    }
    return ludto;
  }

  @Override
  public UserDtoNoPw getUser(Long id) {
    User u = em.find(User.class, id);
    return UserDtoManager.getUserNoPw(u);
  }

  @Override
  public void removeUser(Long id) {
    try {
      User u = em.find(User.class, id);
      for (Transaction t : u.getTransactions()) {
	t.setUser(null);
	em.merge(t);
      }
      for (UsersFilms uf : u.getFilms()) {
	em.remove(uf);
      }
      if (u.getCaddy() != null) {
	em.remove(u.getCaddy());
      }
      TypedQuery<UserActivation> query = em.createQuery("FROM UserActivation ua join ua.user u WHERE u.id = " + u.getId(), UserActivation.class);
      UserActivation ua = query.getSingleResult();
      if (ua != null) {
	em.remove(ua);
      }
      em.remove(u);
      em.flush();
    } catch (PersistenceException ex) {
      Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);

    }
  }

  @Override
  public void mergeOrSave(UserDtoNoPw udto) {
    UserDtoManager.mergeOrSave(udto, em);
  }

  @Override
  public List<FilmDto> getFilms(Long id) {
    User p = em.find(User.class, id);
    List<FilmDto> lfdto = new ArrayList<>();
    for (UsersFilms f : p.getFilms()) {
      lfdto.add(FilmDtoManager.getDto(f.getFilm()));
    }
    return lfdto;
  }

  @Override
  public boolean changePassword(Long id, String oldPassword, String newPassword) {
    User u = em.find(User.class, id);
    if (!u.getPassword().equals(oldPassword)) {
      return false;
    } else {
      u.setPassword(newPassword);
      em.merge(u);
      return true;
    }
  }

  @Override
  public boolean changeEmail(Long id, String email, String newPassword) {
    User u = em.find(User.class, id);
    if (!u.getPassword().equals(newPassword)) {
      return false;
    } else {
      u.setEmail(email);
      em.merge(u);
      return true;
    }
  }
}
