/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UtilsJson.JsonReader;
import dtos.FilmDto;
import dtos.GenreDto;
import dtos.PersonDto;
import java.text.DateFormat;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import ejbs.ManageTransactionRemote;
import ejbs.ManageUserRemote;
import ejbs.ManageVideoRemote;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class Admin {

    private InitialContext ic;
    private ManageProductRemote mpr = null;
    private ManageFilmRemote mfr = null;
    private ManageUserRemote mur = null;
    private ManageVideoRemote mvr = null;
    private ManageTransactionRemote mtr = null;
    //private ManageGenreRemote mgr = null;

    public FilmDto createFilm(Long id) throws JSONException, IOException, ParseException {
       // System.out.println("Add film: " + id);
        FilmDto fdto = new FilmDto();
        String urlString = "http://api.themoviedb.org/3/movie/" + id + "?api_key=63d250a5b71c307f7592228c79b729cf";

        JSONObject json = JsonReader.readJsonFromUrl(urlString);
        fdto.id = id;
        fdto.title = (String) json.get("title");
        fdto.cover = (String) json.get("poster_path");
        fdto.runtime = (int) json.get("runtime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        fdto.release_date = formatter.parse((String) json.get("release_date"));
        fdto.overview = (String) json.get("overview");
        fdto.rating = (Double) json.get("vote_average");
        return fdto;

    }

    public Set<GenreDto> getGenre(Long id) throws JSONException, IOException {
        Set<GenreDto> res = new HashSet<>();
        String urlString = "http://api.themoviedb.org/3/movie/" + id + "?api_key=63d250a5b71c307f7592228c79b729cf";

        JSONObject json = JsonReader.readJsonFromUrl(urlString);
        JSONArray lg = json.getJSONArray("genres");
        for (int i = 0; i < lg.length(); i++) {
            GenreDto gdto = new GenreDto();
            gdto.id = lg.getJSONObject(i).getLong("id");
            gdto.name = lg.getJSONObject(i).getString("name");
            res.add(gdto);
        }

        return res;
    }
    
    public List<PersonDto> getCast(Long id)  throws JSONException, IOException
    {
                List<PersonDto> res = new ArrayList<>();
        String urlString = "http://api.themoviedb.org/3/movie/" + id + "/credits?api_key=63d250a5b71c307f7592228c79b729cf";

        JSONObject json = JsonReader.readJsonFromUrl(urlString);
        JSONArray lg = json.getJSONArray("cast");
        for (int i = 0; i < lg.length(); i++) {
            PersonDto gdto = new  PersonDto();
            gdto.id = lg.getJSONObject(i).getLong("id");
            gdto.name = lg.getJSONObject(i).getString("name");
            res.add(gdto);
        }

        return res;
    }
    
        public List<PersonDto> getDirectors(Long id)  throws JSONException, IOException
    {
                List<PersonDto> res = new ArrayList<>();
        String urlString = "http://api.themoviedb.org/3/movie/" + id + "/credits?api_key=63d250a5b71c307f7592228c79b729cf";

        JSONObject json = JsonReader.readJsonFromUrl(urlString);
        JSONArray lg = json.getJSONArray("crew");
        for (int i = 0; i < lg.length(); i++) {
            PersonDto gdto = new  PersonDto();
            gdto.id = lg.getJSONObject(i).getLong("id");
            gdto.name = lg.getJSONObject(i).getString("name");
            if ("Director".equals(lg.getJSONObject(i).getString("job")))
                res.add(gdto);
        }

        return res;
    }

    public Admin() {
        make_context();
    }

    public void make_context() {
        try {
            Properties props = new Properties();
            //props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
            //props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
            //props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            //props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            //props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            ic = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ManageFilmRemote getManageFilmRemote() {
        if (mfr == null) {
            try {
                mfr = (ManageFilmRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
            } catch (NamingException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mfr;
    }

    public ManageProductRemote getManageProductRemote() {
        if (mpr == null) {
            try {
                mpr = (ManageProductRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageProduct!ejbs.ManageProductRemote");
            } catch (NamingException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mpr;
    }

    public ManageUserRemote getManageUserRemote() {
        if (mur == null) {
            try {
                mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
            } catch (NamingException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mur;
    }

    public ManageVideoRemote getManageVideoRemote() {
        if (mvr == null) {
            try {
                mvr = (ManageVideoRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageVideo!ejbs.ManageVideoRemote");
            } catch (NamingException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mvr;
    }

    public ManageTransactionRemote getManageTransactionRemote() {
        if (mtr == null) {
            try {
                mtr = (ManageTransactionRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageTransaction!ejbs.ManageTransactionRemote");
            } catch (NamingException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mtr;
    }

    /* public ManageGenreRemote getManageGenreRemote() {
     if (mgr == null) {
     try {
     mgr = (ManageTransactionRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageGenre!ejbs.ManageGenreRemote");
     } catch (NamingException ex) {
     Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     return mgr;
}*/
}
