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
public class JeuDeTestStruct {

  public static void main(String[] args) {
    try {
      Admin a = new Admin();

      /* **********************
       * create American Beauty videos film and product 
       */
      VideoDto ab_video = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4");
      VideoDto ab_trailer = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4");
      FilmDto ab_film = createAndPushFilm(a, 14L, ab_video.id, ab_trailer.id);
      ProductDto ab_product = createAndPushProduct(a, ab_film, "American beauty", 10.0, true);
      /*
       ********************** */

      /* **********************
       * create Star Wars 4 videos film and product 
       */
      VideoDto sw4_video = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4");
      VideoDto sw4_trailer = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4");
      FilmDto sw4_film = createAndPushFilm(a, 11L, sw4_video.id, sw4_trailer.id);
      ProductDto sw4_product = createAndPushProduct(a, sw4_film, "Star Wars 4", 10.0, true);
      /*
       ********************** */

      /* **********************
       * create Star Wars 5 videos film and product 
       */
      VideoDto sw5_video = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4");
      VideoDto sw5_trailer = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4");
      FilmDto sw5_film = createAndPushFilm(a, 1891L, sw5_video.id, sw5_trailer.id);
      ProductDto sw5_product = createAndPushProduct(a, sw5_film, "Star Wars 5", 10.0, true);
      /*
       ********************** */

      /* **********************
       * create Star Wars 6 videos film and product 
       */
      VideoDto sw6_video = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4");
      VideoDto sw6_trailer = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4");
      FilmDto sw6_film = createAndPushFilm(a, 1892L, sw6_video.id, sw6_trailer.id);
      ProductDto sw6_product = createAndPushProduct(a, sw6_film, "Star Wars 6", 10.0, true);
      /*
       ********************** */

      /* **********************
       * create Star Wars trilogie product 
       */
      ArrayList<FilmDto> sw_films = new ArrayList<>();
      sw_films.add(sw4_film);
      sw_films.add(sw5_film);
      sw_films.add(sw6_film);
      ProductDto sw_product = createAndPushProduct(a, sw_films, "Star Wars Trilogie", 25.0, false);
      /*
       ********************** */

      /* **********************
       * create Citizen Kane videos film and product 
       */
      VideoDto ck_video = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4");
      VideoDto ck_trailer = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4");
      FilmDto ck_film = createAndPushFilm(a, 15L, ck_video.id, ck_trailer.id);
      ProductDto ck_product = createAndPushProduct(a, ck_film, "Citizen Kane", 10.0, true);
      /*
       ********************** */

      /* **********************
       * create Fight Club videos film and product 
       */
      VideoDto fc_video = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
      VideoDto fc_trailer = createAndPushVideo(a, 240,
	      "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
      FilmDto fc_film = createAndPushFilm(a, 550L, fc_video.id, fc_trailer.id);
      ProductDto fc_product = createAndPushProduct(a, fc_film, "Fight Club", 10.0, true);
      /*
       ********************** */

      /* **********************
       * create users: robin, seb, pierre, narjes et abdou
       */
      UserDto rob_user = createAndPushUser(a, "rob", "rob@mail.net", "Jean-Robin", "Grandchamp");
      UserDto seb_user = createAndPushUser(a, "seb", "seb@mail.net", "Sébastien", "Berthier");
      UserDto pierre_user = createAndPushUser(a, "pierre", "pierre@mail.net", "Pierre", "Odin");
      UserDto narjes_user = createAndPushUser(a, "narjes", "narjes@mail.net", "Narjes", "Jomaa");
      UserDto abdou_user = createAndPushUser(a, "abdou", "abdou@mail.net", "Abdourahamane", "Touré");
      /*
       ********************** */

      /* **********************
       * robin buy American Beauty et Citizen Kane
       * pierre buy Star Wars Trilogie
       */
      userBuyProduct(a, rob_user, ab_product, 42L);
      userBuyProduct(a, rob_user, ck_product, 43L);
      userBuyProduct(a, pierre_user, sw_product, 44L);
      /*
       ********************** */

     /* **********************
     * robin caddy: Star Wars Trilogie + Fight Club
     * seb caddy: Fight Club
     */
      userCaddyProduct(a, rob_user, sw_product);
      userCaddyProduct(a, rob_user, fc_product);
      userCaddyProduct(a, seb_user, fc_product);
      /*
       ********************** */
      
    } catch (JSONException ex) {
      Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
      Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
    }

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
    Long trans = a.getManageTransactionRemote().validate(rob_user.id);
    a.getManageTransactionRemote().validatePayement(trans, btn);
    System.out.println(" ...  done");
  }

  private static UserDto createAndPushUser(Admin a, String nickname, String email, String firstname, String lastname) {
    //creer user robin
    System.out.print("create user : " + nickname);
    UserDto u = new UserDto();
    u.birthDate = new Date();
    u.email = email;
    u.firstName = firstname;
    u.lastName = lastname;
    u.nickName = nickname;
    u.password = "password";
    a.getManageUserRemote().signUp(u);
    u = a.getManageUserRemote().login(u.email, u.password);
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
