/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.SimpleUserDto;
import dtos.UserDto;
import dtos.VideoDto;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

            //creer american beauty
            FilmDto f = a.createFilm(14L);
            a.getManageFilmRemote().createFilm(f);
            VideoDto v = new VideoDto();
            v.resolution = 240;
            v.url = "http://geekompagny.ddns.net/ECOM/AmericanBeautyFilm.mp4";
            v.id = a.getManageVideoRemote().createVideo(v);
            a.getManageFilmRemote().addExistingVideo(f.id, v.id);
            VideoDto t = new VideoDto();
            t.resolution = 240;
            t.url = "http://geekompagny.ddns.net/ECOM/AmericanBeautyTrailer.mp4";
            a.getManageFilmRemote().setTrailer(f.id, t);

            //creer un produit
            ProductDto pdto = new ProductDto();
            pdto.name = "Main american beauty";
            pdto.price = 10D;
            pdto.id = a.getManageProductRemote().createProduct(pdto);
            a.getManageProductRemote().addExistingFilm(pdto.id, f.id, true);

                        //creer fight club
            FilmDto f2 = a.createFilm(550L);
            a.getManageFilmRemote().createFilm(f2);
            VideoDto v2 = new VideoDto();
            v2.resolution = 240;
            v2.url = "http://geekompagny.ddns.net/ECOM/FightClubFilm.mp4";
            v2.id = a.getManageVideoRemote().createVideo(v2);
            a.getManageFilmRemote().addExistingVideo(f2.id, v2.id);
            VideoDto t2 = new VideoDto();
            t2.resolution = 240;
            t2.url = "http://geekompagny.ddns.net/ECOM/FightClubTrailer.mp4";
            a.getManageFilmRemote().setTrailer(f2.id, t2);

            //creer un produit
            ProductDto pdto2 = new ProductDto();
            pdto2.name = "Main FC";
            pdto2.price = 8D;
            pdto2.id = a.getManageProductRemote().createProduct(pdto2);
            a.getManageProductRemote().addExistingFilm(pdto2.id, f2.id, true);
            
            //creer user robin
            UserDto u = new UserDto();
            u.birthDate = new Date();
            u.email = "truc@mail.net";
            u.firstName = "robin";
            u.nickName = "grandchamp";
            u.password = "password";

            a.getManageUserRemote().signUp(u);
            u = a.getManageUserRemote().login(u.email, u.password);

            //System.out.println(u.id);
            //System.out.println(pdto.id);
            //a.getManagetransactionRemote().addProduct(u.id, pdto.id);

            //print
            System.out.println("Film");
            List<FilmDto> lfdto = a.getManageFilmRemote().getAllFilm();
            for (FilmDto fdto : lfdto) {
                System.out.println(fdto.title);
            }
            System.out.println("Product");
            List<ProductDto> lpdto = a.getManageProductRemote().getAllProduct();
            for (ProductDto udto : lpdto) {
                System.out.println(udto.name);
            }
            System.out.println("User");
            Set<SimpleUserDto> ludto = a.getManageUserRemote().getAllUser();
            for (SimpleUserDto udto : ludto) {
                System.out.println(udto.nickName);
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
