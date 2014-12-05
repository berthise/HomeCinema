/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.SimpleUserDto;
import dtos.UserDto;
import dtos.VideoDto;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class JeuDeTestStructUser {

    public static void main(String[] args) {

	    Admin a = new Admin();

	    /* **********************
	     * create users: robin, seb, pierre, narjes et abdou
	     */
	    UserDto rob_user = createAndPushUser(a, "rob", "rob@mail.net", "Jean-Robin", "Grandchamp", true);
	    UserDto seb_user = createAndPushUser(a, "seb", "seb@mail.net", "Sébastien", "Berthier", true);
	    UserDto pierre_user = createAndPushUser(a, "pierre", "pierre@mail.net", "Pierre", "Odin", true);
	    UserDto narjes_user = createAndPushUser(a, "narjes", "narjes@mail.net", "Narjes", "Jomaa", false);
	    UserDto abdou_user = createAndPushUser(a, "abdou", "abdou@mail.net", "Abdourahamane", "Touré", false);
	    /*
	     ********************** */




    }

    private static void userCaddyProduct(Admin a, UserDto rob_user, ProductDto sw_product) {

	System.out.print(rob_user.nickName + " caddy : " + sw_product.name);
	a.getManageTransactionRemote().addProduct(rob_user.id, sw_product.id);
	System.out.println(" ...  done");
    }

    private static void userBuyProduct(Admin a, UserDto rob_user, ProductDto ab_product, long btn) {
	//achat american beauty
	System.out.print(rob_user.nickName + " buy : " + ab_product.name);
	a.getManageTransactionRemote().addProduct(rob_user.id, ab_product.id);
	Long trans = a.getManageTransactionRemote().validate(rob_user.id, null);
	a.getManageTransactionRemote().validatePayement(trans, btn);
	System.out.println(" ...  done");
    }

    private static UserDto createAndPushUser(Admin a, String nickname, String email, String firstname, String lastname, boolean activation) {
	//creer user robin
	System.out.print("create user : " + nickname);
	UserDto u = new UserDto();
	u.birthDate = new Date();
	u.email = email;
	u.firstName = firstname;
	u.lastName = lastname;
	u.nickName = nickname;
	u.password = "password";
      try {
	u = a.getManageUserRemote().signUp(u);
      } catch (SignupEmailException ex) {
	Logger.getLogger(JeuDeTestStructUser.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SignupNickNameException ex) {
	Logger.getLogger(JeuDeTestStructUser.class.getName()).log(Level.SEVERE, null, ex);
      }
	if (activation) {
	    a.getManageUserRemote().activate(u.getId());
	    u = a.getManageUserRemote().login(u.email, u.password);
	}
	System.out.println(" ...  done");
	return u;
    }

    private static ProductDto createAndPushProduct(Admin a, List<FilmDto> films, String name, double price, boolean main) {
	//creer un produit
	System.out.print("create product : " + name);
	ProductDto pdto = new ProductDto();
	pdto.name = name;
	pdto.price = price;
	pdto.id = a.getManageProductRemote().createProduct(pdto);
	for (FilmDto f : films) {
	    a.getManageProductRemote().addExistingFilm(pdto.id, f.id, main);
	}
	System.out.println(" ...  done");
	return pdto;
    }

    private static ProductDto createAndPushProduct(Admin a, FilmDto f, String name, double price, boolean main) {
	//creer un produit
	System.out.print("create product : " + name);

	ProductDto pdto = new ProductDto();
	pdto.name = name;
	pdto.price = price;
	pdto.id = a.getManageProductRemote().createProduct(pdto);
	a.getManageProductRemote().addExistingFilm(pdto.id, f.id, main);
	System.out.println(" ...  done");

	return pdto;
    }

    private static FilmDto createAndPushFilm(Admin a, long film_id, long video_id, long trailer_id) throws IOException, ParseException, JSONException {
	//creer american beauty
	System.out.print("create film : ");
	FilmDto f = a.createFilm(film_id);
	System.out.print(f.title);
	a.getManageFilmRemote().createFilm(f);
	a.getManageFilmRemote().addExistingVideo(f.id, video_id);
	a.getManageFilmRemote().setExistingTrailer(f.id, trailer_id);
	a.getManageFilmRemote().addGenres(film_id, a.getGenre(film_id));
	a.getManageFilmRemote().addActors(film_id, a.getCast(film_id));
	a.getManageFilmRemote().addDirectors(film_id, a.getDirectors(film_id));
	System.out.println(" ...  done");
	return f;
    }

    private static VideoDto createAndPushVideo(Admin a, int resolution, String url) {
	System.out.print("create video : " + url);
	VideoDto v = new VideoDto();
	v.resolution = resolution;
	v.url = url;
	v.id = a.getManageVideoRemote().createVideo(v);
	System.out.println(" ...  done");
	return v;
    }
}
