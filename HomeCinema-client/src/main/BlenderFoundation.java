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
import enums.Lang;
import enums.Langs;
import enums.VideoFormat;
import exception.DeactivatedProductException;
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
public class BlenderFoundation {

  public static void main(String[] args) throws SignupEmailException, SignupNickNameException {
    try {
      Admin a = new Admin();

      /* **********************
       * create American Beauty videos film and product 
       */
      VideoDto ed_video = createAndPushVideo(a, 1080,
	      "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_1080p_stereo.ogg",
	      Langs.EN, VideoFormat.OGG);
      VideoDto ed_trailer = createAndPushVideo(a, 400,
	      "http://blendertestbuilds.de/index.php?dir=Video/ProjectOrange/&file=elephantsdream_teaser.mp4",
	      Langs.EN, VideoFormat.MP4);
      FilmDto ed_film = createAndPushFilm(a, 9761L, ed_video.id, ed_trailer.id);
      ProductDto ed_product = createAndPushProduct(a, ed_film, "Elephant Dream", 10.0, true);

      /*
       ********************** */

      /* **********************
       * create Star Wars 4 videos film and product 
       */
      VideoDto bbb_video = createAndPushVideo(a, 1080,
	      "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_1080p_stereo.ogg");
      VideoDto bbb_trailer = createAndPushVideo(a, 400,
	      "http://download.blender.org/peach/trailer/trailer_400p.ogg");
      FilmDto bbb_film = createAndPushFilm(a, 10378L, bbb_video.id, bbb_trailer.id);
      ProductDto bbb_product = createAndPushProduct(a, bbb_film, "Big Buck Bunny", 10.0, true);

      List<Long> bbb_videos = new ArrayList<>();
      bbb_videos.add(createAndPushVideo(a, 720,
	      "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_stereo.ogg",
	      Langs.EN, VideoFormat.OGG).id);
      bbb_videos.add(createAndPushVideo(a, 1080,
	      "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_1080p_stereo.ogg",
	      Langs.EN, VideoFormat.OGG).id);
      bbb_videos.add(createAndPushVideo(a, 720,
	      "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_720p_stereo.ogg",
	      Langs.FR, VideoFormat.OGG).id);
      bbb_videos.add(createAndPushVideo(a, 1080,
	      "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_1080p_stereo.ogg",
	      Langs.FR, VideoFormat.OGG).id);
      a.getManageFilmRemote().addExistingVideos(bbb_film.id, bbb_videos);
      /*
       ********************** */

      /* **********************
       * create Star Wars 5 videos film and product 
       */
      VideoDto stl_video = createAndPushVideo(a, 2048,
	      "http://mirrorblender.top-ix.org/movies/sintel-2048-surround.mp4",
	      Langs.EN, VideoFormat.MP4);
      VideoDto stl_trailer = createAndPushVideo(a, 480,
	      "https://download.blender.org/durian/trailer/sintel_trailer-480p.ogv",
	      Langs.EN, VideoFormat.OGG);
      FilmDto stl_film = createAndPushFilm(a, 45745L, stl_video.id, stl_trailer.id);
      ProductDto stl_product = createAndPushProduct(a, stl_film, "Sintel", 10.0, true);
      
      List<Long> stl_videos = new ArrayList<>();
      stl_videos.add(createAndPushVideo(a, 1280,
	      "http://mirrorblender.top-ix.org/movies/sintel-1280-surround.mp4",
	      Langs.EN, VideoFormat.MP4).id);
            stl_videos.add(createAndPushVideo(a, 1024,
	      "http://mirrorblender.top-ix.org/movies/sintel-1024-surround.mp4",
	      Langs.EN, VideoFormat.MP4).id);
	     stl_videos.add(createAndPushVideo(a, 1024,
	      "http://mirrorblender.top-ix.org/movies/sintel-1024-surround.webm",
	      Langs.EN, VideoFormat.WEBM).id);
	     	     stl_videos.add(createAndPushVideo(a, 1024,
	      "http://mirrorblender.top-ix.org/movies/sintel-1024-surround.ogg",
	      Langs.EN, VideoFormat.OGG).id);
	          stl_videos.add(createAndPushVideo(a, 1280,
	      "http://mirrorblender.top-ix.org/movies/sintel-1280-surround.mp4",
	      Langs.FR, VideoFormat.MP4).id);
            stl_videos.add(createAndPushVideo(a, 1024,
	      "http://mirrorblender.top-ix.org/movies/sintel-1024-surround.mp4",
	      Langs.FR, VideoFormat.MP4).id);
      a.getManageFilmRemote().addExistingVideos(stl_film.id, stl_videos);
      /*
       ********************** */

      /* **********************
       * create Star Wars 6 videos film and product 
       */
      VideoDto tos_video = createAndPushVideo(a, 1080,
	      "http://media.xiph.org/mango/tears_of_steel_1080p.webm",
	      Langs.EN, VideoFormat.WEBM);
      VideoDto tos_trailer = createAndPushVideo(a, 800,
	      "https://download.blender.org/demo/movies/tears-of-steel_teaser.mp4");
      FilmDto tos_film = createAndPushFilm(a, 133701L, tos_video.id, tos_trailer.id);
      ProductDto tos_product = createAndPushProduct(a, tos_film, "Tears of Steel", 10.0, true);
      
            List<Long> tos_videos = new ArrayList<>();
      tos_videos.add(createAndPushVideo(a, 1080,
	      "http://blender-mirror.kino3d.org/mango/download.blender.org/demo/movies/ToS/tears_of_steel_1080p.mkv",
	      Langs.EN, VideoFormat.MP4).id);
            tos_videos.add(createAndPushVideo(a, 720,
	      "http://blender-mirror.kino3d.org/mango/download.blender.org/demo/movies/ToS/tears_of_steel_720p.mkv",
	      Langs.EN, VideoFormat.MP4).id);
	          tos_videos.add(createAndPushVideo(a, 1280,
	      "http://mirrorblender.top-ix.org/movies/sintel-1280-surround.mp4",
	      Langs.FR, VideoFormat.MP4).id);
            tos_videos.add(createAndPushVideo(a, 1024,
	      "http://mirrorblender.top-ix.org/movies/sintel-1024-surround.mp4",
	      Langs.FR, VideoFormat.MP4).id);
      a.getManageFilmRemote().addExistingVideos(tos_film.id, tos_videos);
      /*
       ********************** */

      /* **********************
       * create Blender foundation product 
       */
      ArrayList<FilmDto> bf_films = new ArrayList<>();
      bf_films.add(ed_film);
      bf_films.add(bbb_film);
      bf_films.add(stl_film);
      bf_films.add(tos_film);
      ProductDto sw_product = createAndPushProduct(a, bf_films, "Blender Foundation", 25.0, false);
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

  private static void userCaddyProduct(Admin a, UserDto rob_user, ProductDto sw_product) throws DeactivatedProductException {

    System.out.print(rob_user.nickName + " caddy : " + sw_product.name);
    a.getManageTransactionRemote().addProduct(rob_user.id, sw_product.id,Lang.EN);
    System.out.println(" ...  done");
  }

  private static void userBuyProduct(Admin a, UserDto rob_user, ProductDto ab_product, long btn) throws DeactivatedProductException {
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
      Logger.getLogger(BlenderFoundation.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SignupNickNameException ex) {
      Logger.getLogger(BlenderFoundation.class.getName()).log(Level.SEVERE, null, ex);
    }
    if (activation) {
      try {
	a.getManageUserRemote().activate(u.getId());
	u = a.getManageUserRemote().login(u.email, u.password);
      } catch (UncorrectPasswordException ex) {
	Logger.getLogger(BlenderFoundation.class.getName()).log(Level.SEVERE, null, ex);
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
    return createAndPushVideo(a, resolution, url, Langs.EN, VideoFormat.OGG);
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
