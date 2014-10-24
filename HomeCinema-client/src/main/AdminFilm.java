/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UtilsJson.JsonReader;
import dtos.FilmDto;
import org.json.JSONObject;
import java.util.Date;
/**
 *
 * @author titou
 */
public  class AdminFilm {
    
    public static void createFilm(Long id)
    {
        FilmDto fdto = new FilmDto();
                String urlString = "https://api.themoviedb.org/3/movie/" + id + "?api_key=63d250a5b71c307f7592228c79b729cf";
        try {
            JSONObject json = JsonReader.readJsonFromUrl(urlString);
            fdto.title = (String) json.get("title");
            fdto.cover = (String) json.get("poster_path");
            fdto.runtime= (int) json.get("runtime");
            fdto.release_date = new Date((String) json.get("release_date"));
            fdto.overview = (String) json.get("overview");

        } catch (Exception e) {
            
        }
        
        HomeCinemaClient.getManageFilmRemote().createFilm(fdto);
    }
    
    
}
