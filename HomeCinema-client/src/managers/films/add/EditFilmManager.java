/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films.add;

import main.utils.AbortException;
import UtilsJson.JsonReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.utils.ModuleManager;
import models.FilmModel;
import models.ProductModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author seb
 */
public class EditFilmManager extends ModuleManager {

  ProductModel productModel = null;

  public EditFilmManager(ProductModel p) {
    this("get film info from tmdb", p);
  }

  public EditFilmManager(String name, ProductModel p) {
    super(name);
    productModel = p;
  }

  @Override
  public void exec() throws AbortException {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Id:");
      Long id = reader.nextLong();
      productModel.AddFilm(this.createFilm(id));

    } catch (InputMismatchException e) {
      throw new AbortException();
    } catch (JSONException | IOException | ParseException ex) {
      Logger.getLogger(EditFilmManager.class.getName()).log(Level.SEVERE, null, ex);
      throw new AbortException();
    }
  }

  public FilmModel createFilm(Long id) throws JSONException, IOException, ParseException {
    FilmModel f = new FilmModel();
    System.out.print("Getting tmdb information... ");
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

    System.out.println("done");

    return f;

  }
}
