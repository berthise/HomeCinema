/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UtilsJson.JsonReader;
import dtos.FilmDto;
import java.text.DateFormat;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import ejbs.ManageUserRemote;
import ejbs.ManageVideoRemote;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

    public FilmDto createFilm(Long id) throws JSONException, IOException, ParseException {
        System.out.println("Add film: " + id);
        FilmDto fdto = new FilmDto();
        String urlString = "http://api.themoviedb.org/3/movie/" + id + "?api_key=63d250a5b71c307f7592228c79b729cf";

        JSONObject json = JsonReader.readJsonFromUrl(urlString);
        fdto.id = id;
        fdto.title = (String) json.get("title");
        fdto.cover = (String) json.get("poster_path");
        fdto.runtime = (int) json.get("runtime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        fdto.release_date = formatter.parse((String) json.get("release_date"));
        fdto.overview = (String) json.get("overview");
        fdto.rating = (Double) json.get("vote_average");

        return fdto;

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
            Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ManageFilmRemote getManageFilmRemote() {
        if (mfr == null) {
            try {
                mfr = (ManageFilmRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
            } catch (NamingException ex) {
                Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mfr;
    }

    public ManageProductRemote getManageProductRemote() {
        if (mpr == null) {
            try {
                mpr = (ManageProductRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageProduct!ejbs.ManageProductRemote");
            } catch (NamingException ex) {
                Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mpr;
    }

    public ManageUserRemote getManageUserRemote() {
        if (mur == null) {
            try {
                mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
            } catch (NamingException ex) {
                Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mur;
    }

    public ManageVideoRemote getManageVideoRemote() {
        if (mvr == null) {
            try {
                mvr = (ManageVideoRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageVideo!ejbs.ManageVideoRemote");
            } catch (NamingException ex) {
                Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mvr;
    }

    public void makeAndSendMovie(Long id, String video_url, String trailer_url, Integer price) throws JSONException, IOException, ParseException {
        FilmDto fdto = new FilmDto();

        fdto = createFilm(id);

        VideoDto trailer = new VideoDto();
        VideoDto video = new VideoDto();
        video.url = video_url;
        video.resolution = 240;
        trailer.url = trailer_url;
        trailer.resolution = 240;

        getManageProductRemote().createProductWithFilm(fdto, trailer, video, new Double(price));
    }

}
