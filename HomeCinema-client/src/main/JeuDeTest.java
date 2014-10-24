/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author titou
 */
public class JeuDeTest {
    
        public static void main(String[] args) {
            HomeCinemaClient.main(args);
            AdminFilm.createFilm(new Long(11));
            AdminFilm.createFilm(new Long(12));
            AdminFilm.createFilm(new Long(13));
            AdminFilm.createFilm(new Long(14));
        }
}
