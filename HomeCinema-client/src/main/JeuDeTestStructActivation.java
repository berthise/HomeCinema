/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.UserDto;
import dtos.VideoDto;
import enums.Langs;
import enums.VideoFormat;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import exception.UncorrectPasswordException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import utils.Securite;

/**
 *
 * @author titou
 */
public class JeuDeTestStructActivation {

    public static void main(String[] args) throws SignupEmailException, SignupNickNameException {
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
	    	    
	    List<Long> ab_videos = new ArrayList<>();
	    ab_videos.add(createAndPushVideo(a, 480,
		    "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4",
		    Langs.FR, VideoFormat.MP4).id);
	    ab_videos.add(createAndPushVideo(a, 480,
		    "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", 
		    Langs.FR, VideoFormat.MP4).id);
	    ab_videos.add(createAndPushVideo(a, 1080,
		    "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4",
		    Langs.EN, VideoFormat.MP4).id);
	    ab_videos.add(createAndPushVideo(a, 1080,
		    "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4",
		    Langs.FR, VideoFormat.MP4).id);
	    ab_videos.add(createAndPushVideo(a, 640,
		    "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", 
		    Langs.EN,VideoFormat.WEBM).id);
	    ab_videos.add(createAndPushVideo(a, 640,
		    "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", 
		    Langs.FR,VideoFormat.WEBM).id);
	    a.getManageFilmRemote().addExistingVideos(ab_film.id, ab_videos);
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
	    
	    List<Long> fc_videos = new ArrayList<>();
	    fc_videos.add(createAndPushVideo(a, 480,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4",
		    Langs.EN, VideoFormat.MP4).id);
	    fc_videos.add(createAndPushVideo(a, 480,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4",
		    Langs.FR, VideoFormat.MP4).id);
	    fc_videos.add(createAndPushVideo(a, 1080,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4",
		    Langs.EN, VideoFormat.MP4).id);
	    fc_videos.add(createAndPushVideo(a, 1080,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4",
		    Langs.FR, VideoFormat.MP4).id);
	    fc_videos.add(createAndPushVideo(a, 640,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4",
		    Langs.EN,VideoFormat.WEBM).id);
	    fc_videos.add(createAndPushVideo(a, 640,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4",
		    Langs.FR,VideoFormat.WEBM).id);
	    a.getManageFilmRemote().addExistingVideos(fc_film.id, fc_videos);
	    /*
	     ********************** */

	    /* **********************
	     * create Juno videos film and product 
	     */
	    VideoDto j_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto j_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto j_film = createAndPushFilm(a, 7326L, j_video.id, j_trailer.id);
	    ProductDto j_product = createAndPushProduct(a, j_film, "Juno", 6.0, true);
	    /*
	     ********************** */

	    /* **********************
	     * create sw1 videos film and product 
	     */
	    VideoDto sw1_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto sw1_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto sw1_film = createAndPushFilm(a, 1893L, sw1_video.id, sw1_trailer.id);
	    ProductDto sw1_product = createAndPushProduct(a, sw1_film, "Star Wars I", 6.0, true);
	    /*
	     ********************** */

	    /* **********************
	     * create sw2 videos film and product 
	     */
	    VideoDto sw2_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto sw2_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto sw2_film = createAndPushFilm(a, 1894L, sw2_video.id, sw2_trailer.id);
	    ProductDto sw2_product = createAndPushProduct(a, sw2_film, "Star wars II", 6.0, true);
	    /*
	     ********************** */

	    /* **********************
	     * create sw3 videos film and product 
	     */
	    VideoDto sw3_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto sw3_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto sw3_film = createAndPushFilm(a, 1895L, sw3_video.id, sw3_trailer.id);
	    ProductDto sw3_product = createAndPushProduct(a, sw3_film, "Star Wars III", 6.0, true);
	    /*
	     ********************** */

	    /* **********************
	     * create Star Wars trilogie product 
	     */
	    ArrayList<FilmDto> swp_films = new ArrayList<>();
	    swp_films.add(sw1_film);
	    swp_films.add(sw2_film);
	    swp_films.add(sw3_film);
	    ProductDto swp_product = createAndPushProduct(a, swp_films, "Star Wars Prelogie", 25.0, false);
	    /*
	     ********************** */

	    /* **********************
	     * create Star Wars trilogie product 
	     */
	    ArrayList<FilmDto> swa_films = new ArrayList<>();
	    swa_films.add(sw1_film);
	    swa_films.add(sw2_film);
	    swa_films.add(sw3_film);
	    swa_films.add(sw4_film);
	    swa_films.add(sw5_film);
	    swa_films.add(sw6_film);
	    ProductDto swt_product = createAndPushProduct(a, swa_films, "Star Wars", 45.0, false);
	    /*
	     ********************** */

	    /* **********************
	     * create requiem for a dream videos film and product 
	     */
	    VideoDto rd_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto rd_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto rd_film = createAndPushFilm(a, 641L, rd_video.id, rd_trailer.id);
	    ProductDto rd_product = createAndPushProduct(a, rd_film, "Requieme for a dream", 11.0, true);
	    /*
	     ********************** */

	    /* **********************
	     * create shawshank redemption videos film and product 
	     */
	    VideoDto sr_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto sr_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto sr_film = createAndPushFilm(a, 278L, sr_video.id, sr_trailer.id);
	    ProductDto sr_product = createAndPushProduct(a, sr_film, "The Shawshank Redemption", 11.0, true);
	    /*
	     ********************** */

	    
	    	    /* **********************
	     * create american history X videos film and product 
	     */
	    VideoDto ah_video = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4");
	    VideoDto ah_trailer = createAndPushVideo(a, 240,
		    "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4");
	    FilmDto ah_film = createAndPushFilm(a, 73L, ah_video.id, ah_trailer.id);
	    ProductDto ah_product = createAndPushProduct(a, ah_film, "American History X", 11.0, true);
	    /*
	     ********************** */
	    
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
	a.getManageTransactionRemote().addProduct(rob_user.id, sw_product.id,Lang.EN);
	System.out.println(" ...  done");
    }

    private static void userBuyProduct(Admin a, UserDto rob_user, ProductDto ab_product, long btn) {
	//achat american beauty
	System.out.print(rob_user.nickName + " buy : " + ab_product.name);
	a.getManageTransactionRemote().addProduct(rob_user.id, ab_product.id,Lang.EN);
	Long trans = a.getManageTransactionRemote().validate(rob_user.id, null);
	a.getManageTransactionRemote().validatePayement(trans, btn);
	System.out.println(" ...  done");
    }

    private static UserDto createAndPushUser(Admin a, String nickname, String email, String firstname, String lastname, boolean activation) throws SignupEmailException, SignupNickNameException {
	//creer user robin
	System.out.print("create user : " + nickname);
	UserDto u = new UserDto();
	u.birthDate = new Date();
	u.email = email;
	u.firstName = firstname;
	u.lastName = lastname;
	u.nickName = nickname;
	u.password = "password";
	u.password = Securite.crypte(u.password);

      try {
	u = a.getManageUserRemote().signUp(u);
      } catch (SignupEmailException ex) {
	Logger.getLogger(JeuDeTestStructActivation.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SignupNickNameException ex) {
	Logger.getLogger(JeuDeTestStructActivation.class.getName()).log(Level.SEVERE, null, ex);
      }
	if (activation) {
	  try {
	    a.getManageUserRemote().activate(u.getId());
	    u = a.getManageUserRemote().login(u.email, u.password);
	  } catch (UncorrectPasswordException ex) {
	    Logger.getLogger(JeuDeTestStructActivation.class.getName()).log(Level.SEVERE, null, ex);
	  }
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
	pdto.id = a.getManageProductRemote().createProduct(pdto,Lang.EN);
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
	pdto.id = a.getManageProductRemote().createProduct(pdto,Lang.EN);
	a.getManageProductRemote().addExistingFilm(pdto.id, f.id, main);
	System.out.println(" ...  done");

	return pdto;
    }

    private static FilmDto createAndPushFilm(Admin a, long film_id, long video_id, long trailer_id) throws IOException, ParseException, JSONException {
	//creer american beauty
	System.out.print("create film : ");
	FilmDto f = a.createFilm(film_id);
	System.out.print(f.title);
	a.getManageFilmRemote().createFilm(f,Lang.EN);
	a.getManageFilmRemote().addExistingVideo(f.id, video_id);
	a.getManageFilmRemote().setExistingTrailer(f.id, trailer_id);
	a.getManageFilmRemote().addGenres(film_id, a.getGenre(film_id),Lang.EN);
	a.getManageFilmRemote().addActors(film_id, a.getCast(film_id));
	a.getManageFilmRemote().addDirectors(film_id, a.getDirectors(film_id));
	System.out.println(" ...  done");
	return f;
    }

    private static VideoDto createAndPushVideo(Admin a, int resolution, String url) {
	  return createAndPushVideo(a, resolution, url, Langs.EN, VideoFormat.MP4);
	}
    private static VideoDto createAndPushVideo(Admin a, int resolution, String url, Langs audio, VideoFormat format) {
	System.out.print("create video : " + url);
	VideoDto v = new VideoDto();
	v.resolution = resolution;
	v.audio = audio;
	v.format = format;
	v.url = url;
	v.id = a.getManageVideoRemote().createVideo(v);
	System.out.println(" ...  done");
	return v;
    }
}
