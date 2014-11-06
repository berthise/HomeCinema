/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.films.add;

import UtilsJson.JsonReader;
import dtos.FilmDto;
import managers.users.*;
import models.UserModel;
import dtos.UserDto;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.utils.ExitManager;
import main.utils.Manager_if;
import main.utils.Menu;
import main.utils.ModuleManager;
import main.utils.ReturnManager;
import models.FilmModel;
import models.LoggedModel;
import models.ProductModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author seb
 */
public class EditFilmManager extends ModuleManager implements Manager_if {

  ProductModel productModel = null;

  public EditFilmManager(LoggedModel log, ProductModel model) {
    super(log);
    productModel = model;
  }

  @Override
  public String getMenuEntry() {
    return "get film info from tmdb";
  }

  @Override
  public void runMenuEntry() {
    Scanner reader = new Scanner(System.in);
    try {
      System.out.println("Id:");
      Long id = reader.nextLong();
      productModel.AddFilm(this.createFilm(id));

    } catch (InputMismatchException e) {
      System.out.println("Error: abording user edition");
    } catch (JSONException ex) {
      Logger.getLogger(EditFilmManager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(EditFilmManager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
      Logger.getLogger(EditFilmManager.class.getName()).log(Level.SEVERE, null, ex);
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
