/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDivers;

import dtos.FilmDto;
import dtos.VideoDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageProductRemote;
import exception.DuplicateKey;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import main.AdminFilm;
import main.HomeCinemaClient;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class TestDuplicateKey {

    private static InitialContext ic;
    private static ManageFilmRemote mpr = null;

    public static void main(String[] args) {
        try {
            AdminFilm af = new AdminFilm();
            make_context();
            getManageFilmRemote().createFilm(af.createFilm(14L));
            getManageFilmRemote().createFilm(af.createFilm(14L));
        } catch (JSONException ex) {
            Logger.getLogger(TestDuplicateKey.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestDuplicateKey.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TestDuplicateKey.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void make_context() {
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

    public static ManageFilmRemote getManageFilmRemote() {
        if (mpr == null) {
            try {
                mpr = (ManageFilmRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
            } catch (NamingException ex) {
                Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mpr;
    }

}
