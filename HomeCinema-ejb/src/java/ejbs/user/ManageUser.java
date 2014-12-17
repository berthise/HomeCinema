/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.user;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dtos.FilmDto;
import dtos.SimpleUserDto;
import dtos.TransactionDto;
import dtos.UserDto;
import dtos.UserDtoNoPw;
import ejbs.ManageUserRemote;
import entities.Film;
import entities.Product;
import entities.Transaction;
import entities.User;
import entities.UserActivation;
import entities.UserRetrieve;
import entities.UsersFilms;
import enums.Lang;
import enums.UserStates;
import exception.ActivatedCodeException;
import exception.RetrieveCodeException;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import exception.UncorrectPasswordException;
import exception.UnknownAccountException;
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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import managers.dtos.FilmDtoManager;
import managers.dtos.TransactionDtoManager;
import managers.dtos.UserDtoManager;
import utils.Securite;
import utils.Tools;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.jboss.weld.util.collections.ArraySet;

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
	    if (ex.getCause() instanceof DatabaseException) {
		DatabaseException dbex = (DatabaseException) ex.getCause();
		if (dbex.getCause() instanceof MySQLIntegrityConstraintViolationException) {
		    MySQLIntegrityConstraintViolationException intex = (MySQLIntegrityConstraintViolationException) dbex.getCause();
		    if (intex.getErrorCode() == 1062) {
			if (intex.getMessage().matches("(.*)EMAIL(.*)")) {
			    throw new SignupEmailException();
			} else {
			    throw new SignupNickNameException();
			}
		    }
		}
		throw new SignupEmailException();

	    }
	    return null;
	}
    }

    @Override
    public void activate(Long user, String code) throws ActivatedCodeException {
	try {
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
	} catch (PersistenceException ex) {
	    Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);

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
    public UserDto login(String email, String crypted_password) throws UncorrectPasswordException {
	try {
	    Long id = 1L;
	    TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE (u.email = :email OR u.nickName = :email)AND (u.state=:active OR u.state=:retrieve)", User.class);
	    query.setParameter("email", email);
	    query.setParameter("active", UserStates.Activated);
	    query.setParameter("retrieve", UserStates.Retrieve);
	    User u = query.getSingleResult();

	    if (u.getState().equals(UserStates.Retrieve)) {
		TypedQuery<UserRetrieve> q = em.createQuery("FROM UserRetrieve ur join ur.user u WHERE u.id = " + u.getId(), UserRetrieve.class);
		UserRetrieve ur = q.getSingleResult();
		if (ur != null) {
		    em.remove(ur);
		}
		u.setState(UserStates.Activated);
		em.merge(u);
		em.flush();

	    }


	    /* verification du password */
	    if (Securite.equale(crypted_password, u.getPassword())) {
		return UserDtoManager.getUser(u);
	    }
	    throw new UncorrectPasswordException();
	} catch (NoResultException ex) {
	    // TOOD change exception
	    throw new UncorrectPasswordException();
	} catch (PersistenceException ex) {
	    throw new UncorrectPasswordException();

	}
    }

    @Override
    public List<TransactionDto> getTransaction(Long user, Lang lang) {
	User u = em.find(User.class, user);
	List<TransactionDto> res = new ArrayList<>();
	for (Transaction t : u.getTransactions()) {
	    res.add(TransactionDtoManager.getDto(t, lang));
	}
	return res;
    }

    //TODO userDto => userDtoNoPw
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
    public Set<SimpleUserDto> getAllUser(Integer limit) {
	Query q;

	q = em.createQuery("select u From User u ", User.class);
	q.setMaxResults(limit);
	List<User> lu = q.getResultList();
	Set<SimpleUserDto> ludto = new HashSet<>();
	for (User u : lu) {
	    ludto.add(UserDtoManager.getSimpleDto(u));
	}
	return ludto;
    }

    @Override
    public void removeUser(Long id) {
	System.out.println("test");
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

	    try {
		TypedQuery<UserActivation> query = em.createQuery("FROM UserActivation ua join ua.user u WHERE u.id = " + u.getId(), UserActivation.class);
		UserActivation ua = query.getSingleResult();
		if (ua != null) {
		    em.remove(ua);
		}
	    } catch (Exception ex) {

	    }
	    em.remove(u);
	    em.flush();

	} catch (PersistenceException ex) {
	    Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);

	}
    }

    @Override
    public UserDtoNoPw getUser(Long id) {
	User u = em.find(User.class, id);
	return UserDtoManager.getUserNoPw(u);
    }

    @Override
    public void mergeOrSave(UserDtoNoPw udto) {
	UserDtoManager.mergeOrSave(udto, em);
    }

    @Override
    public List<FilmDto> getFilms(Long id, Lang lang) {
	User p = em.find(User.class, id);
	List<FilmDto> lfdto = new ArrayList<>();
	for (UsersFilms f : p.getFilms()) {
	    lfdto.add(FilmDtoManager.getDto(f.getFilm(), lang));
	}
	return lfdto;
    }

    @Override
    public int getNbFilms(Long id) {
	User p = em.find(User.class, id);

	return p.getFilms().size();
    }

    @Override
    public List<FilmDto> getFilmsPage(Long id, Lang lang, int row, int limit) {
	User p = em.find(User.class, id);
	List<FilmDto> lfdto = new ArrayList<>();
	int i = 0;
	for (UsersFilms f : p.getFilms()) {
	    if (i >= row && i < row + limit) {
		lfdto.add(FilmDtoManager.getDto(f.getFilm(), lang));
	    }
	    i++;
	}
	return lfdto;
    }

    @Override
    public Set<Long> getMyProductId(Long id) {
	User u = em.find(User.class, id);
	Set<Long> lfid = new ArraySet<>();
	for (Transaction t : u.getTransactions()) {
	    for (Product p : t.getProducts()) {
		lfid.add(p.getId());
		if (p.getFilms().size() > 1) // pack, add main product of each film in pack
		{
		    for (Film f : p.getFilms()) {
			lfid.add(f.getMain_product().getId());
		    }
		}
	    }
	}
	return lfid;
    }

    @Override
    public Integer countFilms(Long id) {
	User p = em.find(User.class, id);
	return p.getFilms().size();
    }

    @Override
    public boolean changePassword(Long id, String newPassword, String oldPassword) {
	User u = em.find(User.class, id);
	if (!Securite.equale(oldPassword, u.getPassword())) {
	    return false;
	} else {
	    u.setPassword(newPassword);
	    em.merge(u);
	    return true;
	}
    }

    @Override
    public String retrievePassword(String email) throws UnknownAccountException {
	try {
	    TypedQuery<User> query = em.createQuery("FROM User u WHERE u.email = :email AND u.state=:active", User.class);
	    // TODO et non logge
	    query.setParameter("email", email);
	    query.setParameter("active", UserStates.Activated);
	    User u = query.getSingleResult();

	    String code = Tools.generateString(new Random(), 32);
	    UserRetrieve ur = new UserRetrieve(code, u);
	    em.persist(ur);
	    u.setState(UserStates.Retrieve);
	    em.merge(u);
	    em.flush();
	    return code;
	} catch (NoResultException ex) {
	    throw new UnknownAccountException();
	} catch (PersistenceException ex) {
	    throw new UnknownAccountException();
	}
    }

    @Override
    public void changePasswordRetrieve(String code, String newPassword) throws RetrieveCodeException {
	try {
	    UserRetrieve ur = em.find(UserRetrieve.class, code);
	    if (ur == null) {
		throw new RetrieveCodeException();
	    }
	    User u = ur.getUser();
	    u.setPassword(newPassword);
	    u.setState(UserStates.Activated);
	    em.merge(u);
	    em.remove(ur);
	    em.flush();
	} catch (PersistenceException ex) {
	    Logger.getLogger(ManageUser.class.getName()).log(Level.SEVERE, null, ex);

	    throw new RetrieveCodeException();
	}
    }

    @Override
    public boolean changeEmail(Long id, String email, String oldPassword
    ) {
	User u = em.find(User.class, id);
	if (!Securite.equale(oldPassword, u.getPassword())) {
	    return false;
	} else {
	    u.setEmail(email);
	    em.merge(u);
	    return true;
	}
    }
}
