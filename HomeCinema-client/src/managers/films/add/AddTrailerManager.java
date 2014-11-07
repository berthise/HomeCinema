/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films.add;

import UtilsJson.JsonReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.utils.ModuleManager;
import models.FilmModel;
import models.VideoModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author seb
 */
public class AddTrailerManager extends ModuleManager {

  FilmModel film = null;

  public AddTrailerManager(FilmModel f) {
    this("add a trailer to the film", f);
  }

  public AddTrailerManager(String name, FilmModel f) {
    super(name);
    film = f;
  }

  @Override
  public void exec() {
    Scanner reader = new Scanner(System.in);
    try {
      VideoModel video = new VideoModel();
      System.out.println("Url:");
      video.setUrl(reader.nextLine());
      System.out.println("Resolution [240,...]:");
      video.setResolution(reader.nextInt());
      film.AddTrailer(video);

    } catch (InputMismatchException e) {
      System.out.println("Error: abording traile edition");
    }
  }

  public FilmModel createFilm(Long id) throws JSONException, IOException, ParseException {
    FilmModel f = new FilmModel();
    String urlString = "http://api.themoviedb.org/3/movie/" + id + "?api_key=63d250a5b71c307f7592228c79b729cf";

    JSONObject json = JsonReader.readJsonFromUrl(urlString);
    f.setId(id);
    f.setTitle((String) json.get("title"));
    f.setCover((String) json.get("poster_path"));
    f.setRuntime((int) json.get("runtime"));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    f.setRelease_date(formatter.parse((String) json.get("release_date")));
    f.setOverview((String) json.get("overview"));
    f.setRating((Double) json.get("vote_average"));

    return f;

  }
}
