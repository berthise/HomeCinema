/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import ejbs.ManageFilmRemote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author titou
 */
public class HomeCinemaClient {

    private static InitialContext ic;
    private static ManageFilmRemote mfl = null;

    public static void main(String[] args) {
        make_context();
        
        //truc a faire

    }

    private static void make_context() {
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
        if (mfl == null) {
            try {
                mfl = (ManageFilmRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageFilm!ejbs.ManageFilmRemote");
            } catch (NamingException ex) {
                Logger.getLogger(HomeCinemaClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mfl;
    }

}
