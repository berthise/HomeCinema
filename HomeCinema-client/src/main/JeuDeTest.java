/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import dtos.VideoDto;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class JeuDeTest {

    public static void main(String[] args) {
        try {
            AdminFilm af = new AdminFilm();
            af.makeAndSendMovie(new Long(14), "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4", new Integer(8));
            af.makeAndSendMovie(new Long(550), "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4", "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4", new Integer(10));
            af.makeAndSendMovie(new Long(5), "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4", new Integer(8));
            af.makeAndSendMovie(new Long(12), "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4", "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4", new Integer(10));
            af.makeAndSendMovie(new Long(11), "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4", new Integer(8));
            af.makeAndSendMovie(new Long(13), "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4", "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4", new Integer(10));
            af.makeAndSendMovie(new Long(17), "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4", "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4", new Integer(8));
            af.makeAndSendMovie(new Long(16), "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4", "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4", new Integer(10));
        } catch (JSONException ex) {
            Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
