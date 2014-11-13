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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author titou
 */
public class JeuDeTest2 {
    
        public static void main(String[] args) {
        try {
            Admin a = new Admin();
         FilmDto f = a.createFilm(14L);
         a.getManageFilmRemote().createFilm(f);
         VideoDto v = new VideoDto();
         v.resolution=240;
         v.url="http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4";
         v.id= a.getManageVideoRemote().createVideo(v);
         a.getManageFilmRemote().addExistingVideo(f.id, v.id);
         
         
         List<FilmDto> lfdto =  a.getManageFilmRemote().getAllFilm();
         for (FilmDto fdto :lfdto)
         {
             System.out.println(f.title);
         }
         
        } catch (JSONException ex) {
            Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JeuDeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
